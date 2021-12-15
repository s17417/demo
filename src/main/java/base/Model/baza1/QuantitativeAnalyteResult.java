package base.Model.baza1;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.envers.Audited;

@Entity
@Audited
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
		return this.result;
	}

	@Override
	public void setResult(BigDecimal result) {
		this.result=transformData(result);
	}

	@Override
	public BigDecimal transformData(BigDecimal data) {
		var method = this.getMethod();
		var nFormat = new DecimalFormat();
		if (method.getDecimalFormat()!=null)nFormat.applyPattern(method.getDecimalFormat());
		if(method.getRoundingMode()!=null)nFormat.setRoundingMode(method.getRoundingMode());
		var formated = nFormat.format(data);
		return new BigDecimal(formated);
	}

}
