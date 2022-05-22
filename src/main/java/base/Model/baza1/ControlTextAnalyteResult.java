package base.Model.baza1;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("QUALITATIVE_CONTROL_TARGET_ANALYTE_RESULT")
public class ControlTextAnalyteResult extends TextAnalyteResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ControlTextAnalyteResult() {
	}

	public ControlTextAnalyteResult(LabTestOrder<?> labTestOrder, QualitativeFormatMethod method) {
		super(labTestOrder, method);
	}

}
