package webdev.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Assignment;
import webdev.models.Lesson;
import webdev.repositories.AssignmentRepository;
import webdev.repositories.LessonRepository;

@RestController
@CrossOrigin(origins = "*")
public class AssignmentService {

	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	AssignmentRepository assignmentRepository;
	
	@GetMapping("/api/assignment")
	public Iterable<Assignment> findAllAssignments() {
		return assignmentRepository.findAll();
	}
	
	@GetMapping("/api/assignment/{aid}")
	public Assignment findAssignmentById(@PathVariable("aid") int assignmentId) {
		Optional<Assignment> optional = assignmentRepository.findById(assignmentId);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	@GetMapping("/api/lesson/{lid}/assignment")
	public List<Assignment> findAssignmentsByLessonId(@PathVariable("lid") int lessonId) {
		Optional<Lesson> optional = lessonRepository.findById(lessonId);
		if (optional.isPresent()) {
			Lesson lesson = optional.get();
			return lesson.getAssignments();
		}
		return null;
	}
}
