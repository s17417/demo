package base.DTO.baza1.PatientDTO;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import base.DTO.baza1.AddressDTO;
import base.DTO.baza1.CommentDTO;

public class SimplePatientWithCollectionsDTO extends SimplePatientDTO {
	
	@Size(max=3)
	private List<String> phoneNumbers;
	
	@Size(max=2)
	private List<@Valid AddressDTO> addresses;

	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public List<AddressDTO> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressDTO> addresses) {
		this.addresses = addresses;
	}
	
}
