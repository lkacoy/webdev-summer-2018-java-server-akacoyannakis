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

import webdev.models.Lesson;
import webdev.models.Widget;
import webdev.repositories.LessonRepository;
import webdev.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins="*")
public class WidgetService {
	
	@Autowired
	WidgetRepository widgetRepository;
	@Autowired
	LessonRepository lessonRepository;
	
	@GetMapping("/api/lesson/{lessonId}/widget")
	public List<Widget> findAllWidgetsForLesson(@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> optionalLesson = lessonRepository.findById(lessonId);
		if(optionalLesson.isPresent()) {
			Lesson lesson = optionalLesson.get();
			return lesson.getWidgets();
		}
		return null;
	}
	
	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets() {
		return (List<Widget>) widgetRepository.findAll();
	}
	
	@GetMapping("/api/widget/{widgetId}")
	public Widget findWidgetById(@PathVariable("widgetId") int widgetId) {
		Optional<Widget> optionalWidget = widgetRepository.findById(widgetId);
		if (optionalWidget.isPresent()) {
			Widget widget = optionalWidget.get();
			return widget;
		}
		return null;
	}
	
	
	@PostMapping("/api/widget/save")
	public void saveAllWidgets(@RequestBody
			List<Widget> widgets) {
		widgetRepository.deleteAll();
		for(Widget widget: widgets) {
			widgetRepository.save(widget);
		}
	}
	
	//POST for /api/lesson/:lessonId/widget
	
	//PUT for /api/widget/:widgetId
	
	@DeleteMapping("/api/widget/{widgetId}")
	public void deleteWidgetById(@PathVariable("widgetId") int widgetId) {
		widgetRepository.deleteById(widgetId);
	}
}
