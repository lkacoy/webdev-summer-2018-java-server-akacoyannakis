(function hello() {
    var $usernameFld, $passwordFld, $verifyPasswordFld;
    var $registerBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
    	
    	$registerBtn = $('#registerBtn');
    	$registerBtn.click(register);
    }
    
    function register() {
    	console.log("hitting register");
    	$usernameFld = $("#usernameFld").val();
    	$passwordFld = $("#passwordFld").val();
    	$verifyPasswordFld = $("#verifyPasswordFld").val();
    	
    	if ($passwordFld === $verifyPasswordFld) {
    		var user = {
    				username: $usernameFld,
    				password: $passwordFld
    		}
    		userService.register(user);
    	} else {
    		//display error
    	}
    }
})();
