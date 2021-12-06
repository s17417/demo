package base.DTO.baza1.MethodDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

public class QuantitativeFormatMethodDTO extends AbstractMethodDTO {

	@Size(min=2, max=30)
	private String units;	
	
	@Digits(fraction = 18, integer = 18)
	private BigDecimal limitOfDetection;
	
	@Digits(fraction = 18, integer = 18)
	private BigDecimal limitOfQuantification;
	
	@Digits(fraction = 18, integer = 18)
	private BigDecimal sensitivity;
	
	@Size(min=2, max=255)
	private String decimalFormat;
	
	private RoundingMode roundingMode;

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
