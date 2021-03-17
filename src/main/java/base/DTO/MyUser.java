package base.DTO;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;



/**
 * Interface declaring getters and setters for tennats Id's
 * @author Tomasz Poławski
 *
 * @param <T>
 */
public interface MyUser<K,V> extends UserDetails {
	
	/**
	 * sets tenants Id as Collection
	 * @param tenantsId
	 */
	public void setTenantsId (Map<K,V> tenantsId);
	
	/**
	 * gets tenants Id as Collection
	 * @return
	 */
	public Map<K,V> getTenantsId ();
	
}
