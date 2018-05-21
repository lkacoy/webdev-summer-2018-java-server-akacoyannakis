(function hello() {

	$(main); //declare listener on document to wait until load
	var $userRowTemplate, $tbody;
	var $usernameFld, $passwordFld;
    var $removeBtn, $editBtn, $createBtn;
    var $firstNameFld, $lastNameFld;
	var userService = new UserServiceClient();

	function main() {

		$tbody = $('tbody');
		$userRowTemplate = $('#template');
		$('#createUser').click(createUser);
		
		findAllUsers();
	}
	
	function findAllUsers() {
		userService.findAllUsers().then(renderUsers);
	}
	
	function createUser() {
		var username = $usernameFld.val();
		var password = $passwordFld.val();
		var firstName = $firstNameFld.val();
		var lastName = $lastNameFld.val();
		
		var user = {
				username: username,
				password: password,
				firstName: firstName,
				lastName: lastName
		};
		
		userService.createUser(user).then(findAllUsers);
	}

	function renderUsers(users) {
		$tbody.empty();
		for (var i = 0; i < users.length; i++) {
			var user = users[i];
			var clone = $userRowTemplate.clone();
			
			clone.attr('id', user.id);
			clone.find('#wbdv-remove').click(deleteUser);
			clone.find('#wbdv-edit').click(editUser);
			clone.find('.wbdv-username')
				.html(user.username);
			$tbody.append(clone);
		}
	}
	
	function renderUser(user) {
		
	}
	
	function deleteUser(event) {
		var deleteBtn = $(event.currentTarget);
		var userId = deleteBtn.parent().parent().parent().attr('id'); 
		userService.deleteUser(userId).then(findAllUsers);
	}
	
	function editUser(event) {
		console.log(event);
	}
	
	function findUserById() {
		userService.findUserById().then(renderUser);
	}
	
	function updateUser() {
		
	}

})();