package base.Model.baza1;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DiscriminatorOptions;
import org.hibernate.envers.Audited;

import base.Model.AbstractPersistentClasses.AbstractAuditableObject;

@Entity
@Audited
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorOptions(force = true)
@DiscriminatorColumn(name = "resultType",
discriminatorType = DiscriminatorType.STRING)
public abstract class AbstractAnalyteResult<T, M extends Method> extends AbstractAuditableObject<String> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Enumerated(EnumType.STRING)
	@Column(name="resultType", length = 45, insertable=false, nullable=false, updatable=false)
	private ResultType resultType;
	
	@ManyToOne(
			cascade = {CascadeType.MERGE, CascadeType.PERSIST},
			fetch = FetchType.LAZY
			)
	private  Method method;
	
	@ManyToOne(
			cascade = {CascadeType.MERGE, CascadeType.PERSIST},
			fetch = FetchType.LAZY
			)
	private LabTestOrder<?> labTestOrder;
	
	
	public AbstractAnalyteResult() {
		super();
	}
	
	public AbstractAnalyteResult(LabTestOrder<?> labTestOrder, M method) {
		super();
		this.setLabTestOrder(labTestOrder);
		this.setMethod(method);
	}
	
	abstract public T getResult();
	
	abstract public void setResult(T result);

	public Method getMethod() {
		return method;
	}

	protected void  setMethod(Method method) {
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
	
	public abstract T transformData(T data);

}
