(function hello() {

	$(main); //declare listener on document to wait until load
	var tbody;
	var template;

	function main() {

		tbody = $('tbody');
		template = $('.template');
		var promise = fetch('http://localhost:8080/api/user');
		var users = promise.then(function(response) {
			return response.json();
		}).then(renderUsers);
	}

	function renderUsers(users) {

		for (var i = 0; i < users.length; i++) {
			var user = users[i];
			var clone = template.clone();
			clone.find('.username').html(user.username);
			tbody.append(clone);
		}
	}

})();