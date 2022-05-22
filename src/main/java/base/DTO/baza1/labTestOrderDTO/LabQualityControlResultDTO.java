package base.DTO.baza1.labTestOrderDTO;

import javax.validation.constraints.NotNull;

import base.DTO.DTOObjectConstans;
import base.DTO.baza1.PatientSampleDTO.SimpleControlSampleDTO;
import base.DTO.baza1.PatientSampleDTO.SimplePatientSampleDTO;

public class LabQualityControlResultDTO extends SimpleLabQualityControlResultDTO {

	@NotNull(groups = DTOObjectConstans.Create.class)
	private SimpleControlSampleDTO sample;

	public SimpleControlSampleDTO getSample() {
		return sample;
	}

	public void setSample(SimpleControlSampleDTO sample) {
		this.sample = sample;
	}

	

}
