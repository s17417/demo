package base.Model.baza1;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import base.Model.AbstractPersistentClasses.AbstractAuditableObject;

@MappedSuperclass
public abstract class AbstractComment extends AbstractAuditableObject<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Size(max = 4096)
	@Column(length = 4096)
	private String comment;
	
	
	protected AbstractComment() {	
		
	}
	
	protected AbstractComment(String comment) {	
		this.comment=comment;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
	
