$(document).ready(function() {
	var url = window.location.search;
	var idProgramme = url.substring(url.lastIndexOf("=") + 1);
	
	outils.carousel();
	
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
								
					for (var i = 0; i < programmes.length; i++) {
						var programme = programmes[i];
						
						$('.carousel-inner').append('<div class="item' + (programme["@id"] == idProgramme ? ' active"' : '"') + ' id="' + programme["@id"] + '">'
								+ blocMobile(programme)
	
								+ blocDesktop(programme)
						);
					}
				}
			});
		}
	});
});

function ajouterActeurs(acteurs) {
	var lesActeurs = '';
	if (acteurs != undefined) {
		for (var j = 0; j < acteurs.length; j++) {
			lesActeurs += acteurs[j].nomComplet + (j+1 == acteurs.length ? '' : ', ');
		}
	}
	return lesActeurs;
}

function construireBlocInformation (programme, estDesktop) {
	if(programme.dateRealisation == undefined && programme.realisateur == undefined && programme.acteurs == undefined){
		return (estDesktop ? '<p>Aucune information disponible.</p>' :'');
	}else
		return (programme.dateRealisation != undefined ? '<p><b>Date de r&eacute;alisation</b> : ' + programme.dateRealisation + '</p>' : '')
			+ (programme.realisateur != undefined ? '<p><b>R&eacute;alisateur</b> : ' + programme.realisateur.nomComplet + '</p>' : '')
			+ (programme.acteurs != undefined ? '<p><b>Acteurs</b> : ' +ajouterActeurs(programme.acteurs) + '</p>' : '');
}

function blocMobile(programme) {
	return '<div class="visible-xs visible-sm" style="margin-left: 5px;">'
		+ '<div class="media">'
		+ '<img class="media-object pull-left" style="width: 176px; height: 136px;" src="' + (programme.image != undefined ? programme.image : "img/defaut.jpg") + '" title="' + programme.nom + '" alt="' + programme.nom + '">'
		+ '<div class="media-body">'
		+ '<p class="media-heading" style="margin-left: 10px;"><b>' + programme.heureDebut + '</b> sur <a href="chaine.html?id=' + programme.chaine["@id"] + '">' + programme.chaine.nom + '</a></p>'
		+ '<p style="margin-left: 10px;">' +programme.categorie+ ' (' +programme.duree+ 'min)</p>'
		+ '<p style="margin-left: 10px;">' +programme.csa+ '</p>'
		+ '<p style="margin-left: 10px;">Le ' +programme.dateDebut+ '</p>'
		+ '</div>'
		+ '</div>'
		+ '<h3 style="color: black; text-align: left;">' + programme.nom + '</h3>'
		+ (programme.deuxiemeNom != undefined ? '<h5 style="color: darkslategray; text-align: left;">' + programme.deuxiemeNom + '</h5>' : '')
		+ blocResume (programme.description)
		+ enteteBlocInformation()
		+ '<div class="panel-body">'
		+ '<p><b>Horaire</b> : ' +programme.heureDebut+ ' - ' +programme.heureFin+ '</p>'
		+ construireBlocInformation (programme, false)
		+ '</div>'
		+ '</div>'
		+ '</div>';
}

function blocDesktop (programme){
	return '<div class="row hidden-xs hidden-sm"><a href="javascript:carouselPrecedent();" class="col-lg-offset-3 col-md-offset-3 gauche" title="Programme pr&eacute;c&eacute;dent" alt="Programme pr&eacute;c&eacute;dent"><span class="glyphicon glyphicon-chevron-left"></span></a>'
		+ '<img class="img-responsive" src="' + (programme.image != undefined ? programme.image : "img/defaut.jpg") + '" title="' + programme.nom + '" alt="' + programme.nom + '">'
		+ '<a href="javascript:carouselSuivant();" class="droite col-lg-offset-9 col-md-offset-9" title="Programme suivant" alt="Programme suivant"><span class="glyphicon glyphicon-chevron-right"></span></a><div>'
		+ '<div class="hidden-xs hidden-sm col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1">'
		+ '<h3 style="color: black;">' + programme.nom + '</h3>'
		+ (programme.deuxiemeNom != undefined ? '<h5 style="color: darkslategray;">' + programme.deuxiemeNom + '</h5>' : '')
		+ '<div class="panel panel-info">'
		+ '<div class="panel-heading">'
		+ '<h6 class="panel-title">Diffusion</h6>'
		+ '</div>'
		+ '<div class="panel-body">'
		+ '<p><b>Horaire</b> : ' +programme.heureDebut+ ' - ' +programme.heureFin+ '  (' +programme.duree+ 'min)</p>'
		+ '<p><b>Genre</b> : ' +programme.categorie+ ' sur <a href="chaine.html?id=' + programme.chaine["@id"] + '">' + programme.chaine.nom + '</a></p>'
		+ '<p><b>Public</b> : ' +programme.csa+ '</p>'
		+ '<p><b>Date</b> : ' +programme.dateDebut+ '</p>'
		+ '</div>'
		+ '</div>'
		+ blocResume (programme.description)
		+ enteteBlocInformation()
		+ '<div class="panel-body">'
		+ construireBlocInformation (programme, true)
		+ '</div>'
		+ '</div>'
		+ '</div>'
		+ '</div>';
}

function enteteBlocInformation () {
	return '<div class="panel panel-info">'
		+ '<div class="panel-heading">'
		+ '<h6 class="panel-title">Informations</h6>'
		+ '</div>';
}

function blocResume (description) {
	return '<div class="panel panel-info">'
		+ '<div class="panel-heading">'
		+ '<h6 class="panel-title">R&eacute;sum&eacute;</h6>'
		+ '</div>'
		+ '<div class="panel-body">'
		+ (description == undefined ? 'Aucune description.' : description)
		+ '</div>'
		+ '</div>';
}

function carouselSuivant() {
	$('.carousel').carousel('next');
}

function carouselPrecedent() {
	$('.carousel').carousel('prev');
}