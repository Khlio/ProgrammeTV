$(document).ready(function() {
	var url = window.location.search;
	var idChaine = url.substring(url.lastIndexOf("=")+1);
	idChaine=parseInt(idChaine);
	
	outils.ajaxRequest(outils.url+'/programmes/chaine/'+idChaine+'/20131216',function(json) {
		if(json==undefined) document.location.href="index.html";
		else{
			programmes=json.programme;
			var image = document.getElementById('imageChaine');
			image.src='img/'+idChaine+'.gif';
			image.title=programmes[0].chaine.nom;
			image.alt=programmes[0].chaine.nom;
			document.getElementById('imageChaine').setAttribute('idChaine',idChaine);
			
			if(idChaine==1) 	document.getElementById('lienChainePrecedente').setAttribute('href','javascript:;');
			else 				document.getElementById('lienChainePrecedente').setAttribute('href','chaine.html?id='+(idChaine-1));
			if(idChaine==19) 	document.getElementById('lienChaineSuivante').setAttribute('href','javascript:;');
			else 				document.getElementById('lienChaineSuivante').setAttribute('href','chaine.html?id='+(idChaine+1));
			
			for(var i=0;i<programmes.length;i++){			
				description=(programmes[i].description==undefined)?'Aucune description':programmes[i].description.substr(0, 64)+"...";
				$('#programmes').append(
					'<div class="programmes col-lg-6 col-md-6 col-sm-6">'+
						'<a href="programme.html?id='+programmes[i]["@id"]+'"><h4>'+programmes[i].nom+'</h4></a>'+
						'<p>'+programmes[i].heureDebut+' - '+programmes[i].categorie+'</p>'+
						'<p class="hidden-xs hidden-sm">'+description+'</p>'+
					'</div>'
				);
			}
		}
	});
});

function chaineSuivante(){

}

function chainePrecedente(){

}