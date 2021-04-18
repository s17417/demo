package base.Model.baza1;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
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
public abstract class AbstractAnalyteResult<T> extends AbstractAuditableObject<String> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Enumerated
	@Column(name="resultType", insertable=false, nullable=false, updatable=false)
	private IResultTypeAssociationCreator resultType;
	
	@ManyToOne(
			cascade = {CascadeType.MERGE, CascadeType.PERSIST},
			fetch = FetchType.LAZY
			)
	private Method method;
	
	@ManyToOne(
			cascade = {CascadeType.MERGE, CascadeType.PERSIST},
			fetch = FetchType.LAZY
			)
	private LabTestOrder<?> labTestOrder;
	
	
	protected AbstractAnalyteResult() {
		super();
	}
	
	protected AbstractAnalyteResult(LabTestOrder<?> labTestOrder, Method method) {
		super();
		this.setLabTestOrder(labTestOrder);
		this.setMethod(method);
	}
	
	abstract public T getResult();
	
	abstract public void setResult(T result);

	public Method getMethod() {
		return method;
	}

	protected void setMethod(Method method) {
		this.method = method;
		method.getAnalyteResult().add(this);
	}

	public LabTestOrder<?> getLabTestOrder() {
		return labTestOrder;
	}

	protected void setLabTestOrder(LabTestOrder<?> labTestOrder) {
		this.labTestOrder = labTestOrder;
		labTestOrder.getAnalyteResult().add(this);
	}

	public IResultTypeAssociationCreator getResultType() {
		return resultType;
	}

	public void setResultType(IResultTypeAssociationCreator resultType) {
		this.resultType = resultType;
	}
	
	
}
