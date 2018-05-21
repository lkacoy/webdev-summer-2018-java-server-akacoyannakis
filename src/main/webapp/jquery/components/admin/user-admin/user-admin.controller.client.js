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
		$usernameFld = $('#usernameFld').val();
		$passwordFld = $('#passwordFld').val();
		$firstNameFld = $('#firstNameFld').val();
		$lastNameFld = $('#lastNameFld').val();

		var user = {
			username : $usernameFld,
			password : $passwordFld,
			firstName : $firstNameFld,
			lastName : $lastNameFld
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
			clone.find('.wbdv-username').html(user.username);
			clone.find('.wbdv-first-name').html(user.firstName);
			clone.find('.wbdv-last-name').html(user.lastName);
			clone.find('.wbdv-role').html(user.role);
			$tbody.append(clone);
		}
	}

	function renderUser(user) {

	}

	function deleteUser(event) {
		$deleteBtn = $(event.currentTarget);
		var userId = $deleteBtn.parent().parent().parent().attr('id');
		userService.deleteUser(userId).then(findAllUsers);
	}

	function editUser(event) {
		$editBtn = $(event.currentTarget);

	}

	function findUserById() {
		userService.findUserById().then(renderUser);
	}

	function updateUser() {
		
	}

})();