package base.Model.baza1;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class PatientOrder extends AbstractOrder {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Valid
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable=false)
	private Patient patient;
	
	@Valid
	@ManyToOne(fetch = FetchType.LAZY)
	private OrderingUnit orderingUnit;
	
	@Valid
	@ManyToOne(fetch = FetchType.LAZY)
	private Phisician phisician;

	@OneToMany(
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE},
			mappedBy = "patientOrder"
			)
	private Set<OrderResult> orderResults = new HashSet<>();
	
	protected PatientOrder() {
		
	}
	
	public PatientOrder(@NotNull Patient patient, OrderingUnit orderingUnit, Phisician phisician) {
		this.setPatient(patient);
		this.setOrderingUnit(orderingUnit);
		this.setPhisician(phisician);
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
		if (orderingUnit==null) {
			if (this.orderingUnit!=null)
				this.orderingUnit.getPatientOrders().remove(this);
			this.orderingUnit=null;
		}
		if (orderingUnit!=null) {
			if (this.orderingUnit!=null)
				this.orderingUnit.getPatientOrders().remove(this);
			this.orderingUnit=orderingUnit;
			orderingUnit.getPatientOrders().add(this);
		}
	}

	public Phisician getPhisician() {
		return phisician;
	}

	public void setPhisician(Phisician phisician) {
		if (phisician==null) {
			if (this.phisician!=null)
				this.phisician.getPatientOrders().remove(this);
			this.phisician=null;
		}
		if (phisician!=null) {
			if (this.phisician!=null)
				this.phisician.getPatientOrders().remove(this);
			this.phisician=phisician;
			phisician.getPatientOrders().add(this);
		}
	}

	public Set<OrderResult> getOrderResults() {
		return orderResults;
	}

	protected void setOrderResults(Set<OrderResult> orderResults) {
		this.orderResults.clear();
		this.orderResults.addAll(orderResults);
	}
	
	public OrderResult addLaboratoryTest(LaboratoryTest laboratoryTest) {
		return new OrderResult(laboratoryTest, this);
	}
	
	
}
