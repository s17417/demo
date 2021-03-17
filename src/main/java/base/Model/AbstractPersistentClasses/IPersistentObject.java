package base.Model.AbstractPersistentClasses;

import java.time.LocalDateTime;

public interface IPersistentObject {

	String getId();

	void setId(String id);

	LocalDateTime getVersionTimestamp();

	void setVersionTimestamp(LocalDateTime versionTimestamp);

}