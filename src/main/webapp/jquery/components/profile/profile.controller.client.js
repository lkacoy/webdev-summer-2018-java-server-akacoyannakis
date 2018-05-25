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
		$usernameFld = $('#usernameFld');
		$updateBtn = $("#updateBtn").click(updateUser);
		$logoutBtn = $("#logoutBtn").click(logout);
		
		var url = window.location.search.substring(1);
		var userId = url.substr(url.indexOf('=')+1); 
		
		//var user = getUserAttribute(); session is still not working correctly
		findUserById(userId);
	}
	
	function findUserById(userId) {
		userService.findUserById(userId).then(renderUser);
	}
	
	function renderUser(user) {
		console.log(user);
		$usernameFld.val(user.username);
		$emailFld.val(user.email);
		$firstNameFld.val(user.firstName);
		$lastNameFld.val(user.lastName);
		$phoneFld.val(user.phone);
		$roleFld.val(user.role);
		
		//calculate date in the proper format for datepicker
		var date = new Date(user.dateOfBirth);
		var month = date.getMonth() + 1;
		if (month < 10) {
			month = '0' + month;
		}
		var newDate = date.getFullYear()+"-"+month+"-"+date.getDate();
		
		$dateOfBirth.val(newDate);
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
		$(location).attr('href', '/jquery/components/login/login.template.client.html');
	}
	
	function getUserAttribute() {
		userService.getUserAttribute().then(handleUserAttribute);
	}
	
	function handleUserAttribute(response) {
		if (response.ok) {
			console.log(response);
		}
	}
	
})();