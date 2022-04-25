package base.Model.baza1;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.envers.Audited;

@Entity
@Audited
@DiscriminatorValue("QUALITATIVE_ANALYTE_RESULT")
public class TextAnalyteResult extends AbstractAnalyteResult<String,QualitativeFormatMethod> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="TextResult")
	private String result;
	
	public TextAnalyteResult() {
		super();
	}

	public TextAnalyteResult(LabTestOrder<?> labTestOrder,QualitativeFormatMethod method) {
		super(labTestOrder, method);
	}


	@Override
	public String transformData(String data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResult() {
		return result;
	}

	@Override
	public void setResult(String result) {
		this.result=result;
		
	}

}
