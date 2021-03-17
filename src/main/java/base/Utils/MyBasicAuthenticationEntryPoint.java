package base.Utils;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class MyBasicAuthenticationEntryPoint  implements AuthenticationEntryPoint {
	
	final ObjectMapper mapper = new ObjectMapper();
	
	@Override
    public void commence(
      HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx) 
      throws IOException {
		
		mapper.writeValue(response.getOutputStream(), createResponse(request, response, authEx));
		
    }
	
	protected Map<String,Object> createResponse( HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx) {
		final Map<String, Object> body = new LinkedHashMap<>();
		final String message = authEx.getCause().getClass().equals(ExpiredJwtException.class)?"token expired":"";
		
        response.addHeader("WWW-Authenticate","Bearer ");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        
        body.put("timestamp", Date.from(Instant.now()));
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        body.put("error", "Unauthorized");  
        body.put("message", message);
        body.put("path", request.getServletPath());
        return body;
	}

}

