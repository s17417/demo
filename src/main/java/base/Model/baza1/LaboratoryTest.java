package base.Model.baza1;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.envers.Audited;

import base.Model.AbstractPersistentClasses.AbstractAuditableObject;

@Audited
@Entity
@SQLDelete(sql = "UPDATE LABORATORYTEST SET ISACTIVE = false WHERE id = ?")
public class LaboratoryTest extends AbstractAuditableObject<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
	
	public Set<LabTestOrder<?>> getLabTestOrder() {
		return labTestOrder;
	}

	protected void setLabTestOrder(Set<LabTestOrder<?>> labTestOrder) {
		this.labTestOrder.clear();
		this.labTestOrder.addAll(labTestOrder);
	}
	
	public OrderResult addPatientMethodResult(@NotNull @Valid PatientOrder patientOrder) {
		return patientOrder.addMethodResult(this);	
	}
	
	public LabQualityControlResult addControlMethodResult(@NotNull @Valid LabQualityControl labQualityControl, @Valid LabQualityControlResult parentTargetValue) {
		return labQualityControl.addMethodControlResult(this, parentTargetValue);
	}
	
	public LabQualityControlResult addControlMethodTargetValue(@NotNull @Valid LabQualityControl labQualityControl) {
		return labQualityControl.addMethodControlTargetValue(this);
	}

	public Set<Method> getMethods() {
		return methods;
	}

	protected void setMethods(Set<Method> methods) {
		this.methods.clear();
		this.methods.addAll(methods);
	}
	
	public Method addAnalyte(Analyte analyte) {
		return new Method(analyte, this);
	}
	
}
