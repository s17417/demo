package base.DTO.baza1.AnalyteResultDTO;

import java.math.BigDecimal;

import base.DTO.baza1.MethodDTO.ListQuantitativeFormatMethodDTO;
import base.DTO.baza1.MethodDTO.QuantitativeFormatMethodDTO;

public class QuantitativeAnalyteResultDTO extends AbstractAnalyteResultDTO<BigDecimal, ListQuantitativeFormatMethodDTO> {

	private BigDecimal result;
	
	@Override
	public BigDecimal getResult() {
		return result;
	}

	@Override
	public void setResult(BigDecimal result) {
		this.result=result;
	}

	

}
