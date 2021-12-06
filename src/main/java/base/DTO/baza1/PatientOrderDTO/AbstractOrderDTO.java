package base.DTO.baza1.PatientOrderDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import base.DTO.AuditableObjectDTO;
import base.DTO.DTOObjectConstans;

public class AbstractOrderDTO extends AuditableObjectDTO {

	@NotBlank(groups = {DTOObjectConstans.Create.class, /*DTOObjectConstans.Update.class*/})
	@Size(min=4, max=128)
	@Pattern(regexp = "[a-zA-Z0-9]+")
	private String orderIdentification;

	public String getOrderIdentification() {
		return orderIdentification;
	}

	public void setOrderIdentification(String orderIdentification) {
		this.orderIdentification = orderIdentification;
	}

}
