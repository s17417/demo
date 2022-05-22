package base.Model.baza1;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
public class OrderResult extends LabTestOrder<PatientSample> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*@NotNull
	@ManyToOne(
			fetch = FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE}
			)
	@JoinColumn(nullable = false)
	private PatientOrder order;*/
	
	@NotNull
	@ManyToOne(
			fetch = FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE}
			)
	@JoinColumn(nullable = false)
	private PatientSample patientSample;

	public OrderResult() {
		super();
	}

	public OrderResult(LaboratoryTest laboratoryTest, /*PatientOrder patientOrder*/ PatientSample patientSample) {
		super(laboratoryTest);
		setSample(patientSample);
	}

	
	@Override
	public void setSample(@NotNull @Valid PatientSample patientSample) {
		if (patientSample == null) return;
		this.patientSample = patientSample;
		patientSample.getLabTestOrders().add(this);
		
	}

	@Override
	public PatientSample getSample() {
		return patientSample;
	}

	@Override
	public Set<? extends AbstractAnalyteResult<?, ?>> createAnalyteResults() {
			
			return this.getLaboratoryTest()==null ? new HashSet<>() :
				this.getLaboratoryTest().getMethods()
				.stream()
				.filter(m-> m.getIsActive())
				.map(obj ->obj.createAnalyteResult(this))
				.filter(Optional::isPresent)
				.map( obj -> obj.get())
				.collect(Collectors.toSet());
	}
	
	
	
	
	
}

	


