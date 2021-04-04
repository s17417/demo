package base.Model.AbstractPersistentClasses;

import javax.persistence.Basic;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

@Audited
@MappedSuperclass
public class AbstractActiveObject<T> extends AbstractAuditableObject<T> implements IActiveObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Basic
	private Boolean isActive=true;

	@Override
	public Boolean getIsActive() {
		return isActive;
	}

	@Override
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
	
}
