package base.DTO.baza1.LaboratoryTestDTO;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import base.DTO.baza1.ActiveObjectDTO;

public class LaboratoryTestDTO extends ActiveObjectDTO {
	
	@NotBlank
	@Size(min=2, max=180)
	private String name;
	
	@NotBlank
	@Size(min=2, max=30)
	private String shortName;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
