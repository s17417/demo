package base.Utils.Exceptions;

public class EntityNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public <T> EntityNotFoundException(String message, Class<T> entity) {
		super(entity.getSimpleName()+": "+message);
	}
	
	public <T> EntityNotFoundException(Class<T> entity) {
		super(entity.getSimpleName()+": Not Found");
	}


}
