package base.Model.baza1;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

@Entity
@Audited
public class LabQualityControl extends AbstractOrder<ControlSample/*LabQualityControlResult*/> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Pattern(regexp = "^[\\p{IsAlphabetic}\\d]([\\p{IsAlphabetic}\\d]|[ ])*[\\p{IsAlphabetic}\\d]$", message="can contain 'aA-zZ', '0-9' an special characters '-' but not on the begining and ending of the word ")
	@Size(min=2, max=60)
	@Column(length=60, nullable = false)
	private String name;
	
	@Size(min=2, max=60)
	@Column(length=60)
	private String externalIdentificationCode;
	
	@Basic
	private LocalDateTime expirationDate;
	
	@Basic
	private LocalDateTime reportingDeadLine;
	
	@Size(min=2, max=255)
	@Column(length=255)
	private String description;
	
	/*@OneToMany(
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
			mappedBy = "order"
			)
	private Set<LabQualityControlResult> labTestOrders = new HashSet<>();*/
	
	@OneToMany(
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
			mappedBy = "order"
			)
	private Set<ControlSample> controlSamples = new HashSet<>();
	
	public LabQualityControl() {
		super();
	}
	
	
	
	
	@Override
	protected void setSamples(@NotNull Set<ControlSample> patientSamples) {
		this.controlSamples.clear();
		this.controlSamples.addAll(controlSamples);
		
	}

	@Override
	public Set<ControlSample> getSamples() {
		return controlSamples;
	}

	@Override
	protected ControlSample createSample(ControlSample sample) {
		return new ControlSample(this);
	}
	
	
	

	/*public Set<LabQualityControlResult> getLabTestOrders() {
		return labTestOrders;
	}

	protected void setLabTestOrders(Set<LabQualityControlResult> getLabTestOrders) {
		this.labTestOrders.clear();
		this.labTestOrders.addAll(getLabTestOrders);
	}

	@Override
	protected LabQualityControlResult createLabTestOrder(LaboratoryTest laboratoryTest) {
		return new LabQualityControlResult(laboratoryTest,this);
	}
	
	public LabQualityControlResult addMethodControlResult(@NotNull @Valid LaboratoryTest laboratoryTest, @Valid LabQualityControlResult parentTargetValue) {
		var lab=createLabTestOrder(laboratoryTest);
		if (parentTargetValue!=null)
			lab.setParentTargetValue(parentTargetValue);
		return lab;
	}
	
	public LabQualityControlResult addMethodControlTargetValue(@NotNull @Valid LaboratoryTest laboratoryTest) {
		var lab=createLabTestOrder(laboratoryTest);
		lab.setTargetValue(true);
		return lab;
	}*/




	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExternalIdentificationCode() {
		return externalIdentificationCode;
	}

	public void setExternalIdentificationCode(String externalIdentificationCode) {
		this.externalIdentificationCode = externalIdentificationCode;
	}

	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}

	public LocalDateTime getReportingDeadLine() {
		return reportingDeadLine;
	}

	public void setReportingDeadLine(LocalDateTime reportingDeadLine) {
		this.reportingDeadLine = reportingDeadLine;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
