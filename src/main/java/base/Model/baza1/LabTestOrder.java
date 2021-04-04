package base.Model.baza1;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import base.Model.AbstractPersistentClasses.AbstractAuditableObject;

@Audited
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class LabTestOrder extends AbstractAuditableObject<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Valid
	@ManyToOne(
			cascade = {CascadeType.MERGE, CascadeType.PERSIST},
			fetch = FetchType.LAZY
			)
	@JoinColumn(nullable = false)
	private LaboratoryTest laboratoryTest;

	protected LabTestOrder() {
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
	
	
	
	
	/*private class LabTestOrderID implements Serializable {
		
	}*/
}
