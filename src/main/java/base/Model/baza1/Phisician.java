package base.Model.baza1;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;

import base.Model.AbstractPersistentClasses.AbstractAuditableObject;

@Entity
@Audited
public class Phisician extends AbstractAuditableObject<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Basic
	@Pattern(regexp = "^[\\p{IsAlphabetic}\\d]([\\p{IsAlphabetic}\\d]|[- ])*[\\p{IsAlphabetic}\\d]$", message="can contain 'aA-zZ', '0-9' an special characters '-' but not on the begining and ending of the word ")
	@Column(length=60)
	@Size(min=2, max=60)
	private String name;
	
	@Basic
	@NotBlank
	@Pattern(regexp = "^[\\p{IsAlphabetic}\\d]([\\p{IsAlphabetic}\\d]|[- ])*[\\p{IsAlphabetic}\\d]$", message="can contain 'aA-zZ', '0-9' an special characters '-' but not on the begining and ending of the word ")
	@Column(length=120)
	@Size(min=1, max=120)
	private String surname;
	
	@Basic
	@Column(length = 30, unique=true)
	@Size(max=30)
	private String personalIdentificationNumber; 
	
	
	@ManyToMany(mappedBy="phisicians")
	private Set<OrderingUnit> orderingUnits = new HashSet<>();
	
	/*@OneToMany(
			cascade = {CascadeType.MERGE, CascadeType.PERSIST},
			mappedBy = "phisician",
			orphanRemoval = false
			)
	private Set<PatientOrder> patientOrders = new HashSet<>(); */

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPersonalIdentificationNumber() {
		return personalIdentificationNumber;
	}

	public void setPersonalIdentificationNumber(String personalIdentificationNumber) {
		this.personalIdentificationNumber = personalIdentificationNumber;
	}

	public Set<OrderingUnit> getOrderingUnits() {
		return orderingUnits;
	}

	protected void setOrderingUnits(Set<OrderingUnit> orderingUnits) {
		this.orderingUnits.clear();
		this.orderingUnits.addAll(orderingUnits);
	}

	/*public Set<PatientOrder> getPatientOrders() {
		return patientOrders;
	}

	protected void setPatientOrders(Set<PatientOrder> patientOrders) {
		this.patientOrders.clear();
		this.patientOrders.addAll(patientOrders);
	}*/
	
	public void addPatientOrder(PatientOrder patientOrder) {
		patientOrder.setPhisician(this);	
	}

	public void removePatientOrder(PatientOrder patientOrder) {
		patientOrder.setPhisician(null);	
	}
	
	
	
	
}
