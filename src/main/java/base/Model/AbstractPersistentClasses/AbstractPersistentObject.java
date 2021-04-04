package base.Model.AbstractPersistentClasses;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import base.Utils.IdGenerator;

@MappedSuperclass
public abstract class AbstractPersistentObject implements IPersistentObject, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(length=60)
	@Access(AccessType.PROPERTY)
	private String Id=IdGenerator.getId().toString();
	
	@Version
	@Basic
	private LocalDateTime versionTimestamp;

	@Override
	public String getId() {
		return Id;
	}

	@Override
	public void setId(String id) {
		Id = id;
	}

	@Override
	public LocalDateTime getVersionTimestamp() {
		return versionTimestamp;
	}

	@Override
	public void setVersionTimestamp(LocalDateTime versionTimestamp) {
		this.versionTimestamp = versionTimestamp;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AbstractPersistentObject)) {
			return false;
		}
		AbstractPersistentObject other = (AbstractPersistentObject) obj;
		return Objects.equals(Id, other.Id);
	}

}
