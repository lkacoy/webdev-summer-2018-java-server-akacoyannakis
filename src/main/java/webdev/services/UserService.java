package webdev.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.User;
import webdev.repositories.UserRepository;

@RestController
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		repository.deleteById(id);
	}
	
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return repository.save(user);
	}
	
	@PostMapping("/api/login")
	public List<User> login(@RequestBody User user, HttpSession session) {
		return (List<User>) repository.findUserByCredentials(user.getUsername(), user.getPassword());
	}
	
	@PostMapping("/api/register")
	public User register(@RequestBody User user, HttpSession session) {
		User checkUser = findUserByUsername(user.getUsername());
		if (checkUser == null) {
			repository.save(user);
			session.setAttribute("user", user);
			return user;
		}
		//should throw error if user does exist
		return null;
	}
	
	@PostMapping("/api/logout")
	public User logout(HttpSession session) {
        if (session.getAttribute("user") != null){
            session.removeAttribute("user");
        }
        return null;
	}

	
	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>) repository.findAll();
	}
	
	@GetMapping(value="/api/user", params="userId")
	public User findUserById(@RequestParam("userId") int userId) {
		Optional<User> data = repository.findById(userId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping(value="/api/user", params="username")
	public User findUserByUsername(@RequestParam("username") String username) {
		Iterable<User> results = repository.findUserByUsername(username);
		if (results.iterator().hasNext()) {
			return results.iterator().next();
		}
		return null;
	}
	
	@PutMapping("api/user/{userId}") 
	public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser) {
		Optional<User> data = repository.findById(userId);
		if (data.isPresent()) {
			User user = data.get();
			user.setFirstName(newUser.getFirstName());
			repository.save(user);
			return user;
		}
		return null;
	}
	
	@PutMapping("/api/profile")
	public User updateProfile(@RequestBody User user, HttpSession session) {
		User currentUser = (User) session.getAttribute("user");
		Optional<User> data = repository.findById(currentUser.getId());
		
		if (data.isPresent()) {
			currentUser = data.get();
			currentUser.setEmail(user.getEmail());
			currentUser.setPhone(user.getPhone());
			currentUser.setDateOfBirth(user.getDateOfBirth());
			currentUser.setRole(user.getRole());
			return currentUser;
		}
		return null;
	}

}
