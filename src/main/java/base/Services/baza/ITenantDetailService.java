package base.Services.baza;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import base.Model.baza.Tenant;

public interface ITenantDetailService {

	Tenant getTenantByName(String name);

}