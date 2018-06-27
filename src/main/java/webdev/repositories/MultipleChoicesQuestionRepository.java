package webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import webdev.models.MultipleChoiceQuestion;
import webdev.models.TrueFalseExamQuestion;

public interface MultipleChoicesQuestionRepository
	extends CrudRepository<MultipleChoiceQuestion, Integer> {
	
}