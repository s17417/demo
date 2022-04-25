package base.DTO.baza1.PatientDTO;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import base.DTO.AuditableObjectDTO;

public class SimplePatientDTO extends AuditableObjectDTO {

	@Pattern(regexp = "^[\\p{IsAlphabetic}\\d]([\\p{IsAlphabetic}\\d]|[ -])*[\\p{IsAlphabetic}\\d]$", message="can contain 'aA-zZ', '0-9' an special characters '-' but not on the begining and ending of the word ")
	@Size(min=2, max=60)
	private String name;
	
	@Pattern(regexp = "^[\\p{IsAlphabetic}\\d]([\\p{IsAlphabetic}\\d]|[ -])*[\\p{IsAlphabetic}\\d]$", message="can contain 'aA-zZ', '0-9' an special characters '-' but not on the begining and ending of the word ")
	@Size(min=1, max=120)
	@NotBlank
	private String surname;
	
	@Size(min=3,max=30)
	private String personalIdentificationNumber;
	
	@PastOrPresent
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate dateOfBirth;
	
	private String gender;

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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
