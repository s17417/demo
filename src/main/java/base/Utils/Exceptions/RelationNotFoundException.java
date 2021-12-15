package base.Utils.Exceptions;

public class RelationNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	

	public <T,V> RelationNotFoundException(String message, Class<T> entity1, Class<V> entity2) {
		super("Relation: "+entity1.getSimpleName()+"<->"+entity2.getSimpleName()+": "+message);
	}

	public <T,V> RelationNotFoundException (Class<T> entity1, Class<V> entity2) {
		super("Relation: "+entity1.getSimpleName()+"<->"+entity2.getSimpleName()+": Not Found");
	}
}
