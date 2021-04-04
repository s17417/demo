package base.Model.baza1;

import javax.persistence.MappedSuperclass;

import org.hibernate.envers.Audited;

import base.Model.AbstractPersistentClasses.AbstractAuditableObject;

@MappedSuperclass
@Audited
public abstract class AbstractOrder extends AbstractAuditableObject<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

}
