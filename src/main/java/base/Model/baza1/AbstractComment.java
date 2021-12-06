package base.Model.baza1;

import javax.persistence.Basic;
import javax.persistence.MappedSuperclass;

import base.Model.AbstractPersistentClasses.AbstractAuditableObject;

@MappedSuperclass
public abstract class AbstractComment extends AbstractAuditableObject<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Basic
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
	
