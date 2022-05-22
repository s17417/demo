package base.DTO.baza1.labTestOrderDTO;

import java.util.List;

import base.DTO.baza1.AnalyteResultDTO.AbstractAnalyteResultDTO;
import base.DTO.baza1.OrdersDTO.LabQualityControlDTO;
import base.Model.baza1.LabQualityControlResult;

public class LabQualityControlResultWithListDTO extends LabQualityControlResultDTO {

	List<AbstractAnalyteResultDTO<?,?>> analyteResults;
	
	LabQualityControlDTO  order;
	
	private LabQualityControlResultWithListDTO parentTargetValue;

	public List<AbstractAnalyteResultDTO<?, ?>> getAnalyteResults() {
		return analyteResults;
	}

	public void setAnalyteResults(List<AbstractAnalyteResultDTO<?, ?>> analyteResults) {
		this.analyteResults = analyteResults;
	}

	public LabQualityControlDTO getOrder() {
		return order;
	}

	public void setOrder(LabQualityControlDTO order) {
		this.order = order;
	}

	public LabQualityControlResultWithListDTO getParentTargetValue() {
		return parentTargetValue;
	}

	public void setParentTargetValue(LabQualityControlResultWithListDTO parentTargetValue) {
		this.parentTargetValue = parentTargetValue;
	}

	
	
	

}
