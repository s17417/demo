package base.DTO.baza1.PatientSampleDTO;

import javax.validation.constraints.Size;
import base.DTO.PersistenceObjectDTO;

public class SpecimentTypeDTO extends PersistenceObjectDTO {

	@Size(min=2, max=60)
	private String speciment;
	
	private Boolean isActive;

	public String getSpeciment() {
		return speciment;
	}

	public void setSpeciment(String speciment) {
		this.speciment = speciment;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
