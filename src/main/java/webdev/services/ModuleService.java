package webdev.services;

import java.util.List;
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
import webdev.models.Module;
import webdev.repositories.CourseRepository;
import webdev.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins="*", maxAge=3600)
public class ModuleService {
	
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	ModuleRepository moduleRepository;
	
	@PostMapping("/api/course/{courseId}/module")
	public Module createModule(@PathVariable("courseId") int courseId,
			@RequestBody Module newModule) {
		Optional<Course> data = courseRepository.findById(courseId);
		if(data.isPresent()) {
			Course course = data.get();
			newModule.setCourse(course);
			return moduleRepository.save(newModule);
		}
		return null;

	}
	
	@GetMapping("/api/course/{courseId}/module")
	public List<Module> findAllModulesForCourse(
			@PathVariable("courseId") int courseId) {
		Optional<Course> data = courseRepository.findById(courseId);
		if(data.isPresent()) {
			Course course = data.get();
			return course.getModules();
		}
		return null;		
	}
	
	@DeleteMapping("/api/course/{courseId}/module/{moduleId}")
	public void deleteModule(@PathVariable("courseId") int courseId,
						@PathVariable("moduleId") int moduleId) {
		moduleRepository.deleteById(moduleId);
	} 
	
	@PutMapping("/api/course/{courseId}/module/{moduleId}")
	public Module  updateModule(@RequestBody Module module, HttpSession session) {
		Module currentModule = (Module) session.getAttribute("module");
		Optional<Module> data = moduleRepository.findById(currentModule.getId());
		
		if (data.isPresent()) {
			currentModule = data.get();
			currentModule.setId(module.getId());
			currentModule.setTitle(module.getTitle());
			
			return currentModule;
		}
		return null;
	}

}
