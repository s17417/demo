package base.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

public class PersistenceObjectDTO {

	//@NotBlank(groups = DTOObjectConstans.Update.class)
	//@Null(groups = DTOObjectConstans.Create.class)
	private String Id;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

}
