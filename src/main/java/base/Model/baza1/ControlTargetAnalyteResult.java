package base.Model.baza1;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("QUANTITATIVE_CONTROL_TARGET_ANALYTE_RESULT")
public class ControlTargetAnalyteResult extends QuantitativeAnalyteResult {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal standardDeviation;
	private BigDecimal lowerLimit;
	private BigDecimal upperLimit;

	public ControlTargetAnalyteResult() {
	}

	public ControlTargetAnalyteResult(LabTestOrder<?> labTestOrder, QuantitativeFormatMethod method) {
		super(labTestOrder, method);
	}

	public BigDecimal getStandardDeviation() {
		return standardDeviation;
	}

	public void setStandardDeviation(BigDecimal standardDeviation) {
		if (standardDeviation==null) this.standardDeviation=null;
		else
		this.standardDeviation=transformData(standardDeviation);
	}

	public BigDecimal getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(BigDecimal lowerLimit) {
		if (lowerLimit==null) this.lowerLimit=null;
		else
		this.lowerLimit=transformData(lowerLimit);
		this.lowerLimit = lowerLimit;
	}

	public BigDecimal getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(BigDecimal upperLimit) {
		if (upperLimit==null) this.upperLimit=null;
		else
		this.upperLimit=transformData(upperLimit);
		this.upperLimit = upperLimit;
	}
	
	
	
	

}
