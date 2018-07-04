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
import webdev.models.FillInTheBlanksExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.FillInTheBlanksQuestionRepository;

@RestController
@CrossOrigin(origins = "*")
public class FillInBlanksService {
	
	@Autowired
	ExamRepository examRepository;
	@Autowired
	FillInTheBlanksQuestionRepository fillInBlanksRepository;
	
	@PostMapping("/api/exam/{eid}/blanks")
	public FillInTheBlanksExamQuestion createFillInBlanksQuestion(@PathVariable("eid") int examId,
			@RequestBody FillInTheBlanksExamQuestion newFillInBlanksQuestion) {
		Optional<Exam> optional = examRepository.findById(examId);
		if (optional.isPresent()) {
			Exam exam = optional.get();
			newFillInBlanksQuestion.setExam(exam);
			return fillInBlanksRepository.save(newFillInBlanksQuestion);
		}
		return null;
	}
	
	
	@GetMapping("/api/fillInBlanks/{questionId}") 
	public FillInTheBlanksExamQuestion findFillInBlanksQuestionById(@PathVariable("questionId") int questionId) {
		Optional<FillInTheBlanksExamQuestion> optional = fillInBlanksRepository.findById(questionId);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

}
