package base.DTO.baza1.PatientSampleDTO;

import javax.validation.constraints.NotNull;

import base.DTO.DTOObjectConstans;
import base.DTO.baza1.OrdersDTO.ListPatientOrderDTO;

public class PatientSampleDTO extends SimplePatientSampleDTO {

	@NotNull(groups = DTOObjectConstans.Create.class)
	private ListPatientOrderDTO order;

	public ListPatientOrderDTO getOrder() {
		return order;
	}

	public void setOrder(ListPatientOrderDTO order) {
		this.order = order;
	}

}
