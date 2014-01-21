var outils = {
	url: 'rest',
    ajaxRequest: function(url, callback) {
        $.ajax({
            type: "GET",
            dataType: 'json',
            url: url,
            success: function(donnees) {
                if (callback != undefined) {
                    callback(donnees);
            	}
            }
        });
    },
	
	carousel : function () {
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
			treshold: 0
		});
	}
};

outils.ajaxRequest(outils.url + '/chaines', function(json) {
	if (json == undefined) {
		$('#selectionChaine').append('<li><a href="javascript:;">N/A</a></li>');
	} else {
		var chaines = json.chaine;
		for (var i = 0; i < chaines.length; i++) {
			$('#selectionChaine').append('<li><a href="chaine.html?id=' + chaines[i]["@id"] + '">' + chaines[i].nom + '</a></li>');
		}
	}
});