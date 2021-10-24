package base.DTO.baza;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import base.DTO.DTOObjectConstans;
import base.DTO.DTOObjectConstans.Create;
import base.DTO.DTOObjectConstans.Update;

public class UserDTO {
	
	@NotBlank(groups = DTOObjectConstans.Update.class)
	@Null(groups = DTOObjectConstans.Create.class)
	private String Id;
	
	@NotBlank
	@Pattern(regexp = "(^[a-zA-Z0-9]([\\w]|[-]){0,58}[a-zA-Z0-9]$)", message="can contain 'aA-zZ', '0-9' an special characters '_-' but not on the begining and ending of the word ")
	@Size(min=2, max=60)
	private String firstname;
	
	@NotBlank
	@Pattern(regexp = "(^[a-zA-Z0-9]([\\w]|[-]){0,58}[a-zA-Z0-9]$)", message="can contain 'aA-zZ', '0-9' an special characters '_-' but not on the begining and ending of the word ")
	@Size(min=2, max=60)
	private String surname;
	
	@NotBlank(groups = DTOObjectConstans.Create.class)
	@Pattern(regexp = "^((?=.*[A-Z]+)(?=.*[\\p{Punct}]+).*[\\S]*)$")
	@Size(min=8, max=60)
	private String password;
	
	@NotBlank(groups = DTOObjectConstans.Create.class)
	@Email
	@Null(groups = DTOObjectConstans.Update.class)
	private String email;


	public String getId() {
		return Id;
	}

	public void setId(String Id) {
		this.Id = Id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserDTO [Id=" + Id + ", firstname=" + firstname + ", surname=" + surname + ", password=" + password
				+ ", email=" + email + "]";
	}

	

}
