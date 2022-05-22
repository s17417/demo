package base.DTO.baza1.AnalyteResultDTO;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import base.DTO.AuditableObjectDTO;
import base.DTO.baza1.MethodDTO.ListMethodDTO;

@JsonTypeInfo(
		  use = JsonTypeInfo.Id.NAME, 
		  include = JsonTypeInfo.As.PROPERTY, 
		  property = "type")
		@JsonSubTypes({ 
		  @Type(value = QuantitativeAnalyteResultDTO.class, name = "quantitative"), 
		  @Type(value = QualitativeAnalyteResultDTO.class, name = "qualitative"),
		  @Type(value = ControlQualitativeAnalyteResultDTO.class, name = "control_qualitative"),
		  @Type(value = ControlQuantitativeAnalyteResultDTO.class, name = "control_quantitative")
		})
public abstract class AbstractAnalyteResultDTO<T,M extends ListMethodDTO> extends AuditableObjectDTO {

	private M method;
	
	public M getMethod() {
		return method;
	}

	public void  setMethod(M method) {
		this.method = method;
	}
	
	abstract public T getResult();
	
	abstract public void setResult(T result);

}
