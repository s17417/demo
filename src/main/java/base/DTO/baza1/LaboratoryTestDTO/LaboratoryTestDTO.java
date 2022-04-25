package base.DTO.baza1.LaboratoryTestDTO;


import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import base.DTO.baza1.ActiveObjectDTO;
import base.DTO.baza1.PatientSampleDTO.SpecimentTypeDTO;
import base.Model.baza1.SpecimentType;

public class LaboratoryTestDTO extends ActiveObjectDTO {
	
	@NotBlank
	@Size(min=2, max=180)
	private String name;
	
	@NotBlank
	@Size(min=2, max=30)
	private String shortName;
	
	@NotNull
	private SpecimentTypeDTO specimentType;
	
	@Size(min=2, max=255)
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	

	public SpecimentTypeDTO getSpecimentType() {
		return specimentType;
	}

	public void setSpecimentType(SpecimentTypeDTO specimentType) {
		this.specimentType = specimentType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
