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
public class LabQualityControl extends AbstractOrder {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToMany(
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
			mappedBy = "labQualityControl"
			)
	private Set<LabQualityControlResult> labQualityControlResults = new HashSet<>();
	
	public Set<LabQualityControlResult> getLabQualityControlResults() {
		return labQualityControlResults;
	}

	protected void setLabQualityControlResults(Set<LabQualityControlResult> labQualityControlResults) {
		this.labQualityControlResults.clear();
		this.labQualityControlResults.addAll(labQualityControlResults);
	}

	public LabQualityControlResult addQualityControlResult(@NotNull @Valid LaboratoryTest laboratoryTest) {
		return new LabQualityControlResult(laboratoryTest, this);
	}
	
	public LabQualityControlResult addQualityControlResult(@NotNull @Valid LaboratoryTest laboratoryTest, @NotNull @Valid LabQualityControlResult parentTargetValue) {
		return new LabQualityControlResult(laboratoryTest, this, parentTargetValue);
	}
	
	public LabQualityControlResult addQualityControlTargetValue(@NotNull @Valid LaboratoryTest laboratoryTest) {
		return new LabQualityControlResult(laboratoryTest, this, true);
	}
	
	
	/*public OrderResult setLabTestTarget(LaboratoryTest laboratoryTest) {
		return new OrderResult(laboratoryTest, this);
	}*/
	

}
