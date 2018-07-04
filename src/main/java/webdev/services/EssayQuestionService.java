package webdev.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.EssayExamQuestion;
import webdev.models.Exam;
import webdev.repositories.EssayQuestionRepository;
import webdev.repositories.ExamRepository;

@RestController
@CrossOrigin(origins = "*")
public class EssayQuestionService {

	@Autowired
	ExamRepository examRepository;
	@Autowired
	EssayQuestionRepository essayRepository;
	
	@PostMapping("/api/exam/{eid}/essay")
	public EssayExamQuestion createEssayExamQuestion(@PathVariable("eid") int examId, 
			@RequestBody EssayExamQuestion newEssayQuestion) {
		Optional<Exam> optional = examRepository.findById(examId);
		if (optional.isPresent()) {
			Exam exam = optional.get();
			newEssayQuestion.setExam(exam);
			return essayRepository.save(newEssayQuestion);
		}
		return null;
	}
}
