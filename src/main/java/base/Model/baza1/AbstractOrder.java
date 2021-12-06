package base.Model.baza1;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;

import base.Model.AbstractPersistentClasses.AbstractAuditableObject;

@MappedSuperclass
@Audited
public abstract class AbstractOrder<T> extends AbstractAuditableObject<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@NotBlank
	@Size(min=4, max=128)
	@Pattern(regexp = "[a-zA-Z0-9]+")
	@Column(nullable = false, updatable = false, length = 128, unique = true)
	private String orderIdentification;
	
	abstract protected T createLabTestOrder(LaboratoryTest laboratoryTest);
	
	abstract protected void setLabTestOrders(@NotNull @Valid Set<T> labTestOrders);
	
	abstract public Set<T> getLabTestOrders();

	public String getOrderIdentification() {
		return orderIdentification;
	}

	public void setOrderIdentification(String orderIdentification) {
		this.orderIdentification = orderIdentification;
	}

}
