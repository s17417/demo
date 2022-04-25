package base.Model.baza1;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

@Entity
@Audited
@DiscriminatorValue("PATIENT")
public class PatientSample extends Sample<PatientOrder, OrderResult> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@ManyToOne(
			fetch = FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE}
			)
	@JoinColumn(name = "patientOrder_Id", nullable = true)
	private PatientOrder order;
	
	@OneToMany(
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE},
			mappedBy = "patientSample"
			)
	private Set<OrderResult> labTestOrders = new HashSet<>();
	
	protected PatientSample() {
	}

	public PatientSample(@NotNull PatientOrder order) {
		this.setOrder(order);
	}

	@Override
	public void setOrder(PatientOrder order) {
		if (order == null) return;
		this.order = order;
		order.getSamples().add(this);
		
	}

	@Override
	public PatientOrder getOrder() {
		return order;
	}

	@Override
	protected void setLabTestOrders(Set<OrderResult> labTestOrders) {
		this.labTestOrders.clear();
		this.labTestOrders.addAll(labTestOrders);
		
	}

	@Override
	public Set<OrderResult> getLabTestOrders() {
		return labTestOrders;
	}
	
	@Override
	protected OrderResult createLabTestOrder(LaboratoryTest laboratoryTest) {
		return new OrderResult(laboratoryTest, this);
	}
	
	public OrderResult addMethodResult(LaboratoryTest laboratoryTest) {
	return createLabTestOrder(laboratoryTest);
	}


	

}
