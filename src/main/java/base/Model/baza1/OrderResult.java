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
public class OrderResult extends LabTestOrder {

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
	private PatientOrder patientOrder;

	protected OrderResult() {
		super();
	}

	public OrderResult(LaboratoryTest laboratoryTest, PatientOrder patientOrder) {
		super(laboratoryTest);
		setPatientOrder(patientOrder);
	}

	public PatientOrder getPatientOrder() {
		return patientOrder;
	}

	protected void setPatientOrder(@NotNull @Valid PatientOrder patientOrder) {
		if (patientOrder == null) return;
		this.patientOrder = patientOrder;
		patientOrder.getOrderResults().add(this);
		
	}
	
	
	
}


