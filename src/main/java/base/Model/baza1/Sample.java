package base.Model.baza1;

import java.time.LocalDateTime;
import java.util.Set;

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
import javax.persistence.MappedSuperclass;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Check;
import org.hibernate.envers.Audited;

import base.Model.AbstractPersistentClasses.AbstractAuditableObject;

@Audited
@Entity
//@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="sampleType", 
discriminatorType = DiscriminatorType.STRING)
@Check(constraints = "(sampleType='PATIENT' AND patientOrder_Id IS NOT NULL AND labQualityControl_Id IS NULL) "
		+ "OR (sampleType='CONTROL' AND labQualityControl_Id IS NOT NULL AND patientOrder_Id IS NULL)")
public abstract class Sample<T extends AbstractOrder<?>, R extends LabTestOrder<?>> extends AbstractAuditableObject<String> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Size(min = 2, max=60)
	@Column(nullable=false,updatable = false, unique = true)
	private String sampleId;
	
	@Enumerated(EnumType.STRING)
	@Column(name ="sampleType",nullable=false, updatable=false, insertable=false)
	private SampleType sampleType;
	
	@NotNull
	@JoinColumn(nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private SpecimentType specimentType;
	
	@PastOrPresent
	private LocalDateTime collectionDate;
	
	abstract public void setOrder(T order);
	
	abstract public T getOrder();
	
	abstract protected void setLabTestOrders(@NotNull @Valid Set<R> labTestOrders);
	
	abstract public Set<R> getLabTestOrders();

	abstract protected R createLabTestOrder(LaboratoryTest laboratoryTest);

	public String getSampleId() {
		return sampleId;
	}

	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}

	public SampleType getSampleType() {
		return sampleType;
	}

	public void setSampleType(SampleType sampleType) {
		this.sampleType = sampleType;
	}

	public SpecimentType getSpecimentType() {
		return specimentType;
	}

	public void setSpecimentType(SpecimentType specimentType) {
		this.specimentType = specimentType;
	}

	public LocalDateTime getCollectionDate() {
		return collectionDate;
	}

	public void setCollectionDate(LocalDateTime collectionDate) {
		this.collectionDate = collectionDate;
	}
	
	

}
