package base.DTO;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * Implementation of Userdetails in form of subclass of class User with
 * additional implemented interface {@link MyUser}, addiotional field and methods
 * defining collection of tenants id.
 * @author Tomasz Poławski
 */
public class MyUserImpl extends User implements MyUser<String, List<String>> {
	
	private static final long serialVersionUID = -926670320669616619L;
	private Map<String,List<String>> tenantsId;

	public MyUserImpl(String username, String password, Map<String,List<String>> tenantsId, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		setTenantsId(tenantsId);
	}

	public MyUserImpl(String username, String password, Map<String,List<String>> tenantsId, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		setTenantsId(tenantsId);
	}

	/**
	 * Sets key-value pair for tenant-role list in relation to this user.
	 * @param tenantsId in form of map interface implementation. Key as String tenant id and value list of String role.
	 */
	@Override
	public void setTenantsId(Map<String,List<String>> tenantsId) {
		this.tenantsId=tenantsId;
	}

	@Override
	public Map<String,List<String>> getTenantsId() {
		return this.tenantsId;
	}
	
	
	
	public static MyUserImpl.MyUserBuilder myUserBuilder() {
		return new MyUserImpl.MyUserBuilder();
	}
	
	
	
	/**
	 * Builds the user to be added. At minimum the username, password, and authorities should provided. The remaining attributes have reasonable defaults
	 * @author Tomasz Poławski
	 *
	 */
	public static class MyUserBuilder {
		
		private Map<String,List<String>> tenantsId;
		private String username;
		private String password;
		private Collection<GrantedAuthority> authorities;
		private boolean accountExpired = false;
		private boolean accountLocked = false;
		private boolean credentialsExpired = false;
		private boolean disabled = false;
		private Function<String,String> encoder;
		
		/**
		 * Populates the username. This attribute is required.
		 * @param username the username. Cannot be null.
		 * @return the {@link #MyUserBuilder} for method chaining.
		 */
		public MyUserBuilder username(String username) {
		this.username=username;
			return this;
		}	
		
		/**
		 * Populates the password. This attribute is required.
		 * @param password the password. Cannot be null.
		 * @return the {@link #MyUserBuilder} for method chaining.
		 */
		public MyUserBuilder password(String password) {
			this.password = password;
			return this;
		}

		/**
		 * Populates the authorities. This attribute is required
		 * @param for this user Cannot be null, or contain null values
		 * @return the {@link #MyUserBuilder}r for method chaining.
		 * @see  {@link #roles(String)}.
		 */
		public MyUserBuilder authorities(Collection<GrantedAuthority> authorities) {
			this.authorities = authorities;
			return this;
		}

		/**
		 * Populates the authorities. This attribute is required
		 * @param authorities for this user Cannot be null, or contain null values
		 * @return the {@link #MyUserBuilder} for method chaining.
		 * @see  {@link #roles(String)}.
		 */
		public MyUserBuilder authorities(GrantedAuthority... authorities) {
			this.authorities = Arrays.asList(authorities);
			return this;
		}
		
		/**
		 * Populates the authorities. This attribute is required
		 * @param authorities for this user (i.e. ROLE_USER, ROLE_ADMIN,etc). Cannot be null, or contain null values
		 * @return the {@link #MyUserBuilder} for method chaining.
		 * @see {@link #roles(String)}.
		 */
		public MyUserBuilder authorities(String... authorities) {
			this.authorities = Arrays.stream(authorities)
					.map(role -> new SimpleGrantedAuthority(role))
					.collect(Collectors.toList());
			return this;
		}
		
		/**
		 * Defines the collection of tenants Id's .
		 * @param tenantsId a collection of tenants ID's.
		 * @return the {@link #MyUserBuilder} for method chaining
		 */
		public MyUserBuilder tenantsId(Map<String,List<String>> tenantsId) {
			this.tenantsId = tenantsId;
			return this;
		}
		
		/**
		 * Defines if the account is expired or not. Default is false.
		 * @param accountExpired true if the account is expired, false otherwise.
		 * @return the {@link #MyUserBuilder} for method chaining
		 */
		public MyUserBuilder accountExpired(boolean accountExpired){
			this.accountExpired=accountExpired;
			return this;
		}
		
		/**
		 * Defines if the account is locked or not. Default is false.
		 * @param accountLocked true if the account is locked, false otherwise.
		 * @return the{@link #MyUserBuilder} for method chaining
		 */
		public MyUserBuilder accountLocked(boolean accountLocked) {
			this.accountLocked=accountLocked;
			return this;
		}
		
		/**
		 * Defines if the credentials are expired or not. Default is false.
		 * @param Parameters:credentialsExpired true if the credentials are expired, false otherwise.
		 * @return the {@link #MyUserBuilder} for method chaining
		 */
		public MyUserBuilder credentialsExpired(boolean credentialsExpired) {
			this.credentialsExpired=credentialsExpired;
			return this;
		}
		
		/**
		 * Defines if the account is disabled or not Default is false.
		 * @param disabled true if the account is disabled, false otherwise
		 * @return the {@link #MyUserBuilder} for method chaining
		 */
		public MyUserBuilder disabled(boolean disabled) {
			this.disabled=disabled;
			return this;
		}
		
		/**
		 * Encodes the current password (if non-null) and any future passwords supplied to password(String).
		 * @param encoder the encoder to use
		 * @return the {@link #MyUserBuilder} for method chaining
		 */
		public MyUserBuilder passwordEncoder(Function<String, String> encoder) {
			this.encoder=encoder;
			return this;
		}
		
		/**
		 * Populates the roles. This method is a shortcut for calling authorities(String), but automatically prefixes each entry with"ROLE_". This means the following: builder.roles("USER","ADMIN"); is equivalent to builder.authorities("ROLE_USER","ROLE_ADMIN");
		 * This attribute is required, but can also be populated with authorities(String). 
		 * @param roles the roles for this user (i.e. USER, ADMIN, etc). Cannot be null,contain null values or start with "ROLE_"
		 * @return the {@link #MyUserBuilder} for method chaining.
		 */
		public MyUserBuilder roles(String... roles) {
			roles=(String[]) Arrays.stream(roles)
			.filter(role -> !role.isBlank())
			.map(role -> role.strip())
			.map(role -> "ROLE_"+role)
			.collect(Collectors.toList()).toArray();
			this.authorities(roles);
			return this;
		}

		
		
		public MyUserImpl build() {
			return new MyUserImpl(
					username,
					password!=null&&encoder!=null ? encoder.apply(password) : password,
					tenantsId,
					!disabled,
					!accountExpired,
					!credentialsExpired,
					!accountLocked,
					authorities
					);
		}
		
	}
	
	
	
}
