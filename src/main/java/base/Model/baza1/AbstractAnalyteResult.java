package base.Model.baza1;

import javax.persistence.MappedSuperclass;

import base.Model.AbstractPersistentClasses.AbstractAuditableObject;

@MappedSuperclass
public abstract class AbstractAnalyteResult<T> extends AbstractAuditableObject<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	abstract public T getResult();
	
	abstract public void setResult(T result);

}
