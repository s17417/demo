package base.DTO.baza1.MethodDTO;

import java.util.List;

import javax.validation.Valid;

import base.DTO.baza1.AddressDTO;
import base.DTO.baza1.RefferentialRangeDTO;

public class ListQuantitativeFormatMethodDTO extends ListMethodDTO {

	private List<@Valid RefferentialRangeDTO> refferentialRangesDTO;
	
	private String units;

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public List<RefferentialRangeDTO> getRefferentialRangesDTO() {
		return refferentialRangesDTO;
	}

	public void setRefferentialRangesDTO(List<RefferentialRangeDTO> refferentialRangesDTO) {
		this.refferentialRangesDTO = refferentialRangesDTO;
	}

}

