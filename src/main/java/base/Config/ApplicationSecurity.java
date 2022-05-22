package base.Config;

import java.util.Arrays;
import java.util.Collections;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
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
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import base.Model.baza.Role;
import base.Repository.BazaRepository.UsersRepository;
import base.Utils.Security.TenantTokenAuthenticationProvider;
import base.Utils.Security.TokenAuthenticationProvider;
import base.Utils.Security.Filters.JwtAuthenticationFilter;
import base.Utils.Security.Filters.JwtAuthorizationFilter;
import base.Utils.Security.Filters.JwtTenantAuthenticationFilter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity(/*debug=true*/)
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
    	.addFilterAfter(jwtAuthenticationFilter, JwtAuthorizationFilter.class)
    	.addFilterAfter(jwtTenantAuthenticationFilter, JwtAuthorizationFilter.class)
    	
    	.authorizeRequests(
    			//USERS
    			auth -> auth
    	    	.antMatchers(
    	    			"/users/*",
    	    			"/users/getTenants",
    	    			"/users/*/acceptInvitation",
    	    			"/users/getTenant",
    	    			"/users/activate/*"
    	    			).permitAll()
    	    	.antMatchers("/users/updateRole").hasRole(Role.SPECIFIC_DATABASE_ADMIN.name())
    	    	.antMatchers(
    	    			"/tenant/create",
    	    			"/tenant/get"
    	    			).permitAll()
    	    	.antMatchers("/tenant/update","/tenant/delete","/tenant/users","/tenant/users/*").hasRole(Role.SPECIFIC_DATABASE_ADMIN.name())
    	    	.antMatchers(HttpMethod.DELETE,"/tenant/users/*").hasRole(Role.SPECIFIC_DATABASE_ADMIN.name())
    	    	.antMatchers("/users/**","/tenants/**").denyAll()
    	    	
    	    	//GET
    	    	.antMatchers(HttpMethod.GET,
    	    			"/lab/patients/**",
    	    			"/lab/patientOrders/**",
    	    			"/lab/laboratoryTests/all"
    	    			)
    	    	.hasRole(Role.SPECIFIC_DATABASE_VISITOR.name())
    	    	
    	    	.antMatchers(HttpMethod.GET, "/lab/**")
    	    	.hasRole(Role.SPECIFIC_DATABASE_USER.name())
    	    	
    	    	//POST
    	    	.antMatchers(HttpMethod.POST,
    	    			"/lab/patients/**",
    	    			"/lab/patientOrders/**",
    	    			"/lab/phisicians/**",
    	    			"/lab/orderingUnits/**",
    	    			"/lab/specimentTypes/**"
    	    			)
    	    	.hasRole(Role.SPECIFIC_DATABASE_USER.name())
    	    	
    	    	.antMatchers(HttpMethod.POST,
    	    			"/lab/labQualityControls/**"
    	    			)
    	    	.hasRole(Role.SPECIFIC_DATABASE_TECHNICIAN.name())
    	    	
    	    	//PUT
    	    	.antMatchers(HttpMethod.PUT,
    	    			"/lab/patients/**",
    	    			"/lab/patientOrders/*",
    	    			"/lab/patientOrders/*/patientSamples/*",
    	    			"/lab/patientOrders/*/patientSamples/*/ordersResults/*",
    	    			"/lab/phisicians/**",
    	    			"/lab/orderingUnits/**",
    	    			"/lab/specimentTypes/**"
    	    			)
    	    	.hasRole(Role.SPECIFIC_DATABASE_USER.name())
    	    	
    	    	.antMatchers(HttpMethod.PUT,
    	    			"/lab/labQualityControls/*",
    	    			"/lab/labQualityControls/*/controlSamples/*/controlResults/*",
    	    			"/lab/labQualityControls/*/controlSamples/*/controlResults/*/cancel",
    	    			"/lab/labQualityControls/*/controlSamples/*/controlResults/*/analyteResults/",
    	    			"/lab/patientOrders/*/patientSamples/*/ordersResults/*/cancel",
    	    			"/lab/patientOrders/*/patientSamples/*/ordersResults/*/analyteResults/"
    	    			)
    	    	.hasRole(Role.SPECIFIC_DATABASE_TECHNICIAN.name())
    	    	
    	    	.antMatchers(HttpMethod.PUT,
    	    			"/lab/patientOrders/*/patientSamples/*/ordersResults/*/accept",
    	    			"/lab/patientOrders/*/patientSamples/*/ordersResults/*/reject"
    	    			)
    	    	.hasRole(Role.SPECIFIC_DATABASE_SCIENTIST.name())
    	    	
    	    
    	    	//DELETE
    	    	.antMatchers(HttpMethod.DELETE,
    	    			"/lab/orderingUnits/**"
    	    			)
    	    	.hasRole(Role.SPECIFIC_DATABASE_USER.name())
    	    	
    	    	
    	    	.antMatchers(HttpMethod.DELETE,  "/lab/**")
    	    	.hasRole(Role.SPECIFIC_DATABASE_ADMIN.name())
    	    	.antMatchers(HttpMethod.PUT,  "/lab/**")
    	    	.hasRole(Role.SPECIFIC_DATABASE_ADMIN.name())
    	    	.antMatchers(HttpMethod.POST,  "/lab/**")
    	    	.hasRole(Role.SPECIFIC_DATABASE_ADMIN.name())
    	    	
    	    	
    	    	
    	    	.antMatchers("/lab/**").denyAll()
    	    	//hasRole(Role.SPECIFIC_DATABASE_USER.name())
    	    	//.antMatchers(HttpMethod.DELETE, "/lab/*").hasRole(Role.SPECIFIC_DATABASE_USER.name())
    	    	//.antMatchers(HttpMethod.GET, "/lab/*").hasRole(Role.SPECIFIC_DATABASE_USER.name())
    	    	)  	
    	/*.authorizeRequests(
    			auth -> auth
    			.anyRequest().authenticated()
    			
    			)*/
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
    
    
    
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        //config.setAllowCredentials(true);
        // Don't do this in production, use a proper list  of allowed origins
        config.setAllowedOriginPatterns(Collections.singletonList("*"));
        //config.setAllowedOrigins(Collections.singletonList("*"));
        config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept","Authorization"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
        //config.setAllowedMethods(Arrays.asList("*"));

        config.setExposedHeaders(Arrays.asList("Authorization"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
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
	
	@Bean
	public RoleHierarchy roleHierarchy() {
	    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
	    String hierarchy = "ROLE_SPECIFIC_DATABASE_ADMIN > ROLE_SPECIFIC_DATABASE_SCIENTIST "
	    		+ "\n ROLE_SPECIFIC_DATABASE_SCIENTIST > ROLE_SPECIFIC_DATABASE_TECHNICIAN "
	    		+ "\n ROLE_SPECIFIC_DATABASE_TECHNICIAN > ROLE_SPECIFIC_DATABASE_USER "
	    		+ "\n ROLE_SPECIFIC_DATABASE_USER > ROLE_SPECIFIC_DATABASE_VISITOR "
	    		+ "\n ROLE_SPECIFIC_DATABASE_VISITOR > ROLE_SPECIFIC_DATABASE_INVITATION";
	    roleHierarchy.setHierarchy(hierarchy);
	    return roleHierarchy;
	}
	
	
}
