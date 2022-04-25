package base.Model.baza1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import base.Model.AbstractPersistentClasses.AbstractPersistentObject;
import base.Model.AbstractPersistentClasses.IActiveObject;

@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class SpecimentType extends AbstractPersistentObject implements IActiveObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(length=60, nullable=false)
	@Size(min=2, max=60)
	private String speciment;
	
	@Column(nullable=false)
	private Boolean isActive=true;

	@Override
	public Boolean getIsActive() {
		return isActive;
	}

	@Override
	public void setIsActive(Boolean isActive) {
		this.isActive=isActive;
	}

	public String getSpeciment() {
		return speciment;
	}

	public void setSpeciment(String speciment) {
		this.speciment = speciment;
	}
}
