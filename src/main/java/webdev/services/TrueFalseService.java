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
import webdev.models.TrueFalseExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.TrueFalseQuestionRepository;

@RestController
@CrossOrigin(origins = "*")
public class TrueFalseService {
	
	@Autowired
	ExamRepository examRepository;
	@Autowired
	TrueFalseQuestionRepository trueFalseRepository;


	@PostMapping("/api/exam/{eid}/truefalse")
	public TrueFalseExamQuestion createTrueFalseQuestion(@PathVariable("eid") int examId,
			@RequestBody TrueFalseExamQuestion newTrueFalseQuestion) {
		Optional<Exam> optional = examRepository.findById(examId);
		if (optional.isPresent()) {
			Exam exam = optional.get();
			newTrueFalseQuestion.setExam(exam);
			return trueFalseRepository.save(newTrueFalseQuestion);
		}
		return null;
	}
	
	
	@GetMapping("/api/truefalse/{questionId}")
	public TrueFalseExamQuestion findTrueFalseQuestionById(@PathVariable("questionId") int questionId) {
		Optional<TrueFalseExamQuestion> optional = trueFalseRepository.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
}
