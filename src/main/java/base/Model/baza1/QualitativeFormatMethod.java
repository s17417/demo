package base.Model.baza1;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("QUALITATIVE_ANALYTE_RESULT")
public class QualitativeFormatMethod extends Method {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ElementCollection
	private List<String> resultTemplates;
	
	public QualitativeFormatMethod(@NotNull Analyte analyte, @NotNull LaboratoryTest laboratoryTest) {
		super(analyte, laboratoryTest);
	}

	public List<String> getResultTemplates() {
		return resultTemplates;
	}

	public void setResultTemplates(List<String> resultTemplates) {
		this.resultTemplates = resultTemplates;
	}

}
