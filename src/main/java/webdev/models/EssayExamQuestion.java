package webdev.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ESSAY_QUESTION")
public class EssayExamQuestion extends BaseExamQuestion {

	@Column(name="ESSAY")
	String essay;

	public String getEssay() {
		return essay;
	}

	public void setEssay(String essay) {
		this.essay = essay;
	}
	
	
}
