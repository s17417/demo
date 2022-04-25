package base.DTO.baza1.MethodDTO;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import base.DTO.baza1.AnalyteDTO.ListAnalyteDTO;


@JsonTypeInfo(
		  use = JsonTypeInfo.Id.NAME, 
		  include = JsonTypeInfo.As.PROPERTY, 
		  property = "type")
		@JsonSubTypes({ 
		  @Type(value = ListQuantitativeFormatMethodDTO.class, name = "quantitative"), 
		  @Type(value = ListQualitativeFormatMethodDTO.class, name = "qualitative") 
		})
public abstract class ListMethodDTO {
	
	private String Id;

	private String analyticalMethodType;
	
	private Boolean printable;
	
	private ListAnalyteDTO analyte;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

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

	public ListAnalyteDTO getAnalyte() {
		return analyte;
	}

	public void setAnalyte(ListAnalyteDTO analyte) {
		this.analyte = analyte;
	}

}
