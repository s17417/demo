package base.Model.baza1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.envers.Audited;

import base.DTO.baza1.AnalyteResultDTO.QualitativeAnalyteResultDTO;

@Audited
@Entity
@DiscriminatorValue("QUALITATIVE_ANALYTE_RESULT")
@SQLDelete(sql = "UPDATE Method SET isActive=false WHERE id=? AND resultType=?")
public class QualitativeFormatMethod extends Method {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ElementCollection
	private List<String> resultTemplates= new ArrayList<>();
	
	
	
	public QualitativeFormatMethod() {
		super();
	}

	public QualitativeFormatMethod(@NotNull Analyte analyte, @NotNull LaboratoryTest laboratoryTest) {
		super(analyte, laboratoryTest);
	}

	public List<String> getResultTemplates() {
		return resultTemplates;
	}

	public void setResultTemplates(List<String> resultTemplates) {
		this.resultTemplates.clear();
		this.resultTemplates.addAll(resultTemplates);
	}

	@Override
	public  Optional<TextAnalyteResult> createAnalyteResult(LabTestOrder<?> labTestOrder) {
		return Optional.ofNullable(new TextAnalyteResult(labTestOrder, this));
	}

	@Override
	public  Optional<ControlTextAnalyteResult> creatControlTargetResult(LabTestOrder<?> labTestOrder) {
		return Optional.ofNullable(new ControlTextAnalyteResult(labTestOrder, this));
	}


	


}
