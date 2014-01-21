﻿$(document).ready(function() {
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
					var enteteBlocInformation = '<div class="panel panel-info">'
								+ '<div class="panel-heading">'
								+ '<h6 class="panel-title">Informations</h6>'
								+ '</div>';
								
					for (var i = 0; i < programmes.length; i++) {
						var programme = programmes[i];
						acteurs = '';
						if (programme.acteurs != undefined) {
							for (var j = 0; j < programme.acteurs.length; j++) {
								acteurs += programme.acteurs[j].nomComplet + (j+1 == programme.acteurs.length ? '' : ', ');
							}
						} 
						
						var blocResume = '<div class="panel panel-info">'
								+ '<div class="panel-heading">'
								+ '<h6 class="panel-title">R&eacute;sum&eacute;</h6>'
								+ '</div>'
								+ '<div class="panel-body">'
								+ (programme.description == undefined ? 'Aucune description.' : programme.description)
								+ '</div>'
								+ '</div>';
								
						var informationsMobile = true;
						if(programme.dateRealisation == undefined && programme.realisateur == undefined && programme.acteurs == undefined){
							var corpsBlocInformation = '<p>Aucune information disponible.</p>';
							informationsMobile = false;
						}else
							var corpsBlocInformation = (programme.dateRealisation != undefined ? '<p><b>Date de r&eacute;alisation</b> : ' + programme.dateRealisation + '</p>' : '')
								+ (programme.realisateur != undefined ? '<p><b>R&eacute;alisateur</b> : ' + programme.realisateur.nomComplet + '</p>' : '')
								+ (programme.acteurs != undefined ? '<p><b>Acteurs</b> : ' +acteurs + '</p>' : '');
						
						$('.carousel-inner').append('<div class="item' + (programme["@id"] == idProgramme ? ' active"' : '"') + ' id="' + programme["@id"] + '">'
								+ '<div class="visible-xs visible-sm" style="margin-left: 5px;">'
								+ '<div class="media">'
								+ '<img class="media-object pull-left" style="width: 176px; height: 136px;" src="' + (programme.image != undefined ? programme.image : "img/defaut.jpg") + '" title="' + programme.nom + '" alt="' + programme.nom + '">'
								+ '<div class="media-body">'
								+ '<p class="media-heading" style="margin-left: 10px;"><b>' + programme.heureDebut + '</b> sur <a href="chaine.html?id=' + programme.chaine["@id"] + '">' + programme.chaine.nom + '</a></p>'
								+ '<p style="margin-left: 10px;">' +programme.categorie+ ' (' +programme.duree+ 'min)</p>'
								+ '<p style="margin-left: 10px;">' +programme.csa+ '</p>'
								+ '<p style="margin-left: 10px;">Le ' +programme.dateDebut+ '</p>'
								+ '</div>'
								+ '</div>'
								+ '<h3 style="text-align: left;">' + programme.nom + '</h3>'
								+ (programme.deuxiemeNom != undefined ? '<h5 style="text-align: left;">' + programme.deuxiemeNom + '</h5>' : '')
								+ blocResume
								+ enteteBlocInformation
								+ '<div class="panel-body">'
								+ '<p><b>Horaire</b> : ' +programme.heureDebut+ ' - ' +programme.heureFin+ '</p>'
								+ (informationsMobile ? corpsBlocInformation : '')
								+ '</div>'
								+ '</div>'
								+ '</div>'
	
								+ '<img class="hidden-xs hidden-sm img-responsive" src="' + (programme.image != undefined ? programme.image : "img/defaut.jpg") + '" title="' + programme.nom + '" alt="' + programme.nom + '">'
								+ '<div class="hidden-xs hidden-sm col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1">'
								+ '<h3>' + programme.nom + '</h3>'
								+ (programme.deuxiemeNom != undefined ? '<h5>' + programme.deuxiemeNom + '</h5>' : '')
								+ '<div class="panel panel-info">'
								+ '<div class="panel-heading">'
								+ '<h6 class="panel-title">Diffusion</h6>'
								+ '</div>'
								+ '<div class="panel-body">'
								+ '<p><b>Horaire</b> : ' +programme.heureDebut+ ' - ' +programme.heureFin+ '</p>'
								+ '<p><b>Genre</b> : ' +programme.categorie+ ' (' +programme.duree+ 'min) sur <a href="chaine.html?id=' + programme.chaine["@id"] + '">' + programme.chaine.nom + '</a></p>'
								+ '<p><b>Public</b> : ' +programme.csa+ '</p>'
								+ '<p><b>Date</b> : ' +programme.dateDebut+ '</p>'
								+ '</div>'
								+ '</div>'
								+ blocResume
								+ enteteBlocInformation
								+ '<div class="panel-body">'
								+ corpsBlocInformation
								+ '</div>'
								+ '</div>'
								+ '</div>'
								+ '</div>'
						);
					}
				}
			});
		}
	});
});