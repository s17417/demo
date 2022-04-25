package base.Model.baza1;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
public class LaboratoryTestHistory {
	
	@EmbeddedId
	private LaboratoryTestHistoryId labboratoryTestHistoryId;
	
	@MapsId("Id")
	@ManyToOne
	private LaboratoryTest laboratoryTest;
	
	@CreationTimestamp
	@Basic
	private LocalDateTime cretionTimeStamp;
	
	
	
	@Column(length=180)
	private String name;
	
	@Column(length=30)
	private String shortName;
	
	@Column(length=255)
	private String description;
	
	
	
}


