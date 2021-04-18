package base.Model.baza1;

import java.util.Set;

import javax.persistence.MappedSuperclass;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import base.Model.AbstractPersistentClasses.AbstractAuditableObject;

@MappedSuperclass
@Audited
public abstract class AbstractOrder<T> extends AbstractAuditableObject<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	abstract protected T createLabTestOrder(LaboratoryTest laboratoryTest);
	
	abstract protected void setLabTestOrders(@NotNull @Valid Set<T> labTestOrders);
	
	abstract public Set<T> getLabTestOrders();

}
