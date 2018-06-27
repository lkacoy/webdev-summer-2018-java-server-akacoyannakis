package webdev.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Exam;
import webdev.models.FillInTheBlanksExamQuestion;
import webdev.models.Lesson;
import webdev.models.MultipleChoiceQuestion;
import webdev.models.BaseExamQuestion;
import webdev.models.EssayExamQuestion;
import webdev.models.TrueFalseExamQuestion;
import webdev.models.Widget;
import webdev.repositories.EssayQuestionRepository;
import webdev.repositories.ExamRepository;
import webdev.repositories.FillInTheBlanksQuestionRepository;
import webdev.repositories.LessonRepository;
import webdev.repositories.MultipleChoicesQuestionRepository;
import webdev.repositories.TrueFalseQuestionRepository;
import webdev.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*")
public class ExamService {
	@Autowired
	ExamRepository examRepository;
	@Autowired
	TrueFalseQuestionRepository trueFalseRepository;
	@Autowired
	MultipleChoicesQuestionRepository multipleChoiceRepository;
	@Autowired
	EssayQuestionRepository essayRepository;
	@Autowired
	FillInTheBlanksQuestionRepository fillInBlanksRepository;
	@Autowired
	LessonRepository lessonRepository;
	
	@GetMapping("/api/exam")
	public Iterable<Exam> findAllExams() {
		return examRepository.findAll();
	}

	@GetMapping("/api/exam/{eid}")
	public Exam findExamById(@PathVariable("eid") int examId) {
		Optional<Exam> optional = examRepository.findById(examId);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	@GetMapping("api/lesson/{lid}/exam")
	public List<Exam> findExamsByLessonId(@PathVariable("lid") int lessonId) {
		Optional<Lesson> optional = lessonRepository.findById(lessonId);
		if (optional.isPresent()) {
			Lesson lesson = optional.get();
			return lesson.getExams();
		}
		
		return null;
	}
	
	@PostMapping("/api/lesson/{lid}/exam")
	public Exam createExam(@PathVariable("lid") int lessonId,
			@RequestBody Exam newExam) {
		Optional<Lesson> optional = lessonRepository.findById(lessonId);
		if (optional.isPresent()) {
			Lesson lesson = optional.get();
			newExam.setLesson(lesson);
			return examRepository.save(newExam);
		}
		return null;
	}
	
	@DeleteMapping("/api/exam/{eid}")
	public void deleteExam(@PathVariable("eid") int examId) {
		examRepository.deleteById(examId);
	}
	
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
	
	@GetMapping("/api/multi/{questionId}")
	public MultipleChoiceQuestion findMultiQuestionById(@PathVariable("questionId") int questionId) {
		Optional<MultipleChoiceQuestion> optional = multipleChoiceRepository.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
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
	
	@GetMapping("/api/exam/{examId}/question")
	public List<BaseExamQuestion> findAllQuestionsForExam(@PathVariable("examId") int examId) {
		Optional<Exam> optionalExam = examRepository.findById(examId);
		if(optionalExam.isPresent()) {
			Exam exam = optionalExam.get();
			List<BaseExamQuestion> questions = exam.getQuestions();
			int count = questions.size();
			return questions;
		}
		return null;
	}
}