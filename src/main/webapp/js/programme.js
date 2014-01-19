$(document).ready(function() {
	var url = window.location.search;
	var idProgramme = url.substring(url.lastIndexOf("=") + 1);
	
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
	
	outils.ajaxRequest(outils.url + '/programmes/' + idProgramme, function(programme) {
		if (programme == undefined) {
			document.location.href = "index.html";
		} else {
			var elements = programme.dateDebut.split('-');
			var dateDuProgramme = elements[2] + elements[1] + elements[0];
			outils.ajaxRequest(outils.url + '/programmes/chaine/' + programme.chaine["@id"] + '/' + dateDuProgramme, function(json) {
				if (json == undefined) {
					document.location.href = "index.html";
				} else {
					var programmes = json.programme;					
					var acteurs = '';
					for (var i = 0; i < programmes.length; i++) {
						var programme = programmes[i];
						if (programme.acteurs != undefined) {
							acteurs = '<p>Avec ';
							for (var j = 0; j < programme.acteurs.length; j++) {
								acteurs += programme.acteurs[j].nomComplet + (j+1 == programme.acteurs.length ? '' : ', ');
							}
						}
						acteurs += '</p>';
						var description = (programme.description == undefined ? 'Aucune description' : programme.description);
						$('.carousel-inner').append('<div class="item' + (programme["@id"] == idProgramme ? ' active"' : '"') + ' id="' + programme["@id"] + '">'
								+ '<img class="img-responsive" src="' + (programme.image != undefined ? programme.image : "img/defaut.jpg") + '" title="' + programme.nom + '" alt="' + programme.nom + '">'
								+ '<div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1">'
								+ '<h3>' + programme.nom + '</h3>'
								+ (programme.deuxiemeNom != undefined ? '<h5>' + programme.deuxiemeNom + '</h5>' : '')
								+ '<p>' + programme.heureDebut + ' - ' + programme.heureFin + ' (' + programme.duree + ' min) le ' + programme.dateDebut
								+ ' sur <a href="chaine.html?id=' + programme.chaine["@id"] + '">' + programme.chaine.nom + '</a></p>'
								+ '<p>Genre : ' + programme.categorie + '</p>'
								+ '<p>Public : ' + programme.csa + '</p>'
								+ (programme.dateRealisation != undefined ? '<p>Date de r&eacute;alisation : ' + programme.dateRealisation + '</p>' : '')
								+ (programme.realisateur != undefined ? '<p>R&eacute;alisateur : ' + programme.realisateur.nomComplet + '</p>' : '')
								+ acteurs + '<p>'+description+'</p>'
								+ '</div>'
								+ '</div>'
						);
					}
				}
			});
		}
	});
});