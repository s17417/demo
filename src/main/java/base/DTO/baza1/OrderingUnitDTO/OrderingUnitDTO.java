package base.DTO.baza1.OrderingUnitDTO;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import base.DTO.AuditableObjectDTO;
import base.DTO.DTOObjectConstans;
import base.Model.baza1.Address;

public class OrderingUnitDTO extends AuditableObjectDTO {

	@NotBlank(groups = {DTOObjectConstans.Create.class,DTOObjectConstans.Update.class})
	@Size(min=2, max=30)
	private String shortName;
	
	@NotBlank(groups = {DTOObjectConstans.Create.class,DTOObjectConstans.Update.class})
	@Size(min=2, max=255)
	private String name;
	
	@Embedded
	private Address address;

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
