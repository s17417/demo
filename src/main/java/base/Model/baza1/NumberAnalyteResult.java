package base.Model.baza1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.envers.Audited;

@Entity
//@Audited
public class NumberAnalyteResult extends AbstractAnalyteResult<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PositiveOrZero
	@Column(name="NumberResult")
	private Long result;
	
	protected NumberAnalyteResult() {
		super();
	}

	public NumberAnalyteResult(LabTestOrder<?> labTestOrder, Method method) {
		super(labTestOrder, method);
	}





	@Override
	public Long getResult() {
		return result;
	}

	@Override
	public void setResult(Long result) {
		this.result=result;
	}

}
