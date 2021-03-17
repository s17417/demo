package base.Model.baza1;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import base.Model.AbstractPersistentClasses.AbstractAuditableObject;
import base.Model.AbstractPersistentClasses.AbstractPersistentObject;

@Entity
@Audited
public class Patient extends AbstractAuditableObject<String> {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@Basic
	private String name;
	
	@Basic
	private String surname;
	
	public Patient() {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "Patient [getName()=" + getName() + ", getSurname()=" + getSurname() + ", getId()=" + getId() + "]";
	}
	
}
