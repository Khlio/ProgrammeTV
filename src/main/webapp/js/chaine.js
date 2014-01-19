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
				$('.carousel-inner').append('<div class="item ' + (chaines[i]["@id"] == idChaine ? 'active"' : '"') + ' id="' + chaines[i]["@id"] + '" align="center">'
						+ '<img src="img/carousel/' + chaines[i]["@id"] + '.gif" title="' + chaines[i].nom + '" alt="' + chaines[i].nom + '">'
						+ '</div>');
				affichageProgrammes(chaines[i]["@id"], dateDuJour);
			}
		}
	});
	
	$("#date").change(function() {
		var listeDate = document.getElementById('date');
		dateDuJour = listeDate.options[listeDate.selectedIndex].value;
		$('.ligneProgrammes').remove();
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

function affichageProgrammes(idChaine, dateDuJour) {
	outils.ajaxRequest(outils.url + '/programmes/chaine/' + idChaine + '/' + dateDuJour, function(json) {
		if (json != undefined) {
			var programmes = json.programme;						
			for (var i = 0; i < programmes.length; i++) {				
				lesProgrammes = [programmes[i], (i+1 == programmes.length ? 'null' : programmes[i+1])];
				ajouterLigneProgrammes(idChaine, lesProgrammes);
				i++;
			}
		}
	});
}

function ajouterLigneProgrammes(idChaine, programmes) {
	var ligneProgramme = '<div class="ligneProgrammes row">';
	var description = '';
	for (var i = 0; i < programmes.length; i++) {
		if (programmes[i] != 'null') {
			description = (programmes[i].description == undefined ? 'Aucune description' : programmes[i].description.substr(0, 64) + "...");
			ligneProgramme += '<div class="programmes col-lg-6 col-md-6 col-sm-6">'
					+ '<a href="programme.html?id=' + programmes[i]["@id"] + '"><h4>' + programmes[i].nom + '</h4></a>'
					+ '<p>' + programmes[i].heureDebut + ' - ' + programmes[i].categorie + '</p>'
					+ '<p class="hidden-xs hidden-sm">' + description + '</p>'
					+ '</div>'
					+ (i == 0 ? '<span class="visible-xs ligne"></span>' : '');
		}
	}
	ligneProgramme += '</div>';							
	$('#'+idChaine).append(ligneProgramme);
}