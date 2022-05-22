package base.DTO.baza1.AnalyteResultDTO;

import java.math.BigDecimal;

public class ControlQuantitativeAnalyteResultDTO extends QuantitativeAnalyteResultDTO {
	
	private BigDecimal standardDeviation;
	private BigDecimal lowerLimit;
	private BigDecimal upperLimit;
	
	public BigDecimal getStandardDeviation() {
		return standardDeviation;
	}
	public void setStandardDeviation(BigDecimal standardDeviation) {
		this.standardDeviation = standardDeviation;
	}
	public BigDecimal getlowerLimit() {
		return lowerLimit;
	}
	public void setLowerLimit(BigDecimal lowerLimit) {
		this.lowerLimit = lowerLimit;
	}
	public BigDecimal getUpperLimit() {
		return upperLimit;
	}
	public void setUpperLimit(BigDecimal upperLimit) {
		this.upperLimit = upperLimit;
	}
	
	


}
