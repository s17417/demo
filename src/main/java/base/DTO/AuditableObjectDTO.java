package base.DTO;

import java.time.LocalDateTime;

import base.Model.baza1.AbstractComment;


public class AuditableObjectDTO extends PersistenceObjectDTO {

	
	private LocalDateTime cretionTimeStamp;
	
	
	private LocalDateTime updateTimeStamp;
	
	
	private String createdBy;
	
	
	private String lastModifiedBy;


	public LocalDateTime getCretionTimeStamp() {
		return cretionTimeStamp;
	}


	public void setCretionTimeStamp(LocalDateTime cretionTimeStamp) {
		this.cretionTimeStamp = cretionTimeStamp;
	}


	public LocalDateTime getUpdateTimeStamp() {
		return updateTimeStamp;
	}


	public void setUpdateTimeStamp(LocalDateTime updateTimeStamp) {
		this.updateTimeStamp = updateTimeStamp;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public String getLastModifiedBy() {
		return lastModifiedBy;
	}


	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
}
