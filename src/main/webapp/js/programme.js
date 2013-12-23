$(document).ready(function() {
	var url = window.location.search;
	var idProgramme = url.substring(url.lastIndexOf("=")+1);
	
	outils.ajaxRequest(outils.url+'/programmes/'+idProgramme,function(programme) {
		if(programme==undefined) document.location.href="index.html";
		else{
			var image = document.getElementById('imageProgramme');
			image.src=(programme.image!=undefined)?programme.image:"img/defaut.jpg";
			image.title=programme.nom;
			image.alt=programme.nom;
			var description =(programme.description==undefined)?'Aucune description':programme.description;
			document.getElementById('informationsProgramme').setAttribute('idProgramme',programme["@id"]);
			$('#informationsProgramme').html(
				'<h3>'+programme.nom+'</h3>'+
				'<p>'+programme.heureDebut+' - '+programme.heureFin+'</p>'+
				'<p>'+programme.categorie+'</p>'+
				'<p>Programme pour : '+programme.csa+'</p>'+
				'<p>'+description+'</p>'
			);
		}
	});
});

function programmeSuivant(){
	var idProgramme=document.getElementById('informationsProgramme').getAttribute('idProgramme');
	outils.ajaxRequest(outils.url+'/programmes/suivant/'+idProgramme,function(programme) {
		if(programme!=undefined){
			var image = document.getElementById('imageProgramme');
			image.src=(programme.image!=undefined)?programme.image:"img/defaut.jpg";
			image.title=programme.nom;
			image.alt=programme.nom;
			var description =(programme.description==undefined)?'Aucune description':programme.description;
			document.getElementById('informationsProgramme').setAttribute('idProgramme',programme["@id"]);
			$('#informationsProgramme').html(
				'<h3>'+programme.nom+'</h3>'+
				'<p>'+programme.heureDebut+' - '+programme.heureFin+'</p>'+
				'<p>'+programme.categorie+'</p>'+
				'<p>Programme pour : '+programme.csa+'</p>'+
				'<p>'+description+'</p>'
			);
		}
	});
}

function programmePrecedent(){
	var idProgramme=document.getElementById('informationsProgramme').getAttribute('idProgramme');
	outils.ajaxRequest(outils.url+'/programmes/precedent/'+idProgramme,function(programme) {
		if(programme!=undefined){
			var image = document.getElementById('imageProgramme');
			image.src=(programme.image!=undefined)?programme.image:"img/defaut.jpg";
			image.title=programme.nom;
			image.alt=programme.nom;
			var description =(programme.description==undefined)?'Aucune description':programme.description;
			document.getElementById('informationsProgramme').setAttribute('idProgramme',programme["@id"]);
			$('#informationsProgramme').html(
				'<h3>'+programme.nom+'</h3>'+
				'<p>'+programme.heureDebut+' - '+programme.heureFin+'</p>'+
				'<p>'+programme.categorie+'</p>'+
				'<p>Programme pour : '+programme.csa+'</p>'+
				'<p>'+description+'</p>'
			);
		}
	});
}