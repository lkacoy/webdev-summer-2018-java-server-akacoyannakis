package webdev.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Exam;
import webdev.models.MultipleChoiceQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.MultipleChoicesQuestionRepository;

@RestController
@CrossOrigin(origins = "*")
public class MultipleChoiceQuestionService {

	@Autowired
	ExamRepository examRepository;

	@Autowired
	MultipleChoicesQuestionRepository multipleChoiceRepository;
	
	@PostMapping("/api/exam/{eid}/choice")
	public MultipleChoiceQuestion createMultipleChoiceQuestion(@PathVariable("eid") int examId,
			@RequestBody MultipleChoiceQuestion newMultipleChoiceQuestion) {
		Optional<Exam> optional = examRepository.findById(examId);
		if (optional.isPresent()) {
			Exam exam = optional.get();
			newMultipleChoiceQuestion.setExam(exam);
			return multipleChoiceRepository.save(newMultipleChoiceQuestion);
		}
		return null;
	}
	
	@GetMapping("/api/multi/{questionId}")
	public MultipleChoiceQuestion findMultiQuestionById(@PathVariable("questionId") int questionId) {
		Optional<MultipleChoiceQuestion> optional = multipleChoiceRepository.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

}
