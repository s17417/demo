package base.Model.baza;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import base.Model.AbstractPersistentClasses.AbstractAuditableObject;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE,  region = "UsersTenantRoleCache")
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "tenant_id","user_id"/*,"role"*/}))
public class UsersTenantRole extends AbstractAuditableObject<String> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private Users user;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private Tenant tenant;
	
	@Enumerated(EnumType.STRING)
	@Column(length=50)
	private Role role;
	
	protected UsersTenantRole() {
		
	}

	public UsersTenantRole(@NotNull Users user, @NotNull Tenant tenant, @NotNull Role role) {
		this.setUser(user);
		this.setTenant(tenant);
		this.setRole(role);
	}
	
	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		if (user!=null && user.getUsersTenantRole().contains(this)) return;
		if (this.getUser()!=null) {
			this.getUser().getUsersTenantRole().remove(this);
			this.user=null;
		}	
		
		if (user!=null ) {
			user.getUsersTenantRole().add(this);
			this.user=user;
		}
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		if (tenant!=null && tenant.getUsersTenantRole().contains(this)) return;
		if (this.tenant!=null) {
			this.getTenant().getUsersTenantRole().remove(this);
			this.tenant=null;
		}	
		
		if (tenant!=null) {
			tenant.getUsersTenantRole().add(this);
			this.tenant=tenant;
		}
	}

	@Override
	public String toString() {
		return "UsersTenantRole [role=" + role + "]";
	}

	
	
	
}
