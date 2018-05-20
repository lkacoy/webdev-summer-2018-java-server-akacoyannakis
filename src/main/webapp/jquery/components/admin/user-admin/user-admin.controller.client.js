(function hello() {

	$(main); //declare listener on document to wait until load
	var tbody;
	var template;
	var userService = new UserServiceClient();

	function main() {

		tbody = $('tbody');
		template = $('.template');
		$('#createUser').click(createUser);
		
		findAllUsers();
	}
	
	function findAllUsers() {
		userService.findAllUsers().then(renderUsers);
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
		
		userService.createUser(user).then(findAllUsers);
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