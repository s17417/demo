package base.Model.baza;

public enum AdminRole {

	/**
	 * This role allow user to sign in, view account details, crate database.
	 * This role isn't related  to any tenant, and allow only for creation a new tenant
	 */
	BASIC_USER,
	
	/**
	 * Highest privilege for app administration, user account, tenants etc.
	 */
	APP_ADMIN;
	
	
	@Override
	public String toString() {
		return "ROLE_"+super.toString();
	}
	
}
