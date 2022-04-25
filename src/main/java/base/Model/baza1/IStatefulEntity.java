package base.Model.baza1;

public interface IStatefulEntity<E> {
	
	E getState();
	
	void setState(E newState);
	
	IStateTransformation<E> getTransformation();
}
