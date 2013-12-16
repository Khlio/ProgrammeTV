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

function majOngletEnCeMoment(){
	/*outils.ajaxRequest(outils.url+'/programmes/moment',function(json){
		data=json.programme;
		var i=0;
		while(data[i]!= undefined){
			if(data[i].description == undefined) data[i].description= 'Aucune description';
			if(data[i+1].description == undefined) data[i+1].description= 'Aucune description';
			$('#tonight').append(
				'<div class="row chaine media" id="'+data[i].chaine["@id"]+'">'+				
					'<a class="pull-left" href="chaine.html?id='+data[i].chaine["@id"]+'">'+				
						'<img class="media-object img-responsive" src="img/'+data[i].chaine["@id"]+'.gif"'+				
					'</a>'+				
					'<div class="media-body col-lg-4 col-md-4" id="'+data[i]["@id"]+'">'+				
						'<a href="programme.html?id='+data[i]["@id"]+'">'+				
							'<h4 class="media-heading">'+data[i].nom+'</h4>'+				
							'<p>'+data[i].heureDebut+' - '+data[i].categorie+'</p>'+				
							'<p class="hidden-xs">'+data[i].description+'</p>'+				
						'</a>'+				
					'</div>'+				
					'<div class="media-body col-lg-4 col-md-4 visible-md visible-lg" id="'+data[i+1]["@id"]+'">'+				
						'<a href="programme.html?id='+data[i+1]["@id"]+'">'+				
							'<h4 class="media-heading">'+data[i+1].nom+'</h4>'+				
							'<p>'+data[i+1].heureDebut+' - '+data[i+1].categorie+'</p>'+				
							'<p>'+data[i+1].description+'</p>'+				
						'</a>'+				
					'</div>'+
					'<div class="media-body col-lg-3 col-md-3 visible-md visible-lg" id="'+data[i+1]["@id"]+'">'+				
						'<a href="programme.html?id='+data[i+1]["@id"]+'">'+				
							'<h4 class="media-heading">'+data[i+1].nom+'</h4>'+				
							'<p>'+data[i+1].heureDebut+' - '+data[i+1].categorie+'</p>'+				
							'<p>'+data[i+1].description+'</p>'+				
						'</a>'+				
					'</div>'+
				'</div>'			
			);
			i+=2;
		}
	});*/
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
					'<div class="row chaine media" id="'+programmes[i].chaine["@id"]+'">'+
						'<a class="pull-left" href="chaine.html?id='+programmes[i].chaine["@id"]+'">'+
							'<img class="media-object img-responsive" src="img/'+programmes[i].chaine["@id"]+'.gif"'+
						'</a>'
				);
			}
			attributDIV=((programmes[i+1]!=undefined && idChaine!=programmes[i+1].chaine["@id"]) || (i==programmes.length-1))?'col-lg-offset-1 visible-md visible-lg':'';
			description=(programmes[i].description==undefined)?'Aucune description':programmes[i].description;
			$('#'+programmes[i].chaine["@id"]).append(
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