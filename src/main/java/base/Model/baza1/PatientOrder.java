package base.Model.baza1;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;

@Entity
@Audited
public class PatientOrder extends AbstractOrder<PatientSample> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable=false)
	private Patient patient;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private OrderingUnit orderingUnit;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Phisician phisician;

	/*@OneToMany(
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE},
			mappedBy = "order"
			)
	private Set<OrderResult> labTestOrders = new HashSet<>();*/
	
	@OneToMany(
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE},
			mappedBy = "order"
			)
	private Set<PatientSample> patientSamples = new HashSet<>();


	@Basic
	@PastOrPresent
	private LocalDate orderDate;
	
	protected PatientOrder() {
		
	}
	
	public PatientOrder(@NotNull Patient patient) {
		this.setPatient(patient);
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(@NotNull Patient patient) {
		if (patient==null) {
			if (this.patient!=null)
				this.patient.getPatientOrders().remove(this);
			this.patient=patient;
		}
		if (patient!=null) {
			if (this.patient!=null)
				this.patient.getPatientOrders().remove(this);
			this.patient=patient;
			patient.getPatientOrders().add(this);
		}
	}

	public OrderingUnit getOrderingUnit() {
		return orderingUnit;
	}
	
	public void setOrderingUnit(OrderingUnit orderingUnit) {
		this.orderingUnit=orderingUnit;
	}

	public Phisician getPhisician() {
		return phisician;
	}

	public void setPhisician(Phisician phisician) {
		this.phisician=phisician;
	}	
	
	/*@Override
	public Set<OrderResult> getLabTestOrders() {
		return labTestOrders;
	}

	@Override
	protected void setLabTestOrders(Set<OrderResult> labTestOrders) {
		this.labTestOrders.clear();
		this.labTestOrders.addAll(labTestOrders);
	}
	
	@Override
	protected OrderResult createLabTestOrder(LaboratoryTest laboratoryTest) {
		return new OrderResult(laboratoryTest, this);
	}*/
	
	@Override
	protected void setSamples(@NotNull Set<PatientSample> patientSamples) {
		this.patientSamples.clear();
		this.patientSamples.addAll(patientSamples);
		
	}

	@Override
	public Set<PatientSample> getSamples() {
		return patientSamples;
	}

	@Override
	protected PatientSample createSample(PatientSample sample) {
		return new PatientSample(this);
	}

	@Override
	public String toString() {
		return "PatientOrder [patient=" + patient + ", orderingUnit=" + orderingUnit + ", phisician=" + phisician
				+ ", patientSamples=" + patientSamples + ", orderDate=" + orderDate + ", getOrderIdentificationCode()="
				+ getOrderIdentificationCode() + ", getCretionTimeStamp()=" + getCretionTimeStamp()
				+ ", getUpdateTimeStamp()=" + getUpdateTimeStamp() + ", getCreatedBy()=" + getCreatedBy()
				+ ", getLastModifiedBy()=" + getLastModifiedBy() + ", getId()=" + getId() + ", getVersionTimestamp()="
				+ getVersionTimestamp() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";
	}

	
	
	

	/*public OrderResult addMethodResult(LaboratoryTest laboratoryTest) {
		return createLabTestOrder(laboratoryTest);
	}*/
	
}
