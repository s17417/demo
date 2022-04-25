package base.DTO.baza1.PatientDTO;

import java.time.LocalDate;

public class ListPatientDTO {
	
	private String Id;
	
	private String name;
	
	private String surname;

	private String personalIdentificationNumber;
	
	private LocalDate dateOfBirth;
	
	

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

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}
	
}
