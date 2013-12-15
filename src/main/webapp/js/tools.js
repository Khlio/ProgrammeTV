var tools = {
	url : 'http://programmetv.mope.eu.cloudbees.net/rest',
    ajaxRequest: function(url, postVars, callback){
        if(postVars==undefined) postVars={};
        postVars.isAjax=1;
        $.ajax({
            type: "GET",
            dataType: 'json',
            url: url,
            data: postVars,
            success: function(datas){
                if (callback != undefined)
                    callback(datas);
            }
        });
    },
	
	listing: function(json){
		data=json.chaine;
		console.log(data[0].nom);
		$('#tonight').html('<div><ul><li>tomate</li></ul></div>');
	}
}