package base.DTO.baza1.labTestOrderDTO;

import javax.validation.constraints.NotNull;

import base.DTO.DTOObjectConstans;
import base.DTO.baza1.OrdersDTO.PatientOrderDTO;
import base.DTO.baza1.PatientSampleDTO.SimplePatientSampleDTO;

public class OrderResultDTO extends SimpleOrderResultDTO {

	@NotNull(groups = DTOObjectConstans.Create.class)
	private SimplePatientSampleDTO sample;

	public SimplePatientSampleDTO getSample() {
		return sample;
	}

	public void setSample(SimplePatientSampleDTO sample) {
		this.sample = sample;
	}

}
