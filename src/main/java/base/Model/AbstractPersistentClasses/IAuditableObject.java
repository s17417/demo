package base.Model.AbstractPersistentClasses;

import java.time.LocalDateTime;

public interface IAuditableObject<T> {

	LocalDateTime getCretionTimeStamp();

	void setCretionTimeStamp(LocalDateTime cretionTimeStamp);

	LocalDateTime getUpdateTimeStamp();

	void setUpdateTimeStamp(LocalDateTime updateTimeStamp);

	T getCreatedBy();

	void setCreatedBy(T createdBy);

	T getLastModifiedBy();

	void setLastModifiedBy(T lastModifiedBy);

}