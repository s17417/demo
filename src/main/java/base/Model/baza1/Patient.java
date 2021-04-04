package base.Model.baza1;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnore;

import base.Model.AbstractPersistentClasses.AbstractAuditableObject;

@Entity
@Audited
public class Patient extends AbstractAuditableObject<String> {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 

	@Basic
	private String name;
	
	@Basic
	private String surname;
	
	@JsonIgnore
	@OneToMany(
			cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE},
			mappedBy = "patient"//,
			//orphanRemoval = true
			)
	private Set<PatientOrder> patientOrders = new HashSet<>();
	
	public Patient() {
		
	}
	
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

	public Set<PatientOrder> getPatientOrders() {
		return patientOrders;
	}

	public void setPatientOrders(Set<PatientOrder> patientOrders) {
		this.patientOrders.clear();
		this.patientOrders.addAll(patientOrders);
	}

	@Override
	public String toString() {
		return "Patient [getName()=" + getName() + ", getSurname()=" + getSurname() + ", getId()=" + getId() + "]";
	}
	
}
