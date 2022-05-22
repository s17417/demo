package base.DTO.baza1.PatientSampleDTO;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import base.DTO.AuditableObjectDTO;

public class SimpleControlSampleDTO extends AuditableObjectDTO {

	@NotNull
	@Size(min = 2, max=60)
	private String sampleId;
	
	@NotNull
	private SpecimentTypeDTO specimentType;
	
	@PastOrPresent
	private LocalDateTime collectionDate;

	public String getSampleId() {
		return sampleId;
	}

	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}

	public SpecimentTypeDTO getSpecimentType() {
		return specimentType;
	}

	public void setSpecimentType(SpecimentTypeDTO specimentType) {
		this.specimentType = specimentType;
	}

	public LocalDateTime getCollectionDate() {
		return collectionDate;
	}

	public void setCollectionDate(LocalDateTime collectionDate) {
		this.collectionDate = collectionDate;
	}


}
