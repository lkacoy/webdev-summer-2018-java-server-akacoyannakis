package webdev.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	@PostMapping("/api/lesson/{lessonId}/widget")
	public void saveWidgetToLesson(@PathVariable("lessonId") int lessonId, @RequestBody List<Widget> widgets) {
		Optional<Lesson> optionalLesson = lessonRepository.findById(lessonId);
		if (optionalLesson.isPresent()) {
			
			//check if existing widgets exist for lesson
			Optional<Widget> optionalWidget = widgetRepository.findById(lessonId);
			if (optionalWidget.isPresent()) {
				widgetRepository.deleteById(lessonId);
			}
			
			//add widgets
			for(Widget widget: widgets) {
				widget.setLesson(optionalLesson.get());
				widgetRepository.save(widget);
			}
		}
	}
	
	//PUT for /api/widget/:widgetId
	@PutMapping("/api/widget/{widgetId}")
	public Widget updateWidget(@PathVariable("widgetId") int widgetId, @RequestBody Widget newWidget) {
		Optional<Widget> optionalWidget = widgetRepository.findById(widgetId);
		if (optionalWidget.isPresent()) {
			Widget widget = optionalWidget.get();
			widget.setId(newWidget.getId());
			widget.setHeight(newWidget.getHeight());
			widget.setLesson(newWidget.getLesson());
			widget.setName(newWidget.getName());
			widget.setOrder(newWidget.getOrder());
			widget.setStyle(newWidget.getStyle());
			widget.setText(newWidget.getText());
			widget.setWidgetType(newWidget.getWidgetType());
			widget.setWidth(newWidget.getWidth());
			
			//will need to check for subclasses
			
			return widget;
		}
		return null;
	}
	
	
	@DeleteMapping("/api/widget/{widgetId}")
	public void deleteWidgetById(@PathVariable("widgetId") int widgetId) {
		widgetRepository.deleteById(widgetId);
	}
}
