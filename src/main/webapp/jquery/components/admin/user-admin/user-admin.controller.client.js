(function hello() {

	$(main); //declare listener on document to wait until load
	var $userRowTemplate, $tbody;
	var $usernameFld, $passwordFld;
	var $removeBtn, $editBtn, $createBtn, $updateBtn;
	var $firstNameFld, $lastNameFld, $roleFld;
	var userService = new UserServiceClient();

	function main() {

		$tbody = $('tbody');
		$userRowTemplate = $('#template');
		$('#createUser').click(createUser);
		$('#search').click(search);
		$('#updateUser').click(updateUser);

		findAllUsers();
	}
	
	function search() {
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
		
		findAllUsersSearch(user);
	}

	function findAllUsers() {
		userService.findAllUsers().then(renderUsers);
	}

	function findAllUsersSearch(user) {
		userService.findAllUsersSearch(user).then(renderUsers);
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
		$userRowTemplate.attr('id', user.id);
		$userRowTemplate.find(".wbdv-username").html(user.username);
		$userRowTemplate.find(".wbdv-first-name").html(user.username);
		$userRowTemplate.find(".wbdv-last-name").html(user.username);
	}

	function deleteUser(event) {
		$deleteBtn = $(event.currentTarget);
		var userId = $deleteBtn.parent().parent().parent().attr('id');
		userService.deleteUser(userId).then(findAllUsers);
	}

	function editUser(event) {
		$editBtn = $(event.currentTarget);
		var userId = $editBtn.parent().parent().parent().attr('id');
		userService.findUserById(userId).then(updateSearchRow);
//		$userRowTemplate = $editBtn.parent().parent().parent();
//		$userRowTemplate.prop('contenteditable', true);
	}

	function findUserById(userId) {
		userService.findUserById(userId).then(renderUser);
	}

	function updateUser() {
		console.log("update");
		var userId = $userRowTemplate.attr('id');
		
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
		
		
		userService.updateUser(userId, user).then(renderUser);
	}
	
	function updateSearchRow(user) {
		$('#usernameFld').val(user.username);
		$('#passwordFld').val(user.password);
		$('#firstNameFld').val(user.firstName);
		$('#lastNameFld').val(user.lastName);
		$('#roleFld').val(user.role);
	}

})();