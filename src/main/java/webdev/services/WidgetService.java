package webdev.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Widget;
import webdev.repositories.WidgetRepository;

@RestController
public class WidgetService {
	
	@Autowired
	WidgetRepository repository;
	
	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets() {
		return (List<Widget>) repository.findAll();
	}
}
