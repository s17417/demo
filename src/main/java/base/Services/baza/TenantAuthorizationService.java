package base.Services.baza;

import java.security.InvalidKeyException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import base.Model.baza.Invitation;
import base.Model.baza.Role;
import base.Model.baza.Users;
import base.Model.baza.UsersTenantRole;
import base.Repository.BazaRepository.InvitationRepository;
import base.Repository.BazaRepository.TenantRepository;
import base.Repository.BazaRepository.UsersRepository;
import base.Repository.BazaRepository.UsersTenantRoleRepository;
import base.Services.IEmailSender;
import base.Utils.IDataSourceGenerator;
import base.Utils.Jwt.IJWTUtil;
import base.Utils.Security.JwtAuthenticationToken;

@Service
public class TenantAuthorizationService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
    private IEmailSender emailSender;
	
	@Autowired
	private UsersTenantRoleRepository usrTenantRoleRepo;
	
	@Autowired
	private UsersRepository userRepo;
	
	@Autowired
	private TenantRepository tenantRepo;
	
	@Autowired
	private InvitationRepository invitationRepo;
	
	@Autowired
	private IJWTUtil jwtToken;
	
	private final ExampleMatcher userMatcher;
	
	{
		List<String> list= Arrays.stream(Users.class.getDeclaredFields())
				.filter(p -> p.getName()!="email")
				.map(field -> field.getName())
				.collect(Collectors.toList());
		list.addAll(
				Arrays.stream(Users.class.getSuperclass().getDeclaredFields())
				.filter(p -> p.getName()!="email")
				.map(field -> field.getName())
				.collect(Collectors.toList())
				);
		
		userMatcher = ExampleMatcher
				.matching()
				.withIgnorePaths(list.toArray(new String[list.size()]))
				.withMatcher("email", ExampleMatcher.GenericPropertyMatcher.of(StringMatcher.EXACT, true));
	}
	
	public void inviteUser(@NotBlank @Email String email) throws InvalidKeyException, NullPointerException {
		Users exampleUser = new Users();
		exampleUser.setEmail(email);
		var example = Example.of(exampleUser,userMatcher);
		
		var user = (JwtAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
		var invitedUser = userRepo.findOne(example);
		var tenant = tenantRepo.findByName(user.getTenant());
		
		if (invitedUser.isPresent()) {
			var userTenantRole=new UsersTenantRole(invitedUser.get(), tenant,Role.SPECIFIC_DATABASE_INVITATION);
			usrTenantRoleRepo.save(userTenantRole);
		} 
		else {
			Invitation invitation=invitationRepo.findByEmail(email);
			if (invitation==null) {
				invitation = new Invitation();
				invitation.setEmail(email);	
				}
			tenant.addInvitation(invitation);
			invitationRepo.save(invitation);
		}
		
		String text = "Invitation by "+user.getName()+" to Laboratory account:"+tenant.getName()+". If you already have an account sign in to join the group else create an account";
		emailSender.sendEmailTo(email, "probs", text);		
	}

}
