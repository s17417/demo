package base.Config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import base.Repository.BazaRepository.UsersRepository;
import base.Utils.Security.TenantTokenAuthenticationProvider;
import base.Utils.Security.TokenAuthenticationProvider;
import base.Utils.Security.Filters.JwtAuthenticationFilter;
import base.Utils.Security.Filters.JwtAuthorizationFilter;
import base.Utils.Security.Filters.JwtTenantAuthenticationFilter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity(debug=true)
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UsersRepository usRep;
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	BasicAuthenticationFilter jwtAuthorizationFilter;
	
	@Autowired
	private TokenAuthenticationProvider tokenAuthenticationProvider;
	
	@Autowired
	private TenantTokenAuthenticationProvider tenantTokenAuthenticationProvider;
	
	@Autowired
	private JwtTenantAuthenticationFilter jwtTenantAuthenticationFilter;
	
	@Autowired
	JwtAuthenticationFilter  jwtAuthenticationFilter;
	
    @Override
    public void configure(HttpSecurity web) throws Exception {
    	
    	web
    	.cors().and().csrf().disable()
    	.httpBasic().disable()
    	
    	.addFilterAt(jwtAuthorizationFilter,BasicAuthenticationFilter.class)
    	//.authorizeRequests((authz) -> authz.antMatchers("/users/create").authenticated())
    	.addFilterAfter(jwtAuthenticationFilter, JwtAuthorizationFilter.class)
    	.addFilterAfter(jwtTenantAuthenticationFilter, JwtAuthorizationFilter.class)
    	
    	//.requestMatchers().antMatchers("/users/create").and()
    	
    	.authorizeRequests(
    			auth -> auth
    			.antMatchers("/login/").permitAll()
    	    	.antMatchers("/users/create").permitAll()
    	    	.antMatchers("/users/activate").permitAll()
    	    	)  	
    	.authorizeRequests(
    			auth -> auth.anyRequest().authenticated())
    	//.anyRequest()
    	//.authenticated()
    	//.antMatchers("/users/update").hasRole("BASIC_USER")
    	
    	//.and()
    	.sessionManagement()
    	.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
    	//return new BCryptPasswordEncoder();
		return NoOpPasswordEncoder.getInstance();
    	
    }
    
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(tenantTokenAuthenticationProvider);
		auth.authenticationProvider(tokenAuthenticationProvider).userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		}
	
	@Bean
	public UserDetailsChecker jwtAuthenticationDetailsChecker() {
		return new AccountStatusUserDetailsChecker();
	}
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
