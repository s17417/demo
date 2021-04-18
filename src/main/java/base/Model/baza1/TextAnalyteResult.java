package base.Model.baza1;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.envers.Audited;

@Entity
//@Audited
public class TextAnalyteResult extends AbstractAnalyteResult<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="TextResult")
	private String result;
	
	protected TextAnalyteResult() {
		super();
	}

	public TextAnalyteResult(LabTestOrder<?> labTestOrder, Method method) {
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
