package webdev.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MULTIPLE_CHOICE_QUESTION")
public class MultipleChoiceQuestion extends BaseExamQuestion {
	@Column(name="OPTIONS", nullable=false)
	private List<String> options;
	@Column(name="CORRECT_OPTION", nullable=false)
	private int correctOption;
	public List<String> getOptions() {
		return options;
	}
	public void setOptions(List<String> options) {
		this.options = options;
	}
	public int getCorrectOption() {
		return correctOption;
	}
	public void setCorrectOption(int correctOption) {
		this.correctOption = correctOption;
	}
}