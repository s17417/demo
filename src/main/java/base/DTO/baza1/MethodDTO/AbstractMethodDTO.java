package base.DTO.baza1.MethodDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import base.DTO.baza1.ActiveObjectDTO;
import base.DTO.baza1.AnalyteDTO.AnalyteDTO;

@JsonTypeInfo(
		  use = JsonTypeInfo.Id.NAME, 
		  include = JsonTypeInfo.As.PROPERTY, 
		  property = "type")
		@JsonSubTypes({ 
		  @Type(value = QuantitativeFormatMethodDTO.class, name = "quantitative"), 
		  @Type(value = QualitativeFormatMethodDTO.class, name = "qualitative") 
		})
public abstract class AbstractMethodDTO extends ActiveObjectDTO {

	@Size(min=2, max=180)
	private String analyticalMethodType;
	
	private Boolean printable;
	
	@NotNull
	private AnalyteDTO analyte;

	//private LaboratoryTestDTO laboratoryTest;

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

	public AnalyteDTO getAnalyte() {
		return analyte;
	}

	public void setAnalyte(AnalyteDTO analyte) {
		this.analyte = analyte;
	}

	
	

	/*public LaboratoryTestDTO getLaboratoryTest() {
		return laboratoryTest;
	}

	public void setLaboratoryTest(LaboratoryTestDTO laboratoryTest) {
		this.laboratoryTest = laboratoryTest;
	}*/	
}
