(function hello() {
	
	
	$(init);
	
	var $emailFld, $usernameFld;
	var $firstNameFld, $lastNameFld;
	var $phoneFld, $roleFld, $dateOfBirth;
	var $updateBtn, $logoutBtn;
	var userService = new UserServiceClient();
	
	function init() {
		$emailFld = $("#emailFld");
		$firstNameFld = $("#firstNameFld");
		$lastNameFld = $("#lastNameFld");
		$phoneFld = $("#phoneFld");
		$roleFld = $("#roleFld");
		$dateOfBirth = $("#dateOfBirthFld");
		$updateBtn = $("#updateBtn").click(updateUser);
		$logoutBtn = $("#logoutBtn").click(logout);
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
		$phoneFld.val(user.phone);
		$roleFld.val(user.role);
		$dateOfBirth.val(user.dateOfBirth);
	}
	
	function updateUser() {
		var user = {
				username: $usernameFld.val(),
				firstName: $firstNameFld.val(),
				lastName: $lastNameFld.val(),
				email: $emailFld.val(),
				phone: $phoneFld.val(),
				role: $roleFld.val(),
				dateOfBirth: $dateOfBirth.val()
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
	
	function logout() {
		userService.logout().then(redirect);
	}
	
	function redirect() {
		window.location = "/jquery/components/login/login.template.client.html";
	}
	
})();