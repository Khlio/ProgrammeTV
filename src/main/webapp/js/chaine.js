$(document).ready(function() {
	$('.carousel').carousel({
		interval: false
	});
	
	$('.carousel-inner').swipe({
		swipeLeft: function(event, direction, distance, duration, fingerCount) {
			$(this).parent().carousel('next'); 
		},
		swipeRight: function(event, direction, distance, duration, fingerCount) {
			$(this).parent().carousel('prev');
		},
		treshold: 0
	});
	
	var url = window.location.search;
	var idChaine = url.substring(url.lastIndexOf("=") + 1);
	idChaine = parseInt(idChaine);
	var dateDuJour = dateFormatee(new Date());
	
	outils.ajaxRequest(outils.url + '/chaines', function(json) {
		if (json == undefined) {
			document.location.href = "index.html";
		} else {
			var chaines = json.chaine;
			for (var i = 0; i < chaines.length; i++) {
				$('.carousel-inner').append('<div class="item ' + (chaines[i]["@id"] == idChaine ? 'active"' : '"') + ' align="center">'
						+ '<img src="img/carousel/' + chaines[i]["@id"] + '.gif" title="' + chaines[i].nom + '" alt="' + chaines[i].nom + '">'
						+ '<table class="col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2" id="' + chaines[i]["@id"] + '"></table>'
						+ '</div>');
				affichageProgrammes(chaines[i]["@id"], dateDuJour);
			}
		}
	});
	
	$("#date").change(function() {
		var listeDate = document.getElementById('date');
		dateDuJour = listeDate.options[listeDate.selectedIndex].value;
		$('tr').remove();
		outils.ajaxRequest(outils.url + '/chaines', function(json) {
			if (json != undefined) {
				var chaines = json.chaine;
				for (var i = 0; i < chaines.length; i++) {
					affichageProgrammes(chaines[i]["@id"], dateDuJour);
				}
			}
		});
	});
});

function ajouterLigneProgramme(idChaine, programme, dernierProgramme){
	var description = (programme.description == undefined ? 'Aucune description' : programme.description.substr(0, 200) + "...");
	
	var ligneProgramme= '<tr' +(dernierProgramme?'':' class="ligneProgramme"')+ '>'
		+ '<td class="cellule-heure">' +programme.heureDebut+ '</td>';
		
	if(programme.image != undefined){
		ligneProgramme += '<td class="cellule-image">'
			+ '<a href="programme.html?id=' + programme["@id"] + '">'
			+ '<img src="' +programme.image+ '" title="' +programme.nom+ '" style="width: 100px; height: 77px"></a></td>'
			+ '<td>';
	}else
		ligneProgramme += '<td colspan="2" class="cellule-programme">';
	
	ligneProgramme += '<a href="programme.html?id=' + programme["@id"] + '"><h4>' + programme.nom + '</h4></a>'
			+ '<p>' +programme.categorie+ ' (' +programme.duree+ 'min)</p>'
			+ '<p class="hidden-xs hidden-sm">' +description+ '</p>'
			+ '</td></tr>';
			
	$('#' + idChaine).append(ligneProgramme);
}

function affichageProgrammes(idChaine, dateDuJour){
	outils.ajaxRequest(outils.url + '/programmes/chaine/' + idChaine + '/' + dateDuJour, function(json) {
		if (json != undefined) {
			var programmes = json.programme;
			for (var i = 0; i < programmes.length; i++) {
				var dernierProgramme = false;
				if(i+1 == programmes.length) dernierProgramme = true;
				ajouterLigneProgramme(idChaine,programmes[i],dernierProgramme);
			}
		}
	});
}