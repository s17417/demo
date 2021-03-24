package base.Model.baza;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;

import base.Model.AbstractPersistentClasses.AbstractAuditableObject;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "TenantCache")
@Entity
public class Tenant extends AbstractAuditableObject<String> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Basic
	@Column(nullable=false, unique=true, length=60)
	@Size(min=2, max=60)
	@NaturalId
	private String name;
	
	@Basic
	@Column(nullable=false, length=120)
	private String driverClassName="com.mysql.cj.jdbc.Driver";
	
	@Basic
	@Column(nullable=false, length=180)
	private String sqlServerUrl="jdbc:mysql://localhost:3306";
	
	@OneToMany(mappedBy="tenant",
			fetch=FetchType.LAZY, 
			cascade = {CascadeType.REMOVE,CascadeType.PERSIST, CascadeType.MERGE},
			orphanRemoval = true)
	private Set<UsersTenantRole> usersTenantRole=new HashSet<>();
	
	@Basic
	@Column(nullable=false, length=60)
	@Size(min=2, max=60)
	private String databseUserName;
	
	@Basic
	@Column(nullable=false, length=60)
	@Size(min=2, max=60)
	private String databasePassword;
	
	@Basic
	@Column(nullable=false, length=180)
	private String dialect="org.hibernate.dialect.MySQL8Dialect";
	
	@Basic
	@Column(nullable=false, length=180)
	private String ddlGeneration="create-drop";

	public Tenant() {
	}
	
	/**
	 * @return the dialect
	 */
	public String getDialect() {
		return dialect;
	}

	/**
	 * @param dialect the dialect to set
	 */
	public void setDialect(String dialect) {
		this.dialect = dialect;
	}

	/**
	 * @return the ddlGeneration
	 */
	public String getDdlGeneration() {
		return ddlGeneration;
	}

	/**
	 * @param ddlGeneration the ddlGeneration to set
	 */
	public void setDdlGeneration(String ddlGeneration) {
		this.ddlGeneration = ddlGeneration;
	}

	/**
	 * @return the databseUserName
	 */
	public String getDatabseUserName() {
		return databseUserName;
	}

	/**
	 * @param databseUserName the databseUserName to set
	 */
	public void setDatabseUserName(String databseUserName) {
		this.databseUserName = databseUserName;
	}

	/**
	 * @return the databaseFullUrl
	 */
	@Basic
	@Transient
	public String getDatabaseFullUrl() {
		return sqlServerUrl+"/"+this.name;
	}

	
	
	/**
	 * @return the driverClassName
	 */
	public String getDriverClassName() {
		return driverClassName;
	}


	/**
	 * @param driverClassName the driverClassName to set
	 */
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}


	/**
	 * @return the sqlServerUrl
	 */
	public String getSqlServerUrl() {
		return sqlServerUrl;
	}


	/**
	 * @param sqlServerUrl the sqlServerUrl to set
	 */
	public void setSqlServerUrl(String sqlServerUrl) {
		this.sqlServerUrl = sqlServerUrl;
	}


	/**
	 * @return the databasePassword
	 */
	public String getDatabasePassword() {
		return databasePassword;
	}


	/**
	 * @param databasePassword the databasePassword to set
	 */
	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}


	public Set<UsersTenantRole> getUsersTenantRole() {
		return usersTenantRole;
	}

	public void setUsersTenantRole(Set<UsersTenantRole> usersTenantRole) {
		this.usersTenantRole.clear();
		this.usersTenantRole.addAll(usersTenantRole);
	}
	
	public void addUser(@NotNull Users user, @NotNull Role role) {
		new UsersTenantRole(user, this, role);
	}
	
	public void removeUser(@NotNull Users user) {
		this.getUsersTenantRole()
			.stream()
			.filter( e -> e.getUser().equals(user))
			.collect(Collectors.toSet())
			.forEach( usr ->{
							usr.setUser(null);
							usr.setTenant(null);
							}
			);
	}

	public String getName() {
		return name;
	}

	/**
	 * Sets up unique database name, 2-60 chars. 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Tenant [getName()=" + getName() + ", getId()=" + getId() + "]";
	}
	
}
