package base.DTO.baza1.OrdersDTO;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

public class LabQualityControlDTO extends AbstractOrderDTO {

	@NotNull
	@Pattern(regexp = "^[\\p{IsAlphabetic}\\d]([\\p{IsAlphabetic}\\d]|[ ])*[\\p{IsAlphabetic}\\d]$", message="can contain 'aA-zZ', '0-9' an special characters '-' but not on the begining and ending of the word ")
	@Size(min=2, max=60)
	private String name;
	
	@Size(min=2, max=60)
	private String externalIdentificationCode;
	
	//@JsonDeserialize(using = LocalDateTimeDeserializer.class) 
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime expirationDate;
	
	//@JsonDeserialize(using = LocalDateTimeDeserializer.class) 
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime reportingDeadLine;
	
	@Size(min=2, max=255)
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExternalIdentificationCode() {
		return externalIdentificationCode;
	}

	public void setExternalIdentificationCode(String externalIdentificationCode) {
		this.externalIdentificationCode = externalIdentificationCode;
	}

	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}

	public LocalDateTime getReportingDeadLine() {
		return reportingDeadLine;
	}

	public void setReportingDeadLine(LocalDateTime reportingDeadLine) {
		this.reportingDeadLine = reportingDeadLine;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
