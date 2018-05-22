(function() {
	$(init);
	
	var $emailFld;
	var $firstNameFld;
	var $lastNameFld;
	var $updateBtn;
	var userService = new UserServiceClient();
	
	function init() {
		$emailFld = $("#emailFld");
		$firstNameFld = $("#firstNameFld");
		$lastNameFld = $("#lastNameFld");
		$updateBtn = $("#updateBtn").click(updateUser);
		findUserById(12);
	}
	
	function findUserById(userId) {
		userService.findUserById(userId).then(renderUser);
	}
	
	function renderUser(user) {
		console.log(user);
		$emailFld.val(user.email);
		$firstNameFld.val(user.firstName);
		$lastNameFld.val(user.lastName);
	}
	
	function updateUser() {
		var user = {
				firstName: $firstName.val(),
				lastName: $lastName.val()
		};
		
		userService.updateUser(12, user).then(success);
	}
	
	function success(response) {
		if (response === null) {
			alert('unable to update');
		} else {
			alert('success');		
		}
	}
})();