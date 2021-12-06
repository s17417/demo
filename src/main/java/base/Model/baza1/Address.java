package base.Model.baza1;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Embeddable
public class Address implements Serializable /*extends AbstractPersistentObject*/ {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Pattern(regexp = "^[a-zA-Z0-9]([\\w]|[ -_.]){0,98}[a-zA-Z0-9]$")
	@Size(min=2, max=100)
	private String country;
	
	@Pattern(regexp = "^[a-zA-Z0-9]([\\w]|[ -_.]){0,98}[a-zA-Z0-9]$")
	@Size(min=2, max=100)
	private String state;
	
	@Pattern(regexp = "^[a-zA-Z0-9]([\\w]|[ -_.]){0,98}[a-zA-Z0-9]$")
	@Size(min=2, max=100)
	private String city;
	
	@Pattern(regexp = "^[a-zA-Z0-9]([\\w]|[ -_.]){0,98}[a-zA-Z0-9]$")
	@Size(min=2, max=100)
	private String street;
	
	@Pattern(regexp = "^[a-zA-Z0-9]([\\w]|[ -_.]){0,98}[a-zA-Z0-9]$")
	@Size(min=2, max=15)
	private String postalCode;
	
	public Address() {
	}

	public Address(String country, String state, String city, String street) {
		this.country = country;
		this.state = state;
		this.city = city;
		this.street = street;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
}
