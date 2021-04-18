package base.Model.baza1;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Check;
import org.hibernate.envers.Audited;

@Audited
@Entity
public class OrderResult extends LabTestOrder<PatientOrder> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@ManyToOne(
			fetch = FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE}
			)
	@JoinColumn(nullable = false)
	private PatientOrder order;

	public OrderResult() {
		super();
	}

	public OrderResult(LaboratoryTest laboratoryTest, PatientOrder patientOrder) {
		super(laboratoryTest);
		setOrder(patientOrder);
	}

	public PatientOrder getOrder() {
		return order;
	}

	protected void setOrder(@NotNull @Valid PatientOrder order) {
		if (order == null) return;
		this.order = order;
		order.getLabTestOrders().add(this);
		
	}
	
	
	
}


