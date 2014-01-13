$(document).ready(function() {
	var url = window.location.search;
	var idProgramme = url.substring(url.lastIndexOf("=")+1);
	var dateDuProgramme='';
	
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
		treshold:0
	});
	
	outils.ajaxRequest(outils.url+'/programmes/'+idProgramme,function(programme){
		if(programme==undefined) document.location.href="index.html";
		else{
			var elements = programme.dateDebut.split('-');
			dateDuProgramme=elements[2]+elements[1]+elements[0];
			outils.ajaxRequest(outils.url+'/programmes/chaine/'+programme.chaine["@id"]+'/'+dateDuProgramme,function(json) {
				if(json==undefined) document.location.href="index.html";
				else{
					var programmes=json.programme;					
					var acteurs='';
					for(var i=0;i<programmes.length;i++){
						if(programmes[i].acteurs!=undefined){
							acteurs='<p>Avec ';
							for(var a=0; a<programmes[i].acteurs.length; a++){
								acteurs+=programmes[i].acteurs[a].nomComplet+(a+1==programmes[i].acteurs.length?'':', ');
							}
						}
						acteurs+='</p>';
						var description =(programmes[i].description==undefined)?'Aucune description':programmes[i].description;
						$('.carousel-inner').append(
							'<div class="item'+(programmes[i]["@id"]==idProgramme?' active"':'"')+' id="'+programmes[i]["@id"]+'">'+
								'<img class="img-responsive" src="'+(programmes[i].image!=undefined?programmes[i].image:"img/defaut.jpg")+'" title="'+programmes[i].nom+'" alt="'+programmes[i].nom+'">'+
								'<div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1">'+
									'<h3>'+programmes[i].nom+'</h3>'+
									(programmes[i].deuxiemeNom!=undefined?'<h5>'+programmes[i].deuxiemeNom+'</h5>':'')+
									'<p>'+programmes[i].heureDebut+' - '+programmes[i].heureFin+' ('+programmes[i].duree+' min) le '+programmes[i].dateDebut+' sur <a href="chaine.html?id='+programmes[i].chaine["@id"]+'">'+programmes[i].chaine.nom+'</a></p>'+
									'<p>Genre : '+programmes[i].categorie+'</p>'+
									'<p>Public : '+programmes[i].csa+'</p>'+
									(programmes[i].dateRealisation!=undefined?'<p>Date de réalisation : '+programmes[i].dateRealisation+'</p>':'')+
									(programmes[i].realisateur!=undefined?'<p>Réalisateur : '+programmes[i].realisateur.nomComplet+'</p>':'')+
									acteurs+
									'<p>'+description+'</p>'+						
								'</div>'+
							'</div>'
						);
					}
				}
			});
		}
	});
});