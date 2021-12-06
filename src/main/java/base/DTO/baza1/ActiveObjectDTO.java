package base.DTO.baza1;

import base.DTO.AuditableObjectDTO;

public class ActiveObjectDTO extends AuditableObjectDTO {
	
	private Boolean isActive=true;

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
