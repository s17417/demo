package base.DTO.baza;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import base.DTO.AuditableObjectDTO;
import base.DTO.DTOObjectConstans;
import base.Model.AbstractPersistentClasses.AbstractPersistentObject;


public class UserTenantRoleDTO extends AuditableObjectDTO{
	
	/*private String userFirsname;
	
	private String userSurname;
	
	private String userEmail;
	
	public String getUserFirsname() {
		return userFirsname;
	}

	public void setUserFirsname(String userFirsname) {
		this.userFirsname = userFirsname;
	}

	public String getUserSurname() {
		return userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}*/

	/*@NotNull(groups = {DTOObjectConstans.Update.class})
	private String Id;*/
	
	@NotNull(groups = {DTOObjectConstans.Update.class})
	private String role;
	
	private UserDTO user;
	
	private TenantDTO tenant;
	
	/*public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}*/

	public UserDTO getUser() {
		return user;
	}

	public TenantDTO getTenant() {
		return tenant;
	}

	public void setTenant(TenantDTO tenant) {
		this.tenant = tenant;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}


