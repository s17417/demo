package base.DTO.baza;

public class UpdateUserTenantRoleDTO {

	private String Id;
	
	private String role;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
