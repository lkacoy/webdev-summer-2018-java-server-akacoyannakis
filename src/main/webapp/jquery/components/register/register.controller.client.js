(function hello() {
    var $usernameFld, $passwordFld, $verifyPasswordFld;
    var $registerBtn;
    var userService = new UserService();
    $(main);

    function main() {
    	
    	$registerBtn = $('#registerBtn');
    	$registerBtn.click(register);
    }
    
    function register() {
    	console.log("hitting register");
    }
})();
