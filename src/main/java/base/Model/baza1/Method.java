package base.Model.baza1;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@Enumerated
	@Column(nullable=false, updatable=false)
	private ResultType resultType;
	
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
	
	@OneToMany(
			cascade = {
					CascadeType.MERGE,
					CascadeType.PERSIST,
					CascadeType.REMOVE
					},
			fetch = FetchType.LAZY
			)
	private Set<AbstractAnalyteResult<?>> analyteResult = new HashSet<>();
	
	public Method() {
		super();
	}
	
	public Method(@NotNull Analyte analyte, @NotNull LaboratoryTest laboratoryTest) {
		super();
		this.setAnalyte(analyte);
		this.setLaboratoryTest(laboratoryTest);
	}
	
	public ResultType getResultType() {
		return resultType;
	}

	public void setResultType(@NotNull ResultType resultType) {
		this.resultType = resultType;
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

	public Set<AbstractAnalyteResult<?>> getAnalyteResult() {
		return analyteResult;
	}

	protected void setAnalyteResult(Set<AbstractAnalyteResult<?>> analyteResult) {
		this.analyteResult.clear();
		this.analyteResult.addAll(analyteResult);
	}
	
	
}
