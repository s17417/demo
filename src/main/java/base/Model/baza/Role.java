package base.Model.baza;

import org.springframework.security.access.event.PublicInvocationEvent;

public enum Role {	
	
	/**
	 * This role allow user to sign in, view account details, crate database.
	 * This role isn't related  to any tenant, and allow only for creation a new tenant
	 */
	BASIC_USER,
	
	/**
	 * Highest privilege for app administration, user account, tenants etc.
	 */
	APP_ADMIN,
	
	/**
	 * Invited user by tenant admin, doesn't have any additional privileges
	 */
	SPECIFIC_DATABASE_INVITATION,
	
	/**
	 * can browse specific data without possibility to modify it
	 */
	SPECIFIC_DATABASE_VISITOR,
	
	/**
	 * Basic tenant user, with basic rights to modify, add data.
	 * No ability to sign result or delete data
	 */
	SPECIFIC_DATABASE_USER,
	
	/**
	 * Basic tenant user, with basic rights to modify, add data.
	 * No ability to sign result or delete data
	 */
	SPECIFIC_DATABASE_TECHNICHAN,
	
	/**
	 * Basic tenant user, with basic rights to modify, add data.
	 * Can sign result, and change value of signed data and delete data or add new users.
	 */
	SPECIFIC_DATABASE_SCIENTIST,
	
	/**
	 * All privileges with deleting and adding/deleting users
	 */
	SPECIFIC_DATABASE_ADMIN;

	/**
	 * Gets name of this role with added prefix "ROLE_" 
	 * @return String
	 */
	
	@Override
	public String toString() {
		return "ROLE_"+super.toString();
	}
}
