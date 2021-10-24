package base.DTO.baza;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class InvitationDTO {
	

	@NotNull
	@Email
	private String email;

	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "InvitationDTO [email=" + email + "]";
	}
}
