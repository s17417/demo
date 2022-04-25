package base.DTO.baza1.PatientSampleDTO;

import javax.validation.constraints.Size;
import base.DTO.PersistenceObjectDTO;

public class SpecimentTypeDTO extends PersistenceObjectDTO {

	@Size(min=2, max=60)
	private String speciment;

	public String getSpeciment() {
		return speciment;
	}

	public void setSpeciment(String speciment) {
		this.speciment = speciment;
	}

}
