package baza1;

import org.junit.Assert;
import org.junit.Test;

import base.Model.baza1.PatientOrder;
import base.Model.baza1.Phisician;

public class PatientOrderTest {

	@Test
	public void testPatientPhisicianAssociation() {
		var pat = new PatientOrder(null,null,null);
		var phi = new Phisician();
		
		pat.setPhisician(phi);
		Assert.assertTrue(pat.getPhisician()==phi&&phi.getPatientOrders().contains(pat));
		pat.setPhisician(null);
		Assert.assertTrue(pat.getPhisician()==null&&!phi.getPatientOrders().contains(pat));
		
		phi.addPatientOrder(pat);
		Assert.assertTrue(pat.getPhisician()==phi&&phi.getPatientOrders().contains(pat));
		phi.removePatientOrder(pat);
		Assert.assertTrue(pat.getPhisician()==null&&!phi.getPatientOrders().contains(pat));	
	}
}
