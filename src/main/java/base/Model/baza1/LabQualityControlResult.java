package base.Model.baza1;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Check;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Check(constraints = "(targetValue=TRUE AND parentTargetValue_Id=NULL) OR (targetValue=FALSE)")
public class LabQualityControlResult extends LabTestOrder<LabQualityControl> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Basic
	@Column(nullable = false, updatable=false)
	private Boolean targetValue = false;
	
	@ManyToOne(
			cascade = {CascadeType.MERGE,CascadeType.PERSIST}
			)
	private LabQualityControlResult parentTargetValue;
	
	@ManyToOne(
			cascade = {CascadeType.MERGE,CascadeType.PERSIST},
			fetch = FetchType.LAZY
			)
	@JoinColumn(nullable=false)
	private LabQualityControl order;
	
	protected LabQualityControlResult() {
		super();
	}

	protected LabQualityControlResult(@NotNull @Valid LaboratoryTest laboratoryTest, @NotNull @Valid LabQualityControl labQualityControl) {
		super(laboratoryTest);
		this.setOrder(labQualityControl);
	}
	
	/*protected LabQualityControlResult(@NotNull @Valid LaboratoryTest laboratoryTest, @NotNull @Valid LabQualityControl labQualityControl, @NotNull @Valid LabQualityControlResult parentTargetValue) {
		super(laboratoryTest);
		this.setOrder(labQualityControl);
		this.setParentTargetValue(parentTargetValue);
	}
	
	protected LabQualityControlResult(@NotNull @Valid LaboratoryTest laboratoryTest, @NotNull @Valid LabQualityControl labQualityControl, @NotNull Boolean targetValue) {
		super(laboratoryTest);
		this.setOrder(labQualityControl);
		this.setTargetValue(targetValue);
	}*/
	
	public LabQualityControl getOrder() {
		return order;
	}
	
	@Override
	protected void setOrder(@NotNull @Valid LabQualityControl order) {
		if (order == null) return;
		this.order = order;
		order.getLabTestOrders().add(this);	
	}

	public Boolean getTargetValue() {
		return targetValue;
	}

	protected void setTargetValue(Boolean targetValue) {
		this.targetValue = targetValue;
	}

	public LabQualityControlResult getParentTargetValue() {
		return parentTargetValue;
	}

	public void setParentTargetValue(LabQualityControlResult parentTargetValue) {
		this.parentTargetValue = parentTargetValue;
	}
	
}
