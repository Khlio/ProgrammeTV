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
		treshold:0
	});
	
	var url = window.location.search;
	var idChaine = url.substring(url.lastIndexOf("=")+1);
	idChaine=parseInt(idChaine);
	
	var aujourdhui = new Date();
	var dateDuJour = dateFormatee(aujourdhui);
	var jourFr='';
	var moisFr='';
	
	var date=new Date(aujourdhui.getFullYear(),aujourdhui.getMonth(),aujourdhui.getDate()-1);
	
	for(var i=0;i<9;i++){
		jourFr=jourEnFrancais(date.getDay());
		moisFr=moisEnFrancais(date.getMonth());
		$('#date').append(
			'<option value="'+dateFormatee(date)+'" '+((date.getDate()== aujourdhui.getDate())?'selected':'')+'>'+jourFr+' '+date.getDate()+' '+moisFr
		);
		date.setDate(date.getDate()+1);
	}
	
	outils.ajaxRequest(outils.url+'/chaines',function(json){
		if(json==undefined) document.location.href="index.html";
		else{
			var chaines=json.chaine;
			var description='';
			var programmes;
			var lesProgrammes;
			for(var i=0;i<chaines.length;i++){
				$('.carousel-inner').append(
					'<div class="item'+(chaines[i]["@id"]==idChaine?' active"':'"')+' id="'+chaines[i]["@id"]+'" align="center">'+
						'<img src="img/carousel/'+chaines[i]["@id"]+'.gif" title="'+chaines[i].nom+'" alt="'+chaines[i].nom+'">'+									
					'</div>'
				);
				affichageProgrammes(chaines[i]["@id"],dateDuJour);
			}
		}
	});
	
	$("#date").change(function(){
		var listeDate= document.getElementById('date');
		dateDuJour=listeDate.options[listeDate.selectedIndex].value;
		$('.ligneProgrammes').remove();
		outils.ajaxRequest(outils.url+'/chaines',function(json){
			if(json!=undefined){
				var chaines=json.chaine;
				for(var i=0;i<chaines.length;i++){
					affichageProgrammes(chaines[i]["@id"],dateDuJour);
				}
			}
		});
	});
});

function ajouterLigneProgrammes(idChaine,programmes){
	var ligneProgramme='<div class="ligneProgrammes row">';
	var description='';
	for(var i=0;i<programmes.length;i++){
		if(programmes[i]!='null'){
			description=(programmes[i].description==undefined)?'Aucune description':programmes[i].description.substr(0, 64)+"...";
			ligneProgramme+='<div class="programmes col-lg-6 col-md-6 col-sm-6">'+
				'<a href="programme.html?id='+programmes[i]["@id"]+'"><h4>'+programmes[i].nom+'</h4></a>'+
				'<p>'+programmes[i].heureDebut+' - '+programmes[i].categorie+'</p>'+
				'<p class="hidden-xs hidden-sm">'+description+'</p>'+
			'</div>'+
			(i==0?'<span class="visible-xs ligne"></span>':'');
		}
	}
	ligneProgramme+='</div>';							
	$('#'+idChaine).append(ligneProgramme);
}

function affichageProgrammes(idChaine,dateDuJour){
	outils.ajaxRequest(outils.url+'/programmes/chaine/'+idChaine+'/'+dateDuJour,function(json) {
	if(json!=undefined){
		programmes=json.programme;						
		for(var a=0;a<programmes.length;a++){				
			lesProgrammes = [programmes[a],(a+1==programmes.length?'null':programmes[a+1])];
			ajouterLigneProgrammes(idChaine,lesProgrammes);
			a++;
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

function dateFormatee(date){
	return date.getFullYear()
		+(date.getMonth()<10?'0'+(date.getMonth()+1):date.getMonth()+1)
		+(date.getDate()<10?'0'+date.getDate():date.getDate());
}