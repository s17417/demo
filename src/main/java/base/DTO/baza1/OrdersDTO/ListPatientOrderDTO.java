package base.DTO.baza1.OrdersDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import base.DTO.baza1.OrderingUnitDTO.ListOrderingUnitDTO;
import base.DTO.baza1.PatientDTO.ListPatientDTO;

public class ListPatientOrderDTO {
	
	private String Id;
	
	private LocalDateTime cretionTimeStamp;
	
	private LocalDateTime updateTimeStamp;
	
	private String orderIdentificationCode;
	
	private LocalDate orderDate;
	
	private ListPatientDTO patient;
	
	private ListOrderingUnitDTO orderingUnit;

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

	public String getOrderIdentificationCode() {
		return orderIdentificationCode;
	}

	public void setOrderIdentificationCode(String orderIdentificationCode) {
		this.orderIdentificationCode = orderIdentificationCode;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public ListPatientDTO getPatient() {
		return patient;
	}

	public void setPatient(ListPatientDTO patient) {
		this.patient = patient;
	}

	public ListOrderingUnitDTO getOrderingUnit() {
		return orderingUnit;
	}

	public void setOrderingUnit(ListOrderingUnitDTO orderingUnit) {
		this.orderingUnit = orderingUnit;
	}
}
