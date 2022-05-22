package base.DTO.baza1.PatientSampleDTO;

import javax.validation.constraints.NotNull;

import base.DTO.DTOObjectConstans;
import base.DTO.baza1.OrdersDTO.LabQualityControlDTO;

public class ControlSampleDTO extends SimpleControlSampleDTO {

	@NotNull(groups = DTOObjectConstans.Create.class)
	private LabQualityControlDTO order;

	public LabQualityControlDTO getOrder() {
		return order;
	}

	public void setOrder(LabQualityControlDTO order) {
		this.order = order;
	}

}
