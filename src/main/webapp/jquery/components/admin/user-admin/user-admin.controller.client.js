(function hello() {
	
	$(main); //declare listener on document to wait until load
	
	function main() {
		var h1 = $('h1');
		h1.css('color', 'red');
		h1.html('User Administration');
		
		var tr = $('.template');
		
		var users = [
			{username: 'bob'},
			{username: 'charlie'}
		]
		
		var tbody = $('tbody');
		for (var i=0; i < users.length; i++) {
			var user = users[i];
			console.log(user);
			var clone = tr.clone();
			clone.find('.username').html(user.username);
			tbody.append(clone);
		}
		
	}

})();