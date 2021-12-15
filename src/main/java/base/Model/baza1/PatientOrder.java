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

import org.hibernate.envers.Audited;

@Entity
@Audited
public class PatientOrder extends AbstractOrder<OrderResult> {

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

	@OneToMany(
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE},
			mappedBy = "order"
			)
	private Set<OrderResult> labTestOrders = new HashSet<>();


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

	public Set<OrderResult> getLabTestOrders() {
		return labTestOrders;
	}

	protected void setLabTestOrders(Set<OrderResult> labTestOrders) {
		this.labTestOrders.clear();
		this.labTestOrders.addAll(labTestOrders);
	}
	
	@Override
	protected OrderResult createLabTestOrder(LaboratoryTest laboratoryTest) {
		return new OrderResult(laboratoryTest, this);
	}
	
	public OrderResult addMethodResult(LaboratoryTest laboratoryTest) {
		return createLabTestOrder(laboratoryTest);
	}
	
}
