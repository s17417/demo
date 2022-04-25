package base.DTO.baza1.labTestOrderDTO;

import java.time.LocalDateTime;

import base.DTO.baza1.LaboratoryTestDTO.ListLaboratoryTestDTO;
import base.DTO.baza1.OrdersDTO.ListPatientOrderDTO;
import base.Model.baza1.TatMode;

public class ListOrderResultDTO {

	private String Id;
	
	private LocalDateTime cretionTimeStamp;
	
	private LocalDateTime updateTimeStamp;
	
	private LocalDateTime collectionDate;

	private TatMode tatMode;

	private String resultDescription;
	
	private ListLaboratoryTestDTO laboratoryTest;
	
	private ListPatientOrderDTO order;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public LocalDateTime getCretionTimeStamp() {
		return cretionTimeStamp;
	}

	public void setCretionTimeStamp(LocalDateTime cretionTimeStamp) {
		this.cretionTimeStamp = cretionTimeStamp;
	}

	public LocalDateTime getUpdateTimeStamp() {
		return updateTimeStamp;
	}

	public void setUpdateTimeStamp(LocalDateTime updateTimeStamp) {
		this.updateTimeStamp = updateTimeStamp;
	}

	public LocalDateTime getCollectionDate() {
		return collectionDate;
	}

	public void setCollectionDate(LocalDateTime collectionDate) {
		this.collectionDate = collectionDate;
	}

	public TatMode getTatMode() {
		return tatMode;
	}

	public void setTatMode(TatMode tatMode) {
		this.tatMode = tatMode;
	}

	public String getResultDescription() {
		return resultDescription;
	}

	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}

	public ListLaboratoryTestDTO getLaboratoryTest() {
		return laboratoryTest;
	}

	public void setLaboratoryTest(ListLaboratoryTestDTO laboratoryTest) {
		this.laboratoryTest = laboratoryTest;
	}

	public ListPatientOrderDTO getOrder() {
		return order;
	}

	public void setOrder(ListPatientOrderDTO order) {
		this.order = order;
	}

}
