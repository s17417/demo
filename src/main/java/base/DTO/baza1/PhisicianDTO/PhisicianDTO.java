package base.DTO.baza1.PhisicianDTO;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import base.DTO.AuditableObjectDTO;
import base.DTO.DTOObjectConstans;

public class PhisicianDTO extends AuditableObjectDTO {

	@Pattern(regexp = "^[\\p{IsAlphabetic}\\d]([\\p{IsAlphabetic}\\d]|[- ])*[\\p{IsAlphabetic}\\d]$", message="can contain 'aA-zZ', '0-9' an special characters '-' but not on the begining and ending of the word ")
	@Size(min=2, max=60)
	private String name;
	
	@NotBlank(groups = {DTOObjectConstans.Create.class,DTOObjectConstans.Update.class})
	@Pattern(regexp = "^[\\p{IsAlphabetic}\\d]([\\p{IsAlphabetic}\\d]|[- ])*[\\p{IsAlphabetic}\\d]$", message="can contain 'aA-zZ', '0-9' an special characters '-' but not on the begining and ending of the word ")
	@Size(min=1, max=120)
	private String surname;
	
	@Size(max=30)
	private String personalIdentificationNumber;

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getPersonalIdentificationNumber() {
		return personalIdentificationNumber;
	}


	public void setPersonalIdentificationNumber(String personalIdentificationNumber) {
		this.personalIdentificationNumber = personalIdentificationNumber;
	}
	
}
