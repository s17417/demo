package base.Model.baza1;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
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
	
	public QuantitativeAnalyteResult() {
		super();
	}

	public QuantitativeAnalyteResult(LabTestOrder<?> labTestOrder, QuantitativeFormatMethod method) {
		super(labTestOrder, method);
	}

	@Override
	public BigDecimal getResult() {
		return this.result;
	}

	@Override
	public void setResult(BigDecimal result) {
		if (result==null) this.result=null;
		else
		this.result=transformData(result);
	}

	@Override
	public BigDecimal transformData(BigDecimal data) {
		var method = ( QuantitativeFormatMethod)this.getMethod(); 
		var nFormat = new DecimalFormat();
		System.out.println(method.getDecimalFormat());
		if (method.getDecimalFormat()!=null)nFormat.applyPattern(method.getDecimalFormat());
		if(method.getRoundingMode()!=null)nFormat.setRoundingMode(method.getRoundingMode());
		var separator = new DecimalFormatSymbols();
		separator.setDecimalSeparator('.');
		nFormat.setDecimalFormatSymbols(separator);
		var formated = nFormat.format(data);
		System.out.println(formated);
		return new BigDecimal(formated);
	}

}
