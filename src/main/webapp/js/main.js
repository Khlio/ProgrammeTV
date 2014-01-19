$(document).ready(function() {
	$('#onglets a').click(function(event) {
		event.preventDefault();
		$(this).tab('show');
	});
	remplirOngletDuSoir();
});

function remplirOngletDuSoir() {
	outils.ajaxRequest(outils.url + '/programmes/soir', function(json) {
		remplirOnglet(json, 'tonight', 'col-lg-5 col-md-5', 'col-lg-5 col-md-5 col-lg-offset-1 visible-md visible-lg', '');
	});
}

function remplirOngletEnCeMoment() {
	outils.ajaxRequest(outils.url + '/programmes/moment', function(json) {
		remplirOnglet(json, 'now', 'col-lg-4 col-md-4', 'col-lg-4 col-md-4 visible-md visible-lg', 'col-lg-3 col-md-3 visible-md visible-lg');
	});
}

function remplirOnglet(json, onglet, premierProgramme, deuxiemeProgramme, troisiemeProgramme) {
	var programmes = json.programme;
	var idChaine = 0;
	var compteurProgramme = 0;
	var attributDIV = '';
	var description = '';
	var baliseFermante = '';
	$('#' + onglet).html('');
	
	for (var i = 0; i < programmes.length; i++) {
		var programme = programmes[i];
		if (idChaine != programme.chaine["@id"]) {
			baliseFermante = ((idChaine != programme.chaine["@id"] && idChaine != 0) ? '</div>' : '');
			idChaine = programme.chaine["@id"];
			$('#' + onglet).append(baliseFermante
					+ '<div class="row chaine media" id="' + onglet + '-' + programme.chaine["@id"] + '">'
					+ '<a class="pull-left" href="chaine.html?id=' + programme.chaine["@id"] + '">'
					+ '<img class="media-object img-responsive" src="img/' + programme.chaine["@id"] + '.gif" />'
					+ '</a>'
			);
			compteurProgramme = 0;
		}
		attributDIV = (compteurProgramme == 0
				? premierProgramme
				: (compteurProgramme == 1
						? deuxiemeProgramme
						: troisiemeProgramme));
		description = (programme.description == undefined) ? 'Aucune description' : programme.description.substr(0, 200) + "...";
		$('#' + onglet + '-' + programme.chaine["@id"]).append('<div class="media-body ' + attributDIV + '" id="' + programme["@id"] + '">'
				+ '<a href="programme.html?id=' + programme["@id"] + '">'
				+ '<h4 class="media-heading">' + programme.nom + '</h4>'
				+ '<p>' + programme.heureDebut + ' - ' + programme.categorie + '</p>'
				+ '<p class="hidden-xs">' + description + '</p>'
				+ '</a>'
				+ '</div>'
		);
		compteurProgramme++;
	}
	$('#' + onglet).append('</div>');
}