package webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import webdev.models.TrueFalseExamQuestion;

public interface TrueFalseQuestionRepository
	extends CrudRepository<TrueFalseExamQuestion, Integer> {
	
}