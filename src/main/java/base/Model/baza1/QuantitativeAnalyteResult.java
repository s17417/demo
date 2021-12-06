package base.Model.baza1;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.PositiveOrZero;

@Entity
//@Audited
@DiscriminatorValue("QUANTITATIVE_ANALYTE_RESULT")
public class QuantitativeAnalyteResult extends AbstractAnalyteResult<BigDecimal,QuantitativeFormatMethod> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@PositiveOrZero
	@Column(name="NumberResult", precision = 36, scale = 18)
	private BigDecimal result;
	
	protected QuantitativeAnalyteResult() {
		super();
	}

	protected QuantitativeAnalyteResult(LabTestOrder<?> labTestOrder, QuantitativeFormatMethod method) {
		super(labTestOrder, method);
	}

	@Override
	public BigDecimal getResult() {
		return result;
	}

	@Override
	public void setResult(BigDecimal result) {
		this.result=result;
	}

}
