package base.Model.baza1;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.envers.Audited;

@Audited
@Entity
public class PatientComment extends AbstractComment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PatientComment() {
		
	}

	public PatientComment(String comment) {
		super(comment);
	}

}
