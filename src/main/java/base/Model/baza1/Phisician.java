package base.Model.baza1;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.envers.Audited;

import base.Model.AbstractPersistentClasses.AbstractAuditableObject;

@Entity
@Audited
public class Phisician extends AbstractAuditableObject<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToMany(mappedBy="phisicians")
	private Set<OrderingUnit> orderingUnits = new HashSet<>();
	
	@OneToMany(
			cascade = {CascadeType.MERGE, CascadeType.PERSIST},
			mappedBy = "phisician",
			orphanRemoval = false
			)
	private Set<PatientOrder> patientOrders = new HashSet<>(); 

	
	public Set<OrderingUnit> getOrderingUnits() {
		return orderingUnits;
	}

	protected void setOrderingUnits(Set<OrderingUnit> orderingUnits) {
		this.orderingUnits.clear();
		this.orderingUnits.addAll(orderingUnits);
	}

	public Set<PatientOrder> getPatientOrders() {
		return patientOrders;
	}

	protected void setPatientOrders(Set<PatientOrder> patientOrders) {
		this.patientOrders.clear();
		this.patientOrders.addAll(patientOrders);
	}
	
	public void addPatientOrder(PatientOrder patientOrder) {
		patientOrder.setPhisician(this);	
	}

	public void removePatientOrder(PatientOrder patientOrder) {
		patientOrder.setPhisician(null);	
	}
	
	
	
	
}
