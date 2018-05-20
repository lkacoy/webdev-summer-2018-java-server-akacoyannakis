(function hello() {

	$(main); //declare listener on document to wait until load
	var tbody;
	var template;

	function main() {

		tbody = $('tbody');
		template = $('.template');
		$('#createUser').click(createUser);
		
		var promise = fetch('http://localhost:8080/api/user');
		var users = promise.then(function(response) {
			return response.json();
		}).then(renderUsers);
	}
	
	function createUser() {
		var username = $('#usernameFld').val();
		var password = $('#passwordFld').val();
		var firstName = $('#firstNameFld').val();
		var lastName = $('#lastNameFld').val();
		
		var user = {
				username: username,
				password: password,
				firstName: firstName,
				lastName: lastName
		};
		
		fetch('http://localhost:8080/api/user', {
			method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
		});
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