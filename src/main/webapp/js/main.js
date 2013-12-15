var index = {
    init: function(){
		tools.ajaxRequest(tools.url+'/programmes/soir',{},function(json){
			data=json.programme;
			var i=0;
			while(data[i]!= undefined){
				if(data[i].description == undefined) data[i].description= 'Aucune description';
				if(data[i+1].description == undefined) data[i+1].description= 'Aucune description';
				$('#tonight').append(
					'<div class="row chaine media">'+				
						'<a class="pull-left" href="chaine.html">'+				
							'<img class="media-object img-responsive" src="img/'+data[i].chaine["@id"]+'.gif"'+				
						'</a>'+				
						'<div class="media-body col-lg-5 col-md-5" id="'+data[i]["@id"]+'">'+				
							'<a href="programme.html?id='+data[i]["@id"]+'">'+				
								'<h4 class="media-heading">'+data[i].nom+'</h4>'+				
								'<p>'+data[i].heureDebut+' - '+data[i].categorie+'</p>'+				
								'<p class="hidden-xs">'+data[i].description+'</p>'+				
							'</a>'+				
						'</div>'+				
						'<div class="media-body col-lg-offset-1 col-lg-5 col-md-5 visible-md visible-lg" id="'+data[i+1]["@id"]+'">'+				
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
		});
    }
}

$(document).ready(function(){ index.init(); });

$('#onglets a').click(function (e) {
	e.preventDefault();
	$(this).tab('show');
})

