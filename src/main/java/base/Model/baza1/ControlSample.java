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
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

@Entity
@Audited

@DiscriminatorValue("CONTROL")
public class ControlSample extends Sample<LabQualityControl, LabQualityControlResult> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@OneToMany(
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
			mappedBy = "controlSample"
			)
	private Set<LabQualityControlResult> labTestOrders = new HashSet<>();
	
	
	@ManyToOne(
			cascade = {CascadeType.MERGE,CascadeType.PERSIST},
			fetch = FetchType.LAZY
			)
	@JoinColumn(name="labQualityControl_Id", nullable=true)
	private LabQualityControl order;
	
	
	public ControlSample() {
		
	}
	
	public ControlSample(LabQualityControl order) {
		this.setOrder(order);
	}

	@Override
	public void setOrder(LabQualityControl order) {
		if (order == null) return;
		this.order = order;
		order.getSamples().add(this);
		
	}

	@Override
	public LabQualityControl getOrder() {
		return order;
	}

	@Override
	protected void setLabTestOrders(@NotNull @Valid Set<LabQualityControlResult> labTestOrders) {
		this.labTestOrders.clear();
		this.labTestOrders.addAll(labTestOrders);
		
	}

	@Override
	public Set<LabQualityControlResult> getLabTestOrders() {
		return labTestOrders;
	}

	@Override
	protected LabQualityControlResult createLabTestOrder(LaboratoryTest laboratoryTest) {
		return new LabQualityControlResult(laboratoryTest, this);
	}

	public LabQualityControlResult addMethodControlResult(@NotNull @Valid LaboratoryTest laboratoryTest, @Valid LabQualityControlResult parentTargetValue) {
		var lab=createLabTestOrder(laboratoryTest);
		if (parentTargetValue!=null)
			lab.setParentTargetValue(parentTargetValue);
		return lab;
	}
	
	public LabQualityControlResult addMethodControlTargetValue(@NotNull @Valid LaboratoryTest laboratoryTest) {
		var lab=createLabTestOrder(laboratoryTest);
		lab.setTargetValue(true);
		return lab;
	}

}
