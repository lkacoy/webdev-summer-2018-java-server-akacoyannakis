package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Assignment;
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
}
