package webdev.services;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Course;
import webdev.repositories.CourseRepository;

@RestController
@CrossOrigin(origins = "*", maxAge=3600)
public class CourseServices {
	
	@Autowired
	CourseRepository courseRepository;
	
	@GetMapping("/api/course")
	public Iterable<Course> findAllCourses() {
		return courseRepository.findAll(); 
	}
	
	@PostMapping("/api/course")
	public Course createCourse
	(@RequestBody Course course) {
			return courseRepository.save(course);
	}

	@DeleteMapping("/api/course/{courseId}")
	public void deleteCourse(@PathVariable("courseId") int id) {
		courseRepository.deleteById(id);
	}

	@PutMapping("/api/course")
	public Course updateCourse(@RequestBody Course course, HttpSession session) {
		Course currentCourse = (Course) session.getAttribute("course");
		Optional<Course> data = courseRepository.findById(currentCourse.getId());
		
		if (data.isPresent()) {
			currentCourse = data.get();
			currentCourse.setId(course.getId());
			currentCourse.setCreated(course.getCreated());
			currentCourse.setModified(course.getModified());
			currentCourse.setModules(course.getModules());
			currentCourse.setTitle(course.getTitle());
			currentCourse.setOwnedBy(course.getOwnedBy());
			
			return currentCourse;
		}
		return null;
	}
	
}
