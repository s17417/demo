package base.Controllers.baza;


import java.security.InvalidKeyException;

import javax.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import base.DTO.DTOObjectConstans;
import base.DTO.baza.UserDTO;
import base.DTO.baza.UserTenantRoleDTO;
import base.Services.baza.UserService;
import base.Services.baza.UserTenantService;

@Validated
@RestController
@RequestMapping(value="/users")
public class UsersController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserTenantService userTenantService;
	
	@PostMapping(
			value= "/create",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public @ResponseBody ResponseEntity<Object> createUser(@Validated(DTOObjectConstans.Create.class) @RequestBody UserDTO userDTO) throws InvalidKeyException, NullPointerException {
		userService.createUserFromDTO(userDTO);
		return new ResponseEntity<Object>(userDTO,HttpStatus.CREATED);
	}
	
	@PutMapping(
			value= "/update",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public @ResponseBody ResponseEntity<Object> updateUser(@Validated(DTOObjectConstans.Update.class) @RequestBody UserDTO userDTO) throws InvalidKeyException, NullPointerException {
		userService.updateUser(userDTO);
		return new ResponseEntity<Object>(userDTO,HttpStatus.OK);
	}
	
	@GetMapping(
			value= "/get",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public @ResponseBody ResponseEntity<Object> getUser() throws InvalidKeyException, NullPointerException {
		
		return new ResponseEntity<Object>(userService.getUser(),HttpStatus.OK);
	}
	
	@DeleteMapping(
			value = "/delete"
			)
	public @ResponseBody ResponseEntity<Object> deleteUser() throws InvalidKeyException, NullPointerException {
		userService.deleteUser();
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(
			value = "/getTenants",
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public @ResponseBody ResponseEntity<Object> getTenants() {
		return new ResponseEntity<Object>(userTenantService.getUserTenants(), HttpStatus.OK);
	}
	
	@GetMapping(
			value="/activate",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public @ResponseBody ResponseEntity<String> activateAccount(@NotBlank @RequestParam String token) {
		userService.activateUser(token);
		return new ResponseEntity<String>("User activated",HttpStatus.OK);
	}
	
	@PutMapping(
			value = "/updateRole",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public @ResponseBody  ResponseEntity<Object> updateUserRole(@Validated(DTOObjectConstans.Update.class) @RequestBody UserTenantRoleDTO updateUserTenantRoleDTO) {
		userTenantService.updateUserTenantRole(updateUserTenantRoleDTO);
		return new ResponseEntity<Object>(updateUserTenantRoleDTO, HttpStatus.OK);
	}

}
