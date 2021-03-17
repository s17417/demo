package base.Services.baza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import base.Model.baza.Tenant;
import base.Repository.BazaRepository.TenantRepository;

@Service
public class TenantDetailServiceImpl implements ITenantDetailService {
	
	@Autowired
	private TenantRepository tenantRepository;
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public Tenant getTenantByName(String name) {
		return tenantRepository.findByName(name);
	}
}
