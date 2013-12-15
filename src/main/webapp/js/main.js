$('#onglets a').click(function (e) {
	e.preventDefault();
	$(this).tab('show');
});

$(document).ready(
	function() {
		$.getJSON("http://localhost:8080/webapp/chaines/liste", function(data) {
			console.log(data);
		});
	}
);