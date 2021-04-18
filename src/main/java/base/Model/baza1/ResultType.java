package base.Model.baza1;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public enum ResultType implements IResultTypeAssociationCreator {
	
	TEXT(TextAnalyteResult.class),
	NUMBER(NumberAnalyteResult.class);
	
	private Class<?> value;

	private <T> ResultType(Class<T> class1) {
		this.value=class1;
	}

	public Class<?> getValue(){
		return  this.value;
	}
	
	@Override
	public Optional<AbstractAnalyteResult<?>> createAnalyteResult(LabTestOrder<?> labTestOrder, Method method) {
		try {
			return Optional.ofNullable(
					(AbstractAnalyteResult<?>) method.getResultType().getValue()
					.getConstructor(LabTestOrder.class, Method.class)
					.newInstance(labTestOrder, method)
					);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
}
