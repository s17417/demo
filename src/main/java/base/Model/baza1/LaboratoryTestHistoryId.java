package base.Model.baza1;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class LaboratoryTestHistoryId implements Serializable {
	    private String Id;

	    private String updateTimeStamp;

	    public LaboratoryTestHistoryId() {
	    	
		}
	    
		public LaboratoryTestHistoryId(String accountNumber, String accountType) {
	        this.Id = accountNumber;
	        this.updateTimeStamp = accountType;
	    }

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((Id == null) ? 0 : Id.hashCode());
			result = prime * result + ((updateTimeStamp == null) ? 0 : updateTimeStamp.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			LaboratoryTestHistoryId other = (LaboratoryTestHistoryId) obj;
			if (Id == null) {
				if (other.Id != null)
					return false;
			} else if (!Id.equals(other.Id))
				return false;
			if (updateTimeStamp == null) {
				if (other.updateTimeStamp != null)
					return false;
			} else if (!updateTimeStamp.equals(other.updateTimeStamp))
				return false;
			return true;
		}

		

}
