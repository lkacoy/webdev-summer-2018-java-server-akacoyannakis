package webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import webdev.models.Paragraph;

public interface ParagraphRepository extends CrudRepository<Paragraph, Integer>{

}
