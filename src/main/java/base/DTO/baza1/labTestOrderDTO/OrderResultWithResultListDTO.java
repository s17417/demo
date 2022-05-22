package base.DTO.baza1.labTestOrderDTO;

import java.util.List;

import base.DTO.baza1.AnalyteResultDTO.AbstractAnalyteResultDTO;
import base.DTO.baza1.PatientSampleDTO.PatientSampleDTO;
import base.DTO.baza1.PatientSampleDTO.SimplePatientSampleDTO;

public class OrderResultWithResultListDTO extends ListOrderResultDTO {
	
	//SimplePatientSampleDTO sample;

	List<AbstractAnalyteResultDTO<?,?>> analyteResults;
	

	/*public SimplePatientSampleDTO getSample() {
		return sample;
	}

	public void setSample(SimplePatientSampleDTO sample) {
		this.sample = sample;
	}
	*/
	public List<AbstractAnalyteResultDTO<?, ?>> getAnalyteResults() {
		return analyteResults;
	}

	public void setAnalyteResults(List<AbstractAnalyteResultDTO<?, ?>> analyteResults) {
		this.analyteResults = analyteResults;
	}

	
	
}
