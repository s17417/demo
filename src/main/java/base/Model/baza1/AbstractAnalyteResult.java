package base.Model.baza1;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import org.hibernate.envers.Audited;

import base.Model.AbstractPersistentClasses.AbstractAuditableObject;

@Entity
@Audited
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "resultType")
public abstract class AbstractAnalyteResult<T, M extends Method> extends AbstractAuditableObject<String> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Enumerated
	@Column(name="resultType", insertable=false, nullable=false, updatable=false)
	private ResultType resultType;
	//private IResultTypeAssociationCreator resultType;
	
	@ManyToOne(
			cascade = {CascadeType.MERGE, CascadeType.PERSIST},
			fetch = FetchType.EAGER
			)
	private  M method;
	
	@ManyToOne(
			cascade = {CascadeType.MERGE, CascadeType.PERSIST},
			fetch = FetchType.LAZY
			)
	private LabTestOrder<?> labTestOrder;
	
	
	protected AbstractAnalyteResult() {
		super();
	}
	
	protected AbstractAnalyteResult(LabTestOrder<?> labTestOrder, M method) {
		super();
		this.setLabTestOrder(labTestOrder);
		this.setMethod(method);
	}
	
	abstract public T getResult();
	
	abstract public void setResult(T result);

	public M getMethod() {
		return method;
	}

	protected  void  setMethod(M method) {
		this.method = method;
		method.getAnalyteResults().add(this);
	}

	public LabTestOrder<?> getLabTestOrder() {
		return labTestOrder;
	}

	protected void setLabTestOrder(LabTestOrder<?> labTestOrder) {
		this.labTestOrder = labTestOrder;
		labTestOrder.getAnalyteResults().add(this);
	}

	public ResultType getResultType() {
		return resultType;
	}

	public void setResultType(ResultType resultType) {
		this.resultType = resultType;
	}
	
	
}
