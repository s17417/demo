package base.Model.baza1;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;
import base.Model.AbstractPersistentClasses.AbstractAuditableObject;

@Entity
@Audited
public class OrderingUnit extends AbstractAuditableObject<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Column(length=30)
	@Size(min=2, max=30)
	private String shortName;
	
	@NotBlank
	@Column(length=255)
	@Size(min=2, max=255)
	private String name;
	
	@Embedded
	private Address address;
	
	//@RestResource(path = "orderingUnitPhisicians", rel = "phisicians")
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
	
	
	
	
	
	

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

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
