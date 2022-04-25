package base.Controllers;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import base.Utils.Exceptions.EntityNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@ExceptionHandler({MailException.class})
	protected ResponseEntity<Object> handleInternalServerError(MailException ex, WebRequest request){
		
		
		return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
		
	}
	
	@ExceptionHandler({EntityNotFoundException.class})
	protected ResponseEntity<Object> handleNullPointerException(EntityNotFoundException ex, WebRequest request){
		var headers = new HttpHeaders();
		Map<Integer, String> errors = new HashMap<>();
		headers.setContentType(MediaType.APPLICATION_JSON);
		errors.put(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		
		var body=createResponse(request, errors, HttpStatus.NOT_FOUND, ex);
		return handleExceptionInternal(ex, convertToJson(body), headers, HttpStatus.NOT_FOUND, request);		
	}
	
	@ExceptionHandler({NullPointerException.class})
	protected ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request){
		ex.printStackTrace();
		var headers = new HttpHeaders();
		Map<Integer, String> errors = new HashMap<>();
		headers.setContentType(MediaType.APPLICATION_JSON);
		errors.put(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		
		var body=createResponse(request, errors, HttpStatus.NOT_FOUND, ex);
		return handleExceptionInternal(ex, convertToJson(body), headers, HttpStatus.NOT_FOUND, request);		
	}
	
	@ExceptionHandler({IllegalArgumentException.class})
	protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request){
		var headers = new HttpHeaders();
		ex.printStackTrace();
		Map<Integer, String> errors = new HashMap<>();
		headers.setContentType(MediaType.APPLICATION_JSON);
		errors.put(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
		
		var body=createResponse(request, errors, HttpStatus.UNPROCESSABLE_ENTITY, ex);
		return handleExceptionInternal(ex, convertToJson(body), headers, HttpStatus.UNPROCESSABLE_ENTITY, request);		
	}
	
	@ExceptionHandler({ExpiredJwtException.class,JwtException.class})
	protected ResponseEntity<Object> handleInternalServerError(JwtException ex, WebRequest request){
		var headers = new HttpHeaders();
		Map<Integer, String> errors = new HashMap<>();
		headers.setContentType(MediaType.APPLICATION_JSON);
		errors.put(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
		
		var body=createResponse(request, errors, HttpStatus.UNPROCESSABLE_ENTITY, ex);
		return handleExceptionInternal(ex, convertToJson(body), headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		Map<String, String> errors = new HashMap<>();
		
		ex.getBindingResult().getAllErrors()
		.forEach((error) ->{
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		
		var body=createResponse(request, errors, HttpStatus.UNPROCESSABLE_ENTITY, ex);
		return handleExceptionInternal(ex, convertToJson(body), headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
		//return handleExceptionInternal(ex, convertToJson(errors), new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
	
		Map<Integer, String> errors = new HashMap<>();
		String message = ex.getMessage()+". Supported types: ";
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		List<MediaType> mediaTypes = ex.getSupportedMediaTypes();
		if (!CollectionUtils.isEmpty(mediaTypes)) {
			headers.setAccept(mediaTypes);
			message=message+mediaTypes.toString();
		}
		
		errors.put(status.value(), message);
		var body=createResponse(request, errors, status, ex);
		return handleExceptionInternal(ex, convertToJson(body), headers, status, request);		
	}
	
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	protected ResponseEntity<Object> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex, WebRequest request) {
		var headers = new HttpHeaders();
		Map<Integer, String> errors = new HashMap<>();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		errors.put(ex.getErrorCode(), ex.getMessage());
		var body=createResponse(request, errors, HttpStatus.UNPROCESSABLE_ENTITY, ex);
		return handleExceptionInternal(ex, convertToJson(body), headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
	}
	
	@ExceptionHandler(SQLException.class)
	protected ResponseEntity<Object> handleSQLException(SQLException ex, WebRequest request) {
		var headers = new HttpHeaders();
		Map<Integer, String> errors = new HashMap<>();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		errors.put(ex.getErrorCode(), ex.getMessage());
		var body=createResponse(request, errors, HttpStatus.CONFLICT, ex);
		return handleExceptionInternal(ex, convertToJson(body), headers, HttpStatus.CONFLICT, request);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstaintViolationException(ConstraintViolationException ex,WebRequest request) {
		var headers = new HttpHeaders();
		Map<String, String> errors = new HashMap<>();
		headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    ex.getConstraintViolations().forEach(
	    		(error) -> {
	    			errors.put(error.getPropertyPath().toString(), error.getMessage());
	    			}
	    		);
	    var body=createResponse(request, errors, HttpStatus.UNPROCESSABLE_ENTITY, ex);
	    return handleExceptionInternal(ex, convertToJson(body), headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
	}
	

	private String convertToJson(Map<?,?> map) {
		try {
			return objectMapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return map.toString();
		}		
	}
	
	protected Map<String,Object> createResponse( WebRequest request, Object message, HttpStatus status, Exception ex) {
		final Map<String, Object> body = new LinkedHashMap<>();
        
        body.put("timestamp", Date.from(Instant.now()));
        body.put("status", status.getReasonPhrase());
        body.put("error", ex.getClass().getSimpleName());  
        body.put("message", message);
        body.put("path", ServletUriComponentsBuilder.fromCurrentRequest().build().getPath());
        return body;
	}
	
}
