package base.Model.baza1;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.envers.Audited;

import base.Model.AbstractPersistentClasses.AbstractActiveObject;

@Audited
@Entity
@SQLDelete(sql = "UPDATE METHOD SET ISACTIVE = false WHERE id = ?")
public class Method extends AbstractActiveObject<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Valid
	@NotNull
	@ManyToOne(
			cascade = {CascadeType.PERSIST,CascadeType.MERGE},
			fetch = FetchType.LAZY
			)
	@JoinColumn(nullable=false)
	private Analyte analyte;
	
	@Valid
	@NotNull
	@ManyToOne(
			cascade = {CascadeType.PERSIST,CascadeType.MERGE},
			fetch = FetchType.LAZY
			)
	@JoinColumn(nullable=false)
	private LaboratoryTest laboratoryTest;
	
	protected Method() {
		super();
	}
	
	public Method(@NotNull Analyte analyte, @NotNull LaboratoryTest laboratoryTest) {
		super();
		this.setAnalyte(analyte);
		this.setLaboratoryTest(laboratoryTest);
	}

	public Analyte getAnalyte() {
		return analyte;
	}

	protected void setAnalyte(Analyte analyte) {
		if (analyte ==null) return;
		this.analyte = analyte;
		analyte.getMethods().add(this);
	}

	public LaboratoryTest getLaboratoryTest() {
		return laboratoryTest;
	}

	protected void setLaboratoryTest(LaboratoryTest laboratoryTest) {
		if (laboratoryTest==null) return;
		this.laboratoryTest = laboratoryTest;
		this.laboratoryTest.getMethods().add(this);
	}
}
