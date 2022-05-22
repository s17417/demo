package base.Controllers.baza;

import java.security.InvalidKeyException;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import base.DTO.DTOObjectConstans;
import base.DTO.baza.TenantDTO;
import base.Services.baza.TenantAuthorizationService;
import base.Services.baza.TenantService;
import base.Services.baza.UserTenantService;


@Validated
@RestController
@RequestMapping(value="/tenant")
public class TenantController {
	

	@Autowired
	private TenantService tenantService;
	
	@Autowired
	private TenantAuthorizationService tenantAuthorizationService; 
	
	@Autowired
	private UserTenantService userTenantService;
	
	@PostMapping(
			value="/create",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public @ResponseBody ResponseEntity<Object> createTenant(@Validated(DTOObjectConstans.Create.class) @RequestBody TenantDTO tenantDTO){
		return new ResponseEntity<Object>(tenantService.createTenant(tenantDTO), HttpStatus.CREATED);
	}
	
	@PutMapping(
			value="/update",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public @ResponseBody ResponseEntity<Object> updateTenant(@Validated(DTOObjectConstans.Update.class) @RequestBody TenantDTO tenantDTO){
		return new ResponseEntity<Object>(tenantService.updateTenant(tenantDTO), HttpStatus.OK);
	}
	
	@DeleteMapping(
			value="/delete"
			)
	public @ResponseBody ResponseEntity<Object> deleteTenant(){
		tenantService.deleteTenant();
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(
			value="/get",
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public @ResponseBody ResponseEntity<Object> getTenant(){
		return new ResponseEntity<Object>(tenantService.getTenant(),HttpStatus.OK);
	}
	
	@PostMapping(value="/invite", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> inviteUser(@NotBlank @Email @RequestParam String email) throws InvalidKeyException, NullPointerException {
		tenantAuthorizationService.inviteUser(email);
		return ResponseEntity.created(null).build();
		
	}
	
	
	
	@GetMapping(
			value="/users",
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public @ResponseBody ResponseEntity<Object> getUsers(){
		return new ResponseEntity<Object>(userTenantService.getTenantUsers(),HttpStatus.OK);
	}
	
	@GetMapping(
			value = "/getRoles",
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public @ResponseBody ResponseEntity<Object> getRoles(){
		return new ResponseEntity<Object>(userTenantService.getRoles(), HttpStatus.OK);
	}
	
	@GetMapping(
			value = "/getAdminRoles",
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public @ResponseBody ResponseEntity<Object> getAdminRoles(){
		return new ResponseEntity<Object>(userTenantService.getAdminRoles(), HttpStatus.OK);
	}
	
	@DeleteMapping(
			value="/users/{userTenantRoleId}"
			)
	public @ResponseBody ResponseEntity<Object> deleteUserTenant(
			@PathVariable String userTenantRoleId
			){
		userTenantService.deleteUserTenant(userTenantRoleId);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

}
