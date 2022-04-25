package base.Model.baza1;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;

import base.Model.AbstractPersistentClasses.AbstractAuditableObject;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Audited
@Table(
		   uniqueConstraints = {
		      @UniqueConstraint(
		          columnNames = {"orderIdentificationCode"},
		          name="orderIdentificationCode"
		      )
		   }
		)
public abstract class AbstractOrder<T> extends AbstractAuditableObject<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@NotNull
	@NotBlank
	@Size(min=4, max=128)
	@Pattern(regexp = "[a-zA-Z0-9]+")
	@Column(nullable = false, updatable = false, length = 128, unique = true)
	private String orderIdentificationCode;


	abstract protected void setSamples(@NotNull Set<T> patientSamples);
	
	abstract public Set<T> getSamples();
	
	abstract protected T createSample(T sample);
	
	//abstract protected T createLabTestOrder(LaboratoryTest laboratoryTest);
	
	//abstract protected void setLabTestOrders(@NotNull @Valid Set<T> labTestOrders);
	
	//abstract public Set<T> getLabTestOrders();

	public String getOrderIdentificationCode() {
		return orderIdentificationCode;
	}

	public void setOrderIdentificationCode(String orderIdentificationCode) {
		this.orderIdentificationCode = orderIdentificationCode;
	}

}
