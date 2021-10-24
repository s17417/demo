package base.Model.baza;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import base.Model.AbstractPersistentClasses.AbstractPersistentObject;

@Entity
public class Invitation extends AbstractPersistentObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Basic
	@NotNull
	@Email
	@Column(nullable=false, unique=true, length=60)
	@Size(min=6, max=60)
	@NaturalId
	private String email;
	
	
	@ManyToMany
	(mappedBy="invitation", fetch=FetchType.LAZY)
	private Set<Tenant> tenant = new HashSet<>();


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Set<Tenant> getTenant() {
		return tenant;
	}


	public void setTenant(Set<Tenant> tenant) {
		this.tenant.clear();
		this.tenant.addAll(tenant);
	}
	
}
