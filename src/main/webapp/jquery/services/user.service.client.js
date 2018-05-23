function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findAllUsersSearch = findAllUsersSearch;
    this.deleteUser = deleteUser;
    this.findUserById = findUserById;
    this.updateUser = updateUser;
    this.login = login;
    this.logout = logout;
    this.register = register;
    this.getUserAttribute = getUserAttribute;
    this.url =
        '/api/user';
    this.loginUrl =
        '/api/login';
    this.logoutUrl = 
    	'/api/logout';
    this.registerUrl =
    	'/api/register';
    this.sessionUrl = 
    	'/api/profile';
    var self = this;
    
    function login(username, password) {
        return fetch(self.loginUrl, {
            method: 'post',
            body: JSON.stringify({username:username, password: password}),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) {
			return response.json();
		});
    }
    
    function logout() {
    	return fetch(self.logoutUrl, {
    		method: 'post',
    	}).then(function (response) {
    		return response;
    	})
    }
    
    function deleteUser(userId) {
    	return fetch(self.url + '/' + userId, {
    		method: 'delete'
    	});
    }
    
    function findAllUsers() {
    	return fetch(self.url)
    		.then(function (response) {
    			return response.json();
    		});
    }
    
  function findAllUsersSearch(user) {
	return fetch(self.url + '/search', {
		method: 'post',
		body: JSON.stringify(user),
		headers: {
			'content-type': 'application/json'
		}
	})
		.then(function (response) {
			return response.json();
		});
}
    
    function createUser(user, callback) {
    	return fetch(self.url, {
			method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
		});
    }
    
    function findUserById(userId) {
    	return fetch(self.url + '/' + userId)
    		.then(function(response) {
    			return response.json();
    		});
    }
  
    function updateUser(userId, user) {
    	return fetch(self.url + '/' + userId, {
			method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
    	})
		.then(function(response) {
			if (response.bodyUsed) {
				return response.json();
			} else {
				return null;
			}
		});
    }
    
    function register(user) {
    	return fetch(self.registerUrl, {
    		method: 'post',
    		body: JSON.stringify(user),
    		headers: {
    			'content-type': 'application/json'
    		}
    	})
    } 
    
    function getUserAttribute() {
    	return fetch(self.sessionUrl, {
    		method: 'get',
    		headers: {
    			credentials: 'same-origin'
    		}
    	})
    	.then(function(response) {
			return response.json();
		});
    }

}

