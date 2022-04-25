package base.Model.baza1;

@FunctionalInterface
public interface IStateTransformation<X> {
	
	boolean transform(X newState);

}
