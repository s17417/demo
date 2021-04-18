package base.Model.baza1;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import base.Model.AbstractPersistentClasses.AbstractAuditableObject;

@Audited
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class LabTestOrder<T> extends AbstractAuditableObject<String> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Valid
	@ManyToOne(
			cascade = {
					CascadeType.MERGE,
					CascadeType.PERSIST
					},
			fetch = FetchType.LAZY
			)
	@JoinColumn(nullable = false)
	private LaboratoryTest laboratoryTest;
	
	@OneToMany(
			cascade = {
					CascadeType.MERGE,
					CascadeType.PERSIST,
					CascadeType.REMOVE
					},
			fetch = FetchType.LAZY
			)
	private Set<AbstractAnalyteResult<?>> analyteResult = new HashSet<>();

	public LabTestOrder() {
		super();
	}

	protected LabTestOrder(@NotNull @Valid LaboratoryTest laboratoryTest) {
		super();
		this.setLaboratoryTest(laboratoryTest);
	}

	public LaboratoryTest getLaboratoryTest() {
		return laboratoryTest;
	}

	protected void setLaboratoryTest(LaboratoryTest laboratoryTest) {
		this.laboratoryTest = laboratoryTest;
		laboratoryTest.getLabTestOrder().add(this);
	}
	
	public Set<AbstractAnalyteResult<?>> getAnalyteResult() {
		return analyteResult;
	}

	protected void setAnalyteResult(Set<AbstractAnalyteResult<?>> analyteResult) {
		this.analyteResult.clear();
		this.analyteResult.addAll(analyteResult);
	}

	public Set<AbstractAnalyteResult<?>> createAnalyteResults(){
		return this.laboratoryTest==null ? new HashSet<>() :
			this.laboratoryTest.getMethods()
			.stream()
			.map(obj ->obj.getResultType().createAnalyteResult(this, obj))
			.filter(Optional::isPresent)
			.map( obj -> obj.get())
			.collect(Collectors.toSet());
	}
	
	abstract protected void setOrder(@NotNull @Valid T order);
	
	abstract public T getOrder();
	
	
}
