package base.Model.baza1;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLUpdate;
import org.hibernate.envers.Audited;

import base.Model.AbstractPersistentClasses.AbstractActiveObject;

@Audited
@Entity
@SQLDelete(sql = "UPDATE LABORATORYTEST SET ISACTIVE = false WHERE id = ?")
//@SQLUpdate(sql="INSERT INTO LaboratoryTestHistory values (id, updateTimeStamp)")
public class LaboratoryTest extends AbstractActiveObject<String> /*AbstractAuditableObject<String>*/ {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Basic
	@NotBlank
	@Column(length=180)
	@Size(min=2, max=180)
	private String name;
	
	@NotBlank
	@Column(length=30, unique = true)
	@Size(min=2, max=30)
	private String shortName;
	
	@NotNull
	@JoinColumn(nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private SpecimentType specimentType;
	
	@Column(length=255)
	@Size(min=2, max=255)
	private String description;
	
	
	
	@OneToMany(
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE,
					CascadeType.REMOVE
					},
			fetch = FetchType.LAZY,
			mappedBy = "laboratoryTest"
			)
	private Set<LabTestOrder<?>> labTestOrder = new HashSet<>();
	
	@OneToMany(
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE,
					CascadeType.REMOVE
					},
			fetch = FetchType.LAZY,
			mappedBy = "laboratoryTest"
			)
	private Set<Method> methods = new HashSet<>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	

	public SpecimentType getSpecimentType() {
		return specimentType;
	}

	public void setSpecimentType(SpecimentType specimentType) {
		this.specimentType = specimentType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<LabTestOrder<?>> getLabTestOrder() {
		return labTestOrder;
	}

	protected void setLabTestOrder(Set<LabTestOrder<?>> labTestOrder) {
		this.labTestOrder.clear();
		this.labTestOrder.addAll(labTestOrder);
	}
	
	public OrderResult addPatientMethodResult(@NotNull @Valid PatientSample patientSample) {
		return patientSample.addMethodResult(this);	
	}
	
	/*public LabQualityControlResult addControlMethodResult(@NotNull @Valid LabQualityControl labQualityControl, @Valid LabQualityControlResult parentTargetValue) {
		return labQualityControl.addMethodControlResult(this, parentTargetValue);
	}
	
	public LabQualityControlResult addControlMethodTargetValue(@NotNull @Valid LabQualityControl labQualityControl) {
		return labQualityControl.addMethodControlTargetValue(this);
	}*/
	
	public LabQualityControlResult addControlMethodResult(@NotNull @Valid ControlSample controlSample, @Valid LabQualityControlResult parentTargetValue) {
		return controlSample.addMethodControlResult(this, parentTargetValue);
	}
	
	public LabQualityControlResult addControlMethodTargetValue(@NotNull @Valid ControlSample controlSample) {
		return controlSample.addMethodControlTargetValue(this);
	}

	public Set<Method> getMethods() {
		return methods;
	}

	protected void setMethods(Set<Method> methods) {
		this.methods.clear();
		this.methods.addAll(methods);
	}
	
	public <T extends Method> T addAnalyte(Analyte analyte, T method) {
		method.setAnalyte(analyte);
		method.setLaboratoryTest(this);
		return method;
	}
	
}
