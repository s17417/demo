package base.Model.baza1;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
//@Audited
public class TextAnalyteResult extends AbstractAnalyteResult<String,QualitativeFormatMethod> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="TextResult")
	private String result;
	
	protected TextAnalyteResult() {
		super();
	}

	public TextAnalyteResult(LabTestOrder<?> labTestOrder,QualitativeFormatMethod method) {
		super(labTestOrder, method);
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
