package webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import webdev.models.BaseExamQuestion;

public interface BaseQuestionRepository extends CrudRepository<BaseExamQuestion, Integer> {

}
