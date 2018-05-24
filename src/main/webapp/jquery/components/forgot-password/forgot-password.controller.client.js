(function hello() {
	
	var $emailFld;
	var userService = new UserServiceClient();
	$(main);
	
	function main() {
		$emailFld = $('#emailFld').val();
		$('#sendEmail').click(sendForgotPassword);
	}
	
	function sendForgotPassword() {
		console.log("sending email");
		//userService.sendForgotPassword().then(notifyResult);
	}
	
	function notifyResult(response) {
		if (response.ok) {
			alert("Email sent");
		} else {
			alert("There was an error processing your request.");
		}
		
	}
	
	
})();