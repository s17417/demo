package base.Model.AbstractPersistentClasses;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import base.Model.baza1.LaboratoryTest;
import base.Model.baza1.OrderResult;

@Audited
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditableObject<T> extends AbstractPersistentObject implements IAuditableObject<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@CreationTimestamp
	@Basic
	private LocalDateTime cretionTimeStamp;
	
	@UpdateTimestamp
	@Basic
	private LocalDateTime updateTimeStamp;
	
	@CreatedBy
	@Column(length=60)
	private T createdBy;
	
	@LastModifiedBy
	@Column(length=60)
	private T lastModifiedBy;

	@Override
	public LocalDateTime getCretionTimeStamp() {
		return cretionTimeStamp;
	}

	@Override
	public void setCretionTimeStamp(LocalDateTime cretionTimeStamp) {
		this.cretionTimeStamp = cretionTimeStamp;
	}

	@Override
	public LocalDateTime getUpdateTimeStamp() {
		return updateTimeStamp;
	}

	@Override
	public void setUpdateTimeStamp(LocalDateTime updateTimeStamp) {
		this.updateTimeStamp = updateTimeStamp;
	}

	@Override
	public T getCreatedBy() {
		return createdBy;
	}

	@Override
	public void setCreatedBy(T createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public T getLastModifiedBy() {
		return lastModifiedBy;
	}

	@Override
	public void setLastModifiedBy(T lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	
}
