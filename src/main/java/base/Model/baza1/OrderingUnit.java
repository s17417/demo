package base.Model.baza1;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.hibernate.envers.Audited;

import base.Model.AbstractPersistentClasses.AbstractAuditableObject;

@Entity
@Audited
public class OrderingUnit extends AbstractAuditableObject<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
			})
	@JoinTable(name = "PhisicianOrderingUnit",
			joinColumns = @JoinColumn(name= "orderingUnitId"),
			inverseJoinColumns = @JoinColumn(name="phisicianId")
	)
	private Set<@Valid Phisician> phisicians = new HashSet<>();
	
	@OneToMany(
			cascade = {CascadeType.MERGE, CascadeType.PERSIST},
			mappedBy = "orderingUnit"
			)
	private Set<PatientOrder> patientOrders = new HashSet<>();
	
	
	
	
	
	

	public Set<Phisician> getPhisicians() {
		return phisicians;
	}

	protected void setPhisicians(Set<Phisician> phisicians) {
		this.phisicians.clear();
		this.phisicians.addAll(phisicians);
	}
	
	public void addPhisician(@Valid Phisician phisician) {
		if (phisician==null||phisicians.contains(phisician)) return;
		phisicians.add(phisician);
		phisician.getOrderingUnits().add(this);
	}
	
	public void removePhisician(Phisician phisician) {
		System.out.println("-----1-remove------------");
		if (phisician==null||!phisicians.contains(phisician)) return;
		phisicians.remove(phisician);
		phisician.getOrderingUnits().remove(this);
	}

	public Set<PatientOrder> getPatientOrders() {
		return patientOrders;
	}

	protected void setPatientOrders(Set<PatientOrder> patientOrders) {
		this.patientOrders.clear();
		this.patientOrders.addAll(patientOrders);
	}
	
	public void addPatientOrder(PatientOrder patientOrder) {
		patientOrder.setOrderingUnit(this);
	}
	
	public void removePatientORder(PatientOrder patientOrder) {
		patientOrder.setPatient(null);
	}

}
