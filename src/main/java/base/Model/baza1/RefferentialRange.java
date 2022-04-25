package base.Model.baza1;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Embeddable
public class RefferentialRange implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
	
	@NotNull
	private BigDecimal valueFrom;
	
	@NotNull
	private BigDecimal valueTo;
	
	@NotNull
	private Long fromAge;
	
	@NotNull
	private Long toAge;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;

	public BigDecimal getValueFrom() {
		return valueFrom;
	}

	public void setValueFrom(BigDecimal valueFrom) {
		this.valueFrom = valueFrom;
	}

	public BigDecimal getValueTo() {
		return valueTo;
	}

	public void setValueTo(BigDecimal valueTo) {
		this.valueTo = valueTo;
	}

	public Long getFromAge() {
		return fromAge;
	}

	public void setFromAge(Long fromAge) {
		this.fromAge = fromAge;
	}

	public Long getToAge() {
		return toAge;
	}

	public void setToAge(Long toAge) {
		this.toAge = toAge;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
} 
