package base.DTO.baza1.AnalyteResultDTO;

import base.DTO.baza1.MethodDTO.ListQualitativeFormatMethodDTO;

public class QualitativeAnalyteResultDTO extends AbstractAnalyteResultDTO<String, ListQualitativeFormatMethodDTO> {

	private String result;
	
	@Override
	public String getResult() {
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public void setResult(String result) {
		this.result=result;	
	}

}
