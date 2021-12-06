package base.DTO.baza1.PatientOrderDTO;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import base.DTO.AuditableObjectDTO;
import base.DTO.DTOObjectConstans;
import base.DTO.baza1.OrderingUnitDTO.OrderingUnitDTO;
import base.DTO.baza1.PatientDTO.SimplePatientDTO;
import base.DTO.baza1.PhisicianDTO.PhisicianDTO;

public class PatientOrderDTO extends AbstractOrderDTO {
	
	@NotNull(groups = {DTOObjectConstans.Create.class, DTOObjectConstans.Update.class})
	private SimplePatientDTO patient;
	
	private OrderingUnitDTO orderingUnit;
	
	private PhisicianDTO phisician;
	
	@PastOrPresent
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate orderDate;

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public SimplePatientDTO getPatient() {
		return patient;
	}

	public void setPatient(SimplePatientDTO patient) {
		this.patient = patient;
	}

	public OrderingUnitDTO getOrderingUnit() {
		return orderingUnit;
	}

	public void setOrderingUnit(OrderingUnitDTO orderingUnit) {
		this.orderingUnit = orderingUnit;
	}

	public PhisicianDTO getPhisician() {
		return phisician;
	}

	public void setPhisician(PhisicianDTO phisician) {
		this.phisician = phisician;
	}

}
