package webdev.models;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;

@Entity
@DiscriminatorColumn(name="widget_type")
public class Paragraph extends Widget {

}
