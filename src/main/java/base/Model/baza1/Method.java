package base.Model.baza1;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Basic;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DiscriminatorOptions;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.envers.Audited;

import base.Model.AbstractPersistentClasses.AbstractActiveObject;

@Audited
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorOptions(force = true)
@DiscriminatorColumn(name="resultType", 
discriminatorType = DiscriminatorType.STRING)
public abstract class Method extends AbstractActiveObject<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Column(length=180, nullable=false)
	@Size(min=2, max=180)
	private String analyticalMethodType;
	
	@Basic
	@Column
	@ColumnDefault("true")
	private Boolean printable;
	
	@Enumerated(EnumType.STRING)
	@Column(name ="resultType",nullable=false, updatable=false, insertable=false)
	private ResultType resultType;
	
	@NotNull
	@ManyToOne(
			cascade = {CascadeType.PERSIST,CascadeType.MERGE},
			fetch = FetchType.LAZY
			)
	@JoinColumn(nullable=false)
	private Analyte analyte;
	
	@NotNull
	@ManyToOne(
			cascade = {CascadeType.PERSIST,CascadeType.MERGE},
			fetch = FetchType.LAZY
			)
	@JoinColumn(nullable=false)
	private LaboratoryTest laboratoryTest;
	
	@OneToMany(
			cascade = {
					//CascadeType.MERGE,
					//CascadeType.PERSIST
					},
			fetch = FetchType.LAZY, mappedBy = "method"
			)
	private Set<AbstractAnalyteResult<?,?>> analyteResults = new HashSet<>();
	
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

	public void setAnalyte(Analyte analyte) {
		if (analyte ==null) return;
		this.analyte = analyte;
		analyte.getMethods().add(this);
	}

	public LaboratoryTest getLaboratoryTest() {
		return laboratoryTest;
	}

	public void setLaboratoryTest(LaboratoryTest laboratoryTest) {
		if (laboratoryTest==null) return;
		this.laboratoryTest = laboratoryTest;
		this.laboratoryTest.getMethods().add(this);
	}

	public Set<AbstractAnalyteResult<?,?>> getAnalyteResults() {
		return analyteResults;
	}

	protected <N,J extends Method> void setAnalyteResults(Set<AbstractAnalyteResult<N,J>> analyteResults) {
		this.analyteResults.clear();
		this.analyteResults.addAll(analyteResults);
	}

	public Boolean getPrintable() {
		return printable;
	}

	public void setPrintable(Boolean printable) {
		this.printable = printable;
	}

	public String getAnalyticalMethodType() {
		return analyticalMethodType;
	}

	public void setAnalyticalMethodType(String analyticalMethodType) {
		this.analyticalMethodType = analyticalMethodType;
	}


	public abstract Optional<? extends AbstractAnalyteResult<?,?>> createAnalyteResult(LabTestOrder<?> labTestOrder);
	
	public abstract Optional<? extends AbstractAnalyteResult<?,?>> creatControlTargetResult(LabTestOrder<?> labTestOrder);
	
	
	
	
}
