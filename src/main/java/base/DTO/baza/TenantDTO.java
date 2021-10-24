package base.DTO.baza;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import base.DTO.DTOObjectConstans;
import base.DTO.DTOObjectConstans.Create;
import base.DTO.DTOObjectConstans.Update;


public class TenantDTO {
	
	@NotBlank(groups = DTOObjectConstans.Update.class)
	@Null(groups = DTOObjectConstans.Create.class)
	private String Id;
	
	@NotBlank
	@Pattern(regexp = "(^[a-zA-Z0-9]([\\w]|[-]){0,18}[a-zA-Z0-9]$)")
	@Size(min=2, max=60)
	private String name;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
