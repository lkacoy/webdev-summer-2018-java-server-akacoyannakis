package webdev.services;

import java.util.ArrayList;
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
	public User login(@RequestBody User user, HttpSession session) {
		List<User> users = (List<User>) repository.findUserByCredentials(user.getUsername(), user.getPassword());
		for (User currentUser : users) {
			if (currentUser.getUsername().equals(user.getUsername()) &&
					currentUser.getPassword().equals(user.getPassword())) {
				session.setAttribute("user", currentUser);
				return currentUser;
			}
		}
		return null;
	}

	@PostMapping("/api/register")
	public User register(@RequestBody User user, HttpSession session) {
		User checkUser = findUserByUsername(user.getUsername());
		if (checkUser == null) {
			createUser(user);
			session.setAttribute("user", user);
			return user;
		}
		// should throw error if user does exist
		return null;
	}

	@PostMapping("/api/logout")
	public User logout(HttpSession session) {
		if (session.getAttribute("user") != null) {
			session.removeAttribute("user");
			session.invalidate();
		}
		return null;
	}

	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>) repository.findAll();
	}
	
	@GetMapping("/api/session/set/{attr}/{value}")
	public String setSessionAttribute(@PathVariable("attr") String attr,
										@PathVariable("value") String value,
										HttpSession session) {
		session.setAttribute(attr, value);
		return attr +" = " + value;
	}
	
	@PostMapping("/api/user/search")
	public List<User> findAllUsersSearch(@RequestBody User user) {
		if (user == null) {
			return (List<User>) repository.findAll();
		}
		return (List<User>) repository.findUsersBySearch(user.getUsername(), user.getPassword(),
					user.getFirstName(), user.getLastName(), user.getRole());
		
	}

	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int userId) {
		Optional<User> data = repository.findById(userId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/profile")
	public User profile(HttpSession session) {
		User currentUser = (User) session.getAttribute("user");	
		return currentUser;
	}
	
	@PutMapping("api/user/{userId}")
	public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser) {
		Optional<User> data = repository.findById(userId);
		if (data.isPresent()) {
			User user = data.get();
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setEmail(newUser.getEmail());
			user.setPassword(newUser.getPassword());
			user.setPhone(newUser.getPhone());
			user.setRole(newUser.getRole());
			user.setDateOfBirth(newUser.getDateOfBirth());
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

	public User findUserByUsername(String username) {
		Iterable<User> results = repository.findUserByUsername(username);
		if (results.iterator().hasNext()) {
			return results.iterator().next();
		}
		return null;
	}

}
