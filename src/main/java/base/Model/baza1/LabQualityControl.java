package base.Model.baza1;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class LabQualityControl extends AbstractOrder<LabQualityControlResult> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToMany(
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
			mappedBy = "order"
			)
	private Set<LabQualityControlResult> labTestOrders = new HashSet<>();
	
	public Set<LabQualityControlResult> getLabTestOrders() {
		return labTestOrders;
	}

	protected void setLabTestOrders(Set<LabQualityControlResult> getLabTestOrders) {
		this.labTestOrders.clear();
		this.labTestOrders.addAll(getLabTestOrders);
	}

	@Override
	protected LabQualityControlResult createLabTestOrder(LaboratoryTest laboratoryTest) {
		return new LabQualityControlResult(laboratoryTest,this);
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
