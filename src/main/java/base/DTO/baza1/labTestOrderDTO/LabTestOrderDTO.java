package base.DTO.baza1.labTestOrderDTO;

import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import base.DTO.DTOObjectConstans;
import base.DTO.baza1.ActiveObjectDTO;
import base.DTO.baza1.LaboratoryTestDTO.LaboratoryTestDTO;
import base.Model.baza1.OrderStatus;
import base.Model.baza1.TatMode;

public abstract class LabTestOrderDTO extends ActiveObjectDTO {

	
	private OrderStatus labTestOrderStatus/*=OrderStatus.PENDING*/;
	
	
	@PastOrPresent
	@JsonDeserialize(using = LocalDateTimeDeserializer.class) 
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime collectionDate;
	
	@NotNull
	private TatMode tatMode/*= TatMode.RUTINE*/;
	
	@Size(min=2,max=512)
	private String resultDescription;
	
	@NotNull(groups = DTOObjectConstans.Create.class)
	private LaboratoryTestDTO laboratoryTest;

	public OrderStatus getLabTestOrderStatus() {
		return labTestOrderStatus;
	}

	public void setLabTestOrderStatus(OrderStatus labTestOrderStatus) {
		this.labTestOrderStatus = labTestOrderStatus;
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

	public LaboratoryTestDTO getLaboratoryTest() {
		return laboratoryTest;
	}

	public void setLaboratoryTest(LaboratoryTestDTO laboratoryTest) {
		this.laboratoryTest = laboratoryTest;
	}
	
	
	

}
