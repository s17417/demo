package base.Model.baza1;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@DiscriminatorValue("NUMBER")
public class QuantitativeFormatMethod extends Method {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(length=30)
	@Size(min=2, max=30)
	private String units;	
	
	@Column(precision = 36, scale = 18)
	@Basic
	private BigDecimal limitOfDetection;
	
	@Column(precision = 36, scale = 18)
	@Basic
	private BigDecimal limitOfQuantification;
	
	@Column(precision = 36, scale = 18)
	@Basic
	private BigDecimal sensitivity;
	
	@Column(length=255)
	@Size(min=2, max=255)
	private String decimalFormat;
	
	@Enumerated(EnumType.STRING)
	private RoundingMode roundingMode;
	
	public QuantitativeFormatMethod(@NotNull Analyte analyte, @NotNull LaboratoryTest laboratoryTest) {
		super(analyte, laboratoryTest);
		// TODO Auto-generated constructor stub
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public BigDecimal getLimitOfDetection() {
		return limitOfDetection;
	}

	public void setLimitOfDetection(BigDecimal limitOfDetection) {
		this.limitOfDetection = limitOfDetection;
	}

	public BigDecimal getLimitOfQuantification() {
		return limitOfQuantification;
	}

	public void setLimitOfQuantification(BigDecimal limitOfQuantification) {
		this.limitOfQuantification = limitOfQuantification;
	}

	public BigDecimal getSensitivity() {
		return sensitivity;
	}

	public void setSensitivity(BigDecimal sensitivity) {
		this.sensitivity = sensitivity;
	}

	public String getDecimalFormat() {
		return decimalFormat;
	}

	public void setDecimalFormat(String decimalFormat) {
		this.decimalFormat = decimalFormat;
	}

	public RoundingMode getRoundingMode() {
		return roundingMode;
	}

	public void setRoundingMode(RoundingMode roundingMode) {
		this.roundingMode = roundingMode;
	}
	
}
