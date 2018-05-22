(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
    	
    	$loginBtn = $('#login');
    	$loginBtn.click(login);
    	
    }
    
    function login() {
    	
    	$usernameFld = $("#username").val();
    	$passwordFld = $("#password").val();
    	userService.login($usernameFld, $passwordFld).then(handleLogonResult);
    }
    
    function handleLogonResult(user) {
    	var username = user[0].username;
    	var password = user[0].password;
    	
    	if (username === $usernameFld && password === $passwordFld) {
    		//success and go to profile
    		window.location = "/jquery/components/profile/profile.template.client.html";
    	} else {
    		//error
    	}
    }
    
})();
