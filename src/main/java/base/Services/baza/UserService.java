package base.Services.baza;

import java.net.InetAddress;
import java.security.InvalidKeyException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import base.DTO.DTOObjectConstans;
import base.DTO.baza.UserDTO;
import base.Model.baza.Invitation;
import base.Model.baza.Role;
import base.Model.baza.Tenant;
import base.Model.baza.Users;
import base.Model.baza.UsersTenantRole;
import base.Repository.BazaRepository.InvitationRepository;
import base.Repository.BazaRepository.TenantRepository;
import base.Repository.BazaRepository.UsersRepository;
import base.Services.IEmailSender;
import base.Utils.Jwt.IJWTUtil;
import base.Utils.Security.IJwtAuthenticationToken;
import base.Utils.Security.JwtAuthenticationToken;

@Service
public class UserService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UsersRepository userRepo;
	
	@Autowired
	private InvitationRepository invitationRepo;
	
	@Autowired
    private IEmailSender emailSender;
	
	@Autowired
	private IJWTUtil jwtToken;
	
	@Value("${server.address}")
	private InetAddress serverAdress;
	
	@Value("${server.port}")
	private String serverPort;
	
	@Autowired
	private UserTenantService userTenantService;
	
	private Logger logger=LoggerFactory.getLogger(UserService.class);
	
	
	public UserDTO createUserFromDTO(@NotNull UserDTO userDTO) throws InvalidKeyException, NullPointerException {
		var user = modelMapper
				.getTypeMap(UserDTO.class, Users.class, DTOObjectConstans.CREATE.name())
				.map(userDTO);
		System.out.println(user);
		var savedUser=userRepo.saveAndFlush(user);
		var invitations= invitationRepo.findByEmail(user.getEmail());
		
		if (invitations!=null) {
			invitations.getTenant()
			.stream()
			.forEach( tenant ->{
				new UsersTenantRole(savedUser, tenant, Role.SPECIFIC_DATABASE_INVITATION);
				tenant.removeInvitation(invitations);
			});
			if (invitations.getTenant().isEmpty()) invitationRepo.delete(invitations);
		}

		String text = "For account click to attached link: "+serverAdress.getHostAddress()+":"+serverPort+"/users/activate?token="+jwtToken.generateEmailActivationToken(savedUser);
		if (userRepo.findById(savedUser.getId()).isPresent())emailSender.sendEmailTo("tomasz.polawski@gmail.com", "probs", text);
		
		userDTO.setPassword(null);
		modelMapper.getTypeMap(Users.class, UserDTO.class).map(savedUser,userDTO);
		return userDTO;
	}
	
	/*@Transactional
	public UserDTO createUserFromDTO(@NotNull UserDTO userDTO) throws InvalidKeyException, NullPointerException {
		var user = modelMapper
				.getTypeMap(UserDTO.class, Users.class,DTOObjectConstans.CREATE.name())
				.map(userDTO);	
		var savedUser=userRepo.saveAndFlush(user);
		var invitations= invitationRepo.findByEmail(user.getEmail());
		
		if (invitations!=null) {
			invitations.getTenant()
			.stream()
			.forEach( tenant ->{
				new UsersTenantRole(savedUser, tenant, Role.SPECIFIC_DATABASE_INVITATION);
				tenant.removeInvitation(invitations);
			});
			if (invitations.getTenant().isEmpty()) invitationRepo.delete(invitations);
		}

		String text = "For account click to attached link: "+serverAdress.getHostAddress()+":"+serverPort+"/users/activate?token="+jwtToken.generateEmailActivationToken(savedUser);
		if (userRepo.findById(savedUser.getId()).isPresent())emailSender.sendEmailTo("tomasz.polawski@gmail.com", "probs", text);
		
		userDTO.setPassword(null);
		modelMapper.getTypeMap(Users.class, UserDTO.class).map(savedUser,userDTO);
		return userDTO;
	}
	
	*/
	public UserDTO updateUser(@NotNull UserDTO userDTO) throws IllegalArgumentException {
		var signedUserEmail=((AbstractAuthenticationToken)SecurityContextHolder.getContext().getAuthentication()).getName();
		var user = userRepo.findById(userDTO.getId());
		
		if (user.isEmpty())
			throw new NullPointerException("user: "+userDTO.getId()+" doesn't exists");
		if (!user.get().getEmail().contentEquals(signedUserEmail))
			throw new IllegalArgumentException("user: "+userDTO.getId()+" doesn't match signed user");
		
		modelMapper
			.getTypeMap(UserDTO.class, Users.class, DTOObjectConstans.UPDATE.name())
			.map(userDTO, user.get());	
		userRepo.save(user.get());
		
		userDTO.setPassword(null);
		modelMapper.getTypeMap(Users.class, UserDTO.class).map(user.get(),userDTO);
		return userDTO;
	}
	
	public UserDTO getUser() {
		var loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
		var user = userRepo.findByEmail(loggedUser);
		var userDTO = modelMapper.getTypeMap(Users.class, UserDTO.class).map(user);
		return userDTO;
	}
	
	@Transactional
	public void deleteUser() {
		var user = userRepo.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		List<String> list = user.getUsersTenantRole()
			.stream()
			.filter(e -> e.getRole().equals(Role.SPECIFIC_DATABASE_ADMIN))
			.map(e -> e.getTenant())
			.filter(e -> e.getUsersTenantRole().size()==1)
			.map(e -> e.getName())
			.collect(Collectors.toList());
		if (list.size()>0) throw new IllegalArgumentException("Last admin user for databases: "+list+". To delete firstly delete databases");
		userRepo.delete(user);
	}
	
	@Transactional
	public void activateUser(@NotBlank String token) throws NullPointerException{
		String userId=jwtToken.getEmailActivationClaims(token).getSubject();
		Optional<Users> user = userRepo.findById(userId);
		
		if (user.isPresent()) user.get().setEnabled(true);
		else {
			var ex=new NullPointerException("user: "+userId+" doesn't exists");
			logger.warn(ex.getMessage(),ex);
			throw ex;
		}
	}
	
	

}
