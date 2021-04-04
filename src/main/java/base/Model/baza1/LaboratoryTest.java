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
	private Set<LabTestOrder> labTestOrder = new HashSet<>();
	
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
	
	public Set<LabTestOrder> getLabTestOrder() {
		return labTestOrder;
	}

	protected void setLabTestOrder(Set<LabTestOrder> labTestOrder) {
		this.labTestOrder.clear();
		this.labTestOrder.addAll(labTestOrder);
	}
	
	public OrderResult addPatientOrder(PatientOrder patientOrder) {
		return new OrderResult(this, patientOrder);	
	}
	
	public LabQualityControlResult addQualityControlResult(@NotNull @Valid LabQualityControl labQualityControl) {
		return new LabQualityControlResult(this, labQualityControl);
	}
	
	public LabQualityControlResult addQualityControlResult(@NotNull @Valid LabQualityControl labQualityControl, @NotNull @Valid LabQualityControlResult parentTargetValue) {
		return new LabQualityControlResult(this, labQualityControl, parentTargetValue);
	}
	
	public LabQualityControlResult addQualityControlTargetValue(@NotNull @Valid LabQualityControl labQualityControl) {
		return new LabQualityControlResult(this, labQualityControl, true);
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
