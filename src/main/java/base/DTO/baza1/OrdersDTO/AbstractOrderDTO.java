package base.DTO.baza1.OrdersDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import base.DTO.AuditableObjectDTO;
import base.DTO.DTOObjectConstans;
import base.DTO.baza1.MethodDTO.QualitativeFormatMethodDTO;
import base.DTO.baza1.MethodDTO.QuantitativeFormatMethodDTO;

public class AbstractOrderDTO extends AuditableObjectDTO {

	@NotBlank(groups = {DTOObjectConstans.Create.class, /*DTOObjectConstans.Update.class*/})
	@Size(min=4, max=128)
	@Pattern(regexp = "[a-zA-Z0-9]+")
	private String orderIdentificationCode;

	public String getOrderIdentificationCode() {
		return orderIdentificationCode;
	}

	public void setOrderIdentificationCode(String orderIdentificationCode) {
		this.orderIdentificationCode = orderIdentificationCode;
	}

}
