package base.Model.baza1;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Optional;

public enum ResultType implements IResultTypeAssociationCreator {
	
	QUALITATIVE_ANALYTE_RESULT(TextAnalyteResult.class),
	QUANTITATIVE_ANALYTE_RESULT(QuantitativeAnalyteResult.class),
	QUANTITATIVE_CONTROL_TARGET_ANALYTE_RESULT(null),
	QUALITATIVE_CONTROL_TARGET_ANALYTE_RESULT(null);
	
	private Class<?> value;

	private <T> ResultType(Class<T> class1) {
		this.value=class1;
	}

	public Class<?> getValue(){
		return  this.value;
	}
	
	@Override
	public Optional<AbstractAnalyteResult<?,?>> createAnalyteResult(LabTestOrder<?> labTestOrder, Method method) {
		try {
			Arrays.stream(method.getResultType().getValue().getConstructors())
			.forEach(obj -> {
				System.out.println(obj.getParameterCount());
				System.out.print(Arrays.asList(obj.getParameters()));
			}
			);
		
			
			return Optional.ofNullable(
					(AbstractAnalyteResult<?,?>) method.getResultType().getValue()
					.getConstructor(LabTestOrder.class, method.getClass())
					.newInstance(labTestOrder, method)
					);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
}
