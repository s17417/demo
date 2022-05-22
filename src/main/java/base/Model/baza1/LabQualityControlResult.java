package base.Model.baza1;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
public class LabQualityControlResult extends LabTestOrder</*LabQualityControl*/ ControlSample> {

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
	
	/*@ManyToOne(
			cascade = {CascadeType.MERGE,CascadeType.PERSIST},
			fetch = FetchType.LAZY
			)
	@JoinColumn(nullable=false)
	private LabQualityControl order;*/
	
	@NotNull
	@ManyToOne(
			cascade = {CascadeType.MERGE,CascadeType.PERSIST},
			fetch = FetchType.LAZY
			)
	@JoinColumn(nullable=false)
	private ControlSample controlSample;
	
	protected LabQualityControlResult() {
		super();
	}

	/*protected LabQualityControlResult(@NotNull @Valid LaboratoryTest laboratoryTest, @NotNull @Valid LabQualityControl labQualityControl) {
		super(laboratoryTest);
		this.setOrder(labQualityControl);
	}*/
	
	protected LabQualityControlResult(@NotNull @Valid LaboratoryTest laboratoryTest, @NotNull @Valid ControlSample controlSample) {
		super(laboratoryTest);
		this.setSample(controlSample);
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
	
	/*public LabQualityControl getOrder() {
		return order;
	}
	
	@Override
	public void setOrder(@NotNull @Valid LabQualityControl order) {
		if (order == null) return;
		this.order = order;
		order.getLabTestOrders().add(this);	
	}*/
	
	

	public Boolean getTargetValue() {
		return targetValue;
	}

	@Override
	public void setSample(@NotNull @Valid ControlSample controlSample) {
		if (controlSample == null) return;
		this.controlSample = controlSample;
		controlSample.getLabTestOrders().add(this);		
	}

	@Override
	public ControlSample getSample() {
		return controlSample;
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
	
	@Override
	public Set<? extends AbstractAnalyteResult<?, ?>> createAnalyteResults() {
			if (targetValue!=true)
				return this.getLaboratoryTest()==null ? new HashSet<>() :
					this.getLaboratoryTest().getMethods()
					.stream()
					.filter(m-> m.getIsActive())
					.map(obj ->obj.createAnalyteResult(this))
					.filter(Optional::isPresent)
					.map( obj -> obj.get())
					.collect(Collectors.toSet());
			else
				return this.getLaboratoryTest()==null ? new HashSet<>() :
					this.getLaboratoryTest().getMethods()
					.stream()
					.filter(m-> m.getIsActive())
					.map(obj ->obj.creatControlTargetResult(this))
					.filter(Optional::isPresent)
					.map( obj -> obj.get())
					.collect(Collectors.toSet());
	}
	
	public static LabQualityControlResult createTargetLabQualityControlResult(@NotNull @Valid LaboratoryTest laboratoryTest, @NotNull @Valid ControlSample controlSample) {
		var target = new LabQualityControlResult(laboratoryTest, controlSample);
		target.setTargetValue(true);
			laboratoryTest.getMethods()
			.stream()
			.filter(m-> m.getIsActive())
			.map(obj ->obj.creatControlTargetResult(target))
			.filter(Optional::isPresent)
			.map( obj -> obj.get())
			.collect(Collectors.toSet());
		return target;
	}
	
}
