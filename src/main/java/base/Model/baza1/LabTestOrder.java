package base.Model.baza1;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Columns;
import org.hibernate.envers.Audited;

import base.Model.AbstractPersistentClasses.AbstractAuditableObject;

@Audited
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class LabTestOrder<T extends Sample<?,?> /*AbstractOrder<?>*/> extends AbstractAuditableObject<String> implements Serializable, IStatefulEntity<OrderStatus> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private OrderStatus labTestOrderStatus=OrderStatus.PENDING;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TatMode tatMode= TatMode.RUTINE;
	
	@Size(min=2,max=512)
	@Column(length = 512)
	private String resultDescription;
	
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
			fetch = FetchType.LAZY,
			mappedBy = "labTestOrder"
			)
	private Set<AbstractAnalyteResult<?,?>> analyteResults = new HashSet<>();

	public LabTestOrder() {
		super();
	}

	protected LabTestOrder(@NotNull @Valid LaboratoryTest laboratoryTest) {
		super();
		this.setLaboratoryTest(laboratoryTest);
	}
	
	@Override
	public void setState(OrderStatus newState) {
		if (newState==null)
			this.labTestOrderStatus=null;
		else
			//this.getTransformation().transform(/*this,*/ newState);
			if(this.getTransformation().transform(/*this,*/ newState)) this.setLabTestOrderStatus(newState);
			
	}
	

	@Override
	public OrderStatus getState() {
		return labTestOrderStatus;
	}

	@Override
	public OrderStatus getTransformation() {
		return labTestOrderStatus;
	}

	protected OrderStatus getLabTestOrderStatus() {
		return labTestOrderStatus;
	}

	protected void setLabTestOrderStatus(OrderStatus labTestOrderStatus) {
		this.labTestOrderStatus = labTestOrderStatus;
	}

	public LaboratoryTest getLaboratoryTest() {
		return laboratoryTest;
	}

	public void setLaboratoryTest(LaboratoryTest laboratoryTest) {
		this.laboratoryTest = laboratoryTest;
		laboratoryTest.getLabTestOrder().add(this);
	}
	
	public Set<AbstractAnalyteResult<?,?>> getAnalyteResults() {
		return analyteResults;
	}

	protected void setAnalyteResults(Set<AbstractAnalyteResult<?,?>> analyteResults) {
		this.analyteResults.clear();
		this.analyteResults.addAll(analyteResults);
	}

	public  Set<? extends AbstractAnalyteResult<?,?>> createAnalyteResults(){
		/*return this.laboratoryTest==null ? new HashSet<>() :
			this.laboratoryTest.getMethods()
			.stream()
			.map(obj -> obj.create(this)).collect(Collectors.toSet());*/
		
		return this.laboratoryTest==null ? new HashSet<>() :
			this.laboratoryTest.getMethods()
			.stream()
			.filter(m-> m.getIsActive())
			.map(obj ->obj.getResultType().createAnalyteResult(this, obj))
			.filter(Optional::isPresent)
			.map( obj -> obj.get())
			.collect(Collectors.toSet());
	}
	
	/*abstract public void setOrder(@NotNull @Valid T order);
	
	abstract public T getOrder();*/
	
	abstract public void setSample(@NotNull @Valid T order);
	
	abstract public T getSample();

	public TatMode getTatMode() {
		return tatMode;
	}

	public void setTatMode(TatMode tatMode) {
		this.tatMode = tatMode;
	}

	public String getResultDescription() {
		return resultDescription;
	}

	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}
	
	
	
}
