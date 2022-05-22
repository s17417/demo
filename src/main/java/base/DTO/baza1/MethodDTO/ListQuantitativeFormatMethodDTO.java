package base.DTO.baza1.MethodDTO;

import java.util.List;

import javax.validation.Valid;

import base.DTO.baza1.AddressDTO;
import base.DTO.baza1.RefferentialRangeDTO;

public class ListQuantitativeFormatMethodDTO extends ListMethodDTO {

	private List<@Valid RefferentialRangeDTO> refferentialRanges;
	
	private String units;

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public List<RefferentialRangeDTO> getRefferentialRanges() {
		return refferentialRanges;
	}

	public void setRefferentialRanges(List<RefferentialRangeDTO> refferentialRanges) {
		this.refferentialRanges = refferentialRanges;
	}

}

