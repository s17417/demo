package base.Model.baza;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import base.Model.AbstractPersistentClasses.AbstractPersistentObject;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "UsersCache")
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Users extends AbstractPersistentObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Basic
	@NotBlank
	@Pattern(regexp = "^[\\w\\.]*$")
	@Column(nullable=false, unique=true, length=60)
	@Size(min=2, max=60)
	@NaturalId
	private String name;
	
	@Basic
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]*$")
	@Column(nullable=false, length=60)
	@Size(min=2, max=60)
	private String firstname;
	
	@Basic
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]*$")
	@Column(nullable=false, length=60)
	@Size(min=2, max=60)
	private String surname;
	
	@Basic
	@Pattern(regexp = "^((?=.*[A-Z]+)(?=.*[\\p{Punct}]+).*[\\S]*)$", message="must contain at least one special character and one capitalized")
	@NotBlank
	@Column(nullable=false, length=60)
	@Size(min=8, max=60)
	private String password;
	
	@Basic
	@NotNull
	@Email
	@Column(nullable=false, unique=true, length=60)
	@Size(min=6, max=60)
	private String email;
	
	@NotNull
	@Column(nullable=false)
	@ColumnDefault("true")
	private Boolean enabled=true;
	
	@NotNull
	@Column(nullable=false)
	@ColumnDefault("true")
	private Boolean accountNonExpired=true;
	
	@NotNull
	@Column(nullable=false)
	@ColumnDefault("true")
	private Boolean credentialsNonExpired=true;
	
	@NotNull
	@Column(nullable=false)
	@ColumnDefault("true")
	private Boolean accountNonLocked=true;
	
	@CreationTimestamp
	@Basic
	private LocalDateTime creationTimeStamp;
	
	@UpdateTimestamp
	@Basic
	private LocalDateTime updateTimeStamp;
	
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "UsersTenantSetCache")
	@JsonIgnore
	@OneToMany(mappedBy="user",
			fetch=FetchType.LAZY,
			cascade = {CascadeType.REMOVE,CascadeType.PERSIST, CascadeType.MERGE},
			orphanRemoval = true)
	private Set<UsersTenantRole> usersTenantRole=new HashSet<>();
	
	public Users() {
		
	}

	public Set<UsersTenantRole> getUsersTenantRole() {
		return usersTenantRole;
	}

	public void setUsersTenantRole(Set<UsersTenantRole> usersTenantRole) {
		this.usersTenantRole.clear();
		this.usersTenantRole.addAll(usersTenantRole);
	}

	public void addTenant(@NotNull Tenant tenant, @NotNull Role role) {
		new UsersTenantRole(this, tenant, role);
	}
	
	public void removeTenant(@NotNull Tenant tenant) {
		this.getUsersTenantRole()
			.stream()
			.filter( e -> e.getTenant().equals(tenant))
			.collect(Collectors.toSet())
			.forEach( usr ->{
							usr.setUser(null);
							usr.setTenant(null);
							}
			);
	}

	public LocalDateTime getCreationTimeStamp() {
		return creationTimeStamp;
	}

	public void setCreationTimeStamp(LocalDateTime creationTimeStamp) {
		this.creationTimeStamp = creationTimeStamp;
	}

	public LocalDateTime getUpdateTimeStamp() {
		return updateTimeStamp;
	}

	public void setUpdateTimeStamp(LocalDateTime updateTimeStamp) {
		this.updateTimeStamp = updateTimeStamp;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public Boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public Boolean getAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	@Override
	public String toString() {
		return "Users [getName()=" + getName() + ", getPassword()=" + getPassword() + ", getId()=" + getId()
				+ ", getVersionTimestamp()=" + getVersionTimestamp() + "]";
	}
	
	
}
