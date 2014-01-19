$(document).ready(function() {
	var jourEnFrancais = ['Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi'];
	var moisEnFrancais = ['Janvier', 'F&eacute;vrier', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Ao&ucirc;t', 'Septembre', 'Octobre', 'Novembre', 'D&eacute;cembre'];
	
	var aujourdhui = new Date();
	var jourFr = '';
	var moisFr = '';
	var date = new Date(aujourdhui.getFullYear(), aujourdhui.getMonth(), aujourdhui.getDate() - 1);
	
	for (var i = 0; i < 9; i++) {
		jourFr = jourEnFrancais[date.getDay()];
		moisFr = moisEnFrancais[date.getMonth()];
		$('#date').append('<option value="' + dateFormatee(date) + '" ' + (date.getDate() == aujourdhui.getDate() ? 'selected': '') + '>' + jourFr + ' ' + date.getDate() + ' ' + moisFr);
		date.setDate(date.getDate() + 1);
	}
});

function dateFormatee(date) {
	return date.getFullYear()
			+ (date.getMonth() < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1)
			+ (date.getDate() < 10 ? '0' + date.getDate() : date.getDate());
}