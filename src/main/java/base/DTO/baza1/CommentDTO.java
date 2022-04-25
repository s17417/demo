package base.DTO.baza1;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import base.DTO.AuditableObjectDTO;

public class CommentDTO extends AuditableObjectDTO  {
	@NotBlank
	@Size(min = 1, max = 4096)
	private String comment;

	public String getComment() {
		return comment;
	}
	

	public void setComment(String comment) {
		this.comment = comment;
	}

}
