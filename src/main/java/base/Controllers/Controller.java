package base.Controllers;


import java.security.InvalidKeyException;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import base.DemoApplication;
import base.DTO.MyUserImpl;
import base.Model.baza.Users;
import base.Model.baza.UsersTenantRole;
import base.Model.baza1.LabQualityControl;
import base.Model.baza1.LaboratoryTest;
import base.Model.baza1.Patient;
import base.Model.baza1.PatientOrder;
import base.Repository.Baza1Repository.PatientRepository;
import base.Repository.BazaRepository.UsersRepository;
import base.Services.baza.UserDetailServiceImpl;
import base.Utils.Jwt.IJWTUtil;

@RestController
public class Controller {
	
	@Autowired
	private UsersRepository uDAO;
	
	@Autowired
	private PatientRepository pDAO;
	
	@Autowired
	private UserDetailServiceImpl usrep;
	
	@Autowired
	private IJWTUtil tknGen;

	@GetMapping(value= "/", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Patient> helloString() {
		Patient p = new Patient();
		p.setName("lolek");
		p.setSurname("lolko");
		
		var labtest=new LaboratoryTest();
		var labq=new PatientOrder(p,null,null);
		labq.addLaboratoryTest(labtest);
		pDAO.save(p);
		//labtest.addQualityControlResult(null);
		return pDAO.findAll();
	}
	@GetMapping(value= "/tenant/")
	@ResponseBody
	public String generateTokenForTenant() {
		String token="error";
		try {
			token = tknGen.generateToken(usrep.loadUserByUsername("tomasz"), "default_schema1");
		} catch (InvalidKeyException e) {
			return e.getMessage();
			//e.printStackTrace();
		} catch (UsernameNotFoundException e) {
			return e.getMessage();
			//e.printStackTrace();
		}
		return token;
	}
	
	//set in abstact class or default interface method
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public Map<String, String> handleValidationExceptions(
		ConstraintViolationException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getConstraintViolations().forEach((error) -> {
	    	
	        String fieldName = error.getPropertyPath().toString();
	        String errorMessage = error.getMessage();
	        errors.put(fieldName, errorMessage);
	        
	    });
	    return errors;
	}
	
	//set in abstact class or default interface method
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(SQLException.class)
	public Map<Integer, String> handleSqlExceptions(
		SQLException ex) {
	    Map<Integer, String> errors = new HashMap<>();
	    //ex.getConstraintViolations().forEach((error) -> {
	    	
	        int sqlError = ex.getErrorCode();
	        String errorMessage = ex.getMessage();
	        errors.put(sqlError, errorMessage);
	        
	   // });
	    return errors;
	}
	
	@GetMapping(value= "/users/")
	public String helloStringos(@RequestParam String id) {
		
		//uDAO.save(u);
		
		return uDAO.findById(id).toString();
		//return "hello world2";
	}
	
	@PostMapping(value= "/users/")
	public String helloStringo(@RequestParam("token") String token) {
		return tknGen.getClaims(token).toString()+Date.from(Instant.now());
		//return "hello world2";
	}
	
	@GetMapping(value= "/restart/")
	public String restart() {
		DemoApplication.restart();
		return "restarted";
		//return "hello world2";
	}
}
