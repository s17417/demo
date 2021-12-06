package base.Model.baza1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import base.Model.AbstractPersistentClasses.AbstractAuditableObject;

@Entity
@Audited
public class Patient extends AbstractAuditableObject<String> {
	

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
	@Column(length=120, nullable=false)
	@Size(min=1, max=120)
	private String surname;
	
	@Basic
	@Column(length = 30, unique=true)
	@Size(max=30)
	private String personalIdentificationNumber;
	
	@Basic
	@PastOrPresent
	private LocalDate dateOfBirth;
	
	@Enumerated(EnumType.ORDINAL)
	private Gender gender;
	
	@NotAudited
	@ElementCollection(fetch = FetchType.LAZY)
	@Column(nullable =false)
	@OrderColumn
	@Size(max=3)
	private List<String> phoneNumbers = new ArrayList<>();
	
	@NotAudited
	@ElementCollection(fetch = FetchType.LAZY)
	@OrderColumn
	@Size(max=2)
	private List<Address> addresses = new ArrayList<>();
	
	//@Audited(targetAuditMode = RelationTargetAuditMode.AUDITED, )
	
	@OneToMany(
			orphanRemoval = true,
			cascade = {CascadeType.REMOVE,CascadeType.PERSIST, CascadeType.MERGE},
			fetch = FetchType.EAGER
			)
	@JoinColumn(name = "Patient_Id", nullable = false, updatable = false)
	private List<PatientComment> comments = new ArrayList<>();
	
	@OneToMany(
			cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE},
			mappedBy = "patient"//,
			//orphanRemoval = true
			)
	private Set<PatientOrder> patientOrders = new HashSet<>();
	
	public Patient() {
		//org.hibernate.id.factory.internal.DefaultIdentifierGeneratorFactory
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
	public String getPersonalIdentificationNumber() {
		return personalIdentificationNumber;
	}
	public void setPersonalIdentificationNumber(String personalIdentificationNumber) {
		this.personalIdentificationNumber = personalIdentificationNumber;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}	
	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers.clear();
		this.phoneNumbers.addAll(phoneNumbers);
	}
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses.clear();
		this.addresses.addAll(addresses);
	}
	public void addAddress(Address address) {
		this.addresses.add(address);
	}
	public void removeAddress(Address address) {
		this.addresses.remove(address);
	}
	public List<PatientComment> getComments() {
		return comments;
	}
	public void setComments(List<PatientComment> comments) {
		this.comments.clear();
		this.comments.addAll(comments);
	}
	public PatientComment addComment(String comment) {
		var commentObj = new PatientComment(comment);
		this.getComments().add(commentObj);
		return commentObj;
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
		return "Patient [getName()=" + getName() + ", getSurname()=" + getSurname()
				+ ", getPersonalIdentificationNumber()=" + getPersonalIdentificationNumber() + ", getDateOfBirth()="
				+ getDateOfBirth() + ", getGender()=" + getGender() + ", getComments()=" + getComments()
				+ ", getPatientOrders()=" + getPatientOrders() + ", getId()=" + getId() + "]";
	}
	
}
