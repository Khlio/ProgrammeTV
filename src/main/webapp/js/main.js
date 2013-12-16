var index = {
    init: function() {
    	$('#onglets a').click(function(event) {
    		event.preventDefault();
    		$(this).tab('show');
    	});
    	majOngletDuSoir();
    }
};

$(document).ready(function() { index.init(); });

function majOngletEnCeMoment() {
	outils.ajaxRequest(outils.url+'/programmes/moment',function(json) {
		programmes=json.programme;
		var idChaine=0;
		var compteurProgramme=0;
		var attributDIV='';
		var description='';
		var baliseFermante='';
		$('#now').html('');
		for(var i=0;i<programmes.length;i++){
			if(idChaine!=programmes[i].chaine["@id"]){
				baliseFermante=(idChaine!=programmes[i].chaine["@id"] && idChaine!=0)?'</div>':'';
				idChaine=programmes[i].chaine["@id"];
				$('#now').append(
					baliseFermante+
					'<div class="row chaine media" id="now-'+programmes[i].chaine["@id"]+'">'+
						'<a class="pull-left" href="chaine.html?id='+programmes[i].chaine["@id"]+'">'+
							'<img class="media-object img-responsive" src="img/'+programmes[i].chaine["@id"]+'.gif"'+
						'</a>'
				);
				compteurProgramme=0;
			}
			attributDIV = (compteurProgramme==0 ? 'col-lg-4 col-md-4' : 
						(compteurProgramme==1 ? 'col-lg-4 col-md-4 visible-md visible-lg' : 
												'col-lg-3 col-md-3 visible-md visible-lg'));
			
			description=(programmes[i].description==undefined)?'Aucune description':programmes[i].description.substr(0, 200)+"...";
			$('#now-'+programmes[i].chaine["@id"]).append(
				'<div class="media-body '+attributDIV+' id="'+programmes[i]["@id"]+'">'+
					'<a href="programme.html?id='+programmes[i]["@id"]+'">'+
						'<h4 class="media-heading">'+programmes[i].nom+'</h4>'+
						'<p>'+programmes[i].heureDebut+' - '+programmes[i].categorie+'</p>'+
						'<p class="hidden-xs">'+description+'</p>'+
					'</a>'+
				'</div>'
			);
			compteurProgramme++;
		}
		$('#now').append('</div>');
	});
}

function majOngletDuSoir() {
	outils.ajaxRequest(outils.url+'/programmes/soir',function(json) {
		programmes=json.programme;
		var idChaine=0;
		var attributDIV='';
		var description='';
		var baliseFermante='';
		$('#tonight').html('');
		for(var i=0;i<programmes.length;i++){
			if(idChaine!=programmes[i].chaine["@id"]){
				baliseFermante=(idChaine!=programmes[i].chaine["@id"] && idChaine!=0)?'</div>':'';
				idChaine=programmes[i].chaine["@id"];
				$('#tonight').append(
					baliseFermante+
					'<div class="row chaine media" id="tonight-'+programmes[i].chaine["@id"]+'">'+
						'<a class="pull-left" href="chaine.html?id='+programmes[i].chaine["@id"]+'">'+
							'<img class="media-object img-responsive" src="img/'+programmes[i].chaine["@id"]+'.gif"'+
						'</a>'
				);
			}
			attributDIV=((programmes[i+1]!=undefined && idChaine!=programmes[i+1].chaine["@id"]) || (i==programmes.length-1))?'col-lg-offset-1 visible-md visible-lg':'';
			description=(programmes[i].description==undefined)?'Aucune description':programmes[i].description.substr(0, 200)+"...";
			$('#tonight-'+programmes[i].chaine["@id"]).append(
				'<div class="media-body '+attributDIV+' col-lg-5 col-md-5" id="'+programmes[i]["@id"]+'">'+
					'<a href="programme.html?id='+programmes[i]["@id"]+'">'+
						'<h4 class="media-heading">'+programmes[i].nom+'</h4>'+
						'<p>'+programmes[i].heureDebut+' - '+programmes[i].categorie+'</p>'+
						'<p class="hidden-xs">'+description+'</p>'+
					'</a>'+
				'</div>'
			);
		}
		$('#tonight').append('</div>');
	});
}