$(document).ready(function() {
	$('.carousel').carousel({
		interval: false
	});
	
	var url = window.location.search;
	var idChaine = url.substring(url.lastIndexOf("=")+1);
	idChaine=parseInt(idChaine);
	
	var annee = new Date().getFullYear();
	
	var mois = new Date().getMonth()+1;
	mois=(mois<10)?'0'+mois:mois;
	
	var jour = new Date().getDate();
	
	var dateDuJour =0;
	var jourDebut=0;
	var jourFin=0;
	var jourFr='';
	
	var moisFr=moisEnFrancais(new Date().getMonth());
	
	if(jour<=15){
		jourDebut=1;
		jourFin=15;
	}
	else{
		jourDebut=16;
		jourFin=jourDansLeMois(new Date().getMonth(),annee);
	}
	
	var jourDeLaSemaine=new Date(annee, new Date().getMonth(), jourDebut).getDay();
	for(var i=jourDebut;i<=jourFin;i++){
		if(i==jour) dateDuJour=annee+''+mois+''+((jour<10)?'0'+jour:jour);
		jourFr=jourEnFrancais(jourDeLaSemaine);
		$('#date').append(
			'<option value="'+annee+''+mois+''+((i<10)?'0'+i:i)+'" '+((i==jour)?'selected':'')+'>'+jourFr+' '+i+' '+moisFr
		);
		jourDeLaSemaine++;
		if(jourDeLaSemaine==7) jourDeLaSemaine=0;
	}
	
	
	outils.ajaxRequest(outils.url+'/chaines',function(json){
		if(json==undefined) document.location.href="index.html";
		else{
			var chaines=json.chaine;
			var baliseOuvrante ='';
			for(var i=0;i<chaines.length;i++){
				baliseOuvrante='<div class="item'+((chaines[i]["@id"]==idChaine)?' active"':'"')+' id="'+chaines[i]["@id"]+'" align="center">';
				$('.carousel-inner').append(
					baliseOuvrante+
						'<img src="img/carousel/'+chaines[i]["@id"]+'.gif" title="'+chaines[i].nom+'" alt="'+chaines[i].nom+'">'+
					'</div>'
				);
			}
		}
	});
	
	affichageProgrammes(idChaine,dateDuJour);
	
	
	$(".carousel-control.left").click(function(){
		$('.programmes').remove();
		var slidePrecedente = $('div.active').index()-1;
		if(slidePrecedente<0) slidePrecedente=$('.item').length-1;
		$('#carousel-chaine').carousel(slidePrecedente);
		idChaine=(slidePrecedente==$('.item').length-1)?$('div.next').attr('id'):$('div.prev').attr('id');
		affichageProgrammes(idChaine,dateDuJour);
		return false;   
	});
	
	$(".carousel-control.right").click(function(){
		$('.programmes').remove();
		var slideSuivante = $('div.active').index()+1;
		if(slideSuivante>=$('.item').length) slideSuivante=0;
		$('#carousel-chaine').carousel(slideSuivante);
		idChaine=(slideSuivante==0)?$('div.prev').attr('id'):$('div.next').attr('id');
		affichageProgrammes(idChaine,dateDuJour);
		return false;   
	});
	
	$("#date").change(function(){
		var listeDate= document.getElementById('date');
		dateDuJour=listeDate.options[listeDate.selectedIndex].value;
		$('.programmes').remove();
		affichageProgrammes(idChaine,dateDuJour);
	});
});

function affichageProgrammes(idChaine,dateDuJour) {
	outils.ajaxRequest(outils.url+'/programmes/chaine/'+idChaine+'/'+dateDuJour,function(json) {
		if(json==undefined) document.location.href="index.html";
		else{
			var programmes=json.programme;
			
			for(var i=0;i<programmes.length;i++){			
				description=(programmes[i].description==undefined)?'Aucune description':programmes[i].description.substr(0, 64)+"...";
				$('#'+idChaine).append(
					'<div class="programmes col-lg-6 col-md-6 col-sm-6">'+
						'<a href="programme.html?id='+programmes[i]["@id"]+'"><h4>'+programmes[i].nom+'</h4></a>'+
						'<p>'+programmes[i].heureDebut+' - '+programmes[i].categorie+'</p>'+
						'<p class="hidden-xs hidden-sm">'+description+'</p>'+
					'</div>'
				);
			}
		}
	});
}

function jourDansLeMois(mois,annee) {
	var date = new Date(annee, mois+1, 0);
	return date.getDate();
}

function jourEnFrancais(jour){
	var jourFrancais='';
	switch (jour){
		case 0:
			jourFrancais='Dimanche';
			break;
		case 1:
			jourFrancais='Lundi';
			break;
		case 2:
			jourFrancais='Mardi'; 
			break;
		case 3:
			jourFrancais='Mercredi'; 
			break;
		case 4:
			jourFrancais='Jeudi'; 
			break;
		case 5:
			jourFrancais='Vendredi'; 
			break;
		case 6:
			jourFrancais='Samedi'; 
			break;
	};
	return jourFrancais;
}

function moisEnFrancais(mois){
	var moisFrancais='';
	switch (mois){
		case 0:
			moisFrancais='Janvier';
			break;
		case 1:
			moisFrancais='Fevrier';
			break;
		case 2:
			moisFrancais='Mars'; 
			break;
		case 3:
			moisFrancais='Avril'; 
			break;
		case 4:
			moisFrancais='Mai'; 
			break;
		case 5:
			moisFrancais='Juin'; 
			break;
		case 6:
			moisFrancais='Juillet'; 
			break;
		case 7:
			moisFrancais='Aout'; 
			break;
		case 8:
			moisFrancais='Septembre'; 
			break;
		case 9:
			moisFrancais='Octobre'; 
			break;
		case 10:
			moisFrancais='Novembre'; 
			break;
		case 11:
			moisFrancais='Decembre'; 
			break;
		
	};
	return moisFrancais;
}