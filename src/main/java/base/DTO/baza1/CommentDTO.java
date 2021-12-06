package base.DTO.baza1;

import base.DTO.AuditableObjectDTO;

public class CommentDTO extends AuditableObjectDTO  {
	
	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
