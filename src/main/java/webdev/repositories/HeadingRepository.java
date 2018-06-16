package webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import webdev.models.Heading;

public interface HeadingRepository extends CrudRepository<Heading, Integer> {

}
