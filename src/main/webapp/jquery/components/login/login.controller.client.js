(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn, $registerBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
    	
    	$loginBtn = $('#login');
    	$registerBtn = $('#registerBtn');
    	$loginBtn.click(login);
    	$registerBtn.click(register);
    	
    }
    
    function login() {
    	
    	$usernameFld = $("#username").val();
    	$passwordFld = $("#password").val();
    	userService.login($usernameFld, $passwordFld).then(handleLogonResult);
    }
    
    function handleLogonResult(user) {
    	var username = user.username;
    	var password = user.password;
    	
    	if (username === $usernameFld && password === $passwordFld) {
    		//success and go to profile
    		
    		window.location = "/jquery/components/profile/profile.template.client.html?userId="+user.id;
    	} else {
    		//error
    		console.debug("Username and Password do not match");
    	}
    }
    
    function register() {
    	window.location = "/jquery/components/register/register.template.client.html";
    }
    
})();
