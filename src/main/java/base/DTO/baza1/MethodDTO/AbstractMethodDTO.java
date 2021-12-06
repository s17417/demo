package base.DTO.baza1.MethodDTO;

import javax.validation.constraints.Size;

import base.DTO.baza1.ActiveObjectDTO;
import base.DTO.baza1.AnalyteDTO;
import base.DTO.baza1.LaboratoryTestDTO.LaboratoryTestDTO;

public class AbstractMethodDTO extends ActiveObjectDTO {

	@Size(min=2, max=180)
	private String analyticalMethodType;
	
	private Boolean printable;
	
	private AnalyteDTO analyteDTO;

	private LaboratoryTestDTO laboratoryTestDTO;

	public String getAnalyticalMethodType() {
		return analyticalMethodType;
	}

	public void setAnalyticalMethodType(String analyticalMethodType) {
		this.analyticalMethodType = analyticalMethodType;
	}

	public Boolean getPrintable() {
		return printable;
	}

	public void setPrintable(Boolean printable) {
		this.printable = printable;
	}

	public AnalyteDTO getAnalyteDTO() {
		return analyteDTO;
	}

	public void setAnalyteDTO(AnalyteDTO analyteDTO) {
		this.analyteDTO = analyteDTO;
	}

	public LaboratoryTestDTO getLaboratoryTestDTO() {
		return laboratoryTestDTO;
	}

	public void setLaboratoryTestDTO(LaboratoryTestDTO laboratoryTestDTO) {
		this.laboratoryTestDTO = laboratoryTestDTO;
	}
	
}
