package base.Model.baza1;

import java.util.Optional;

public interface IControlTypeAssociationCreator {
	
	Optional<AbstractAnalyteResult<?,?>> createAnalyteResult(LabTestOrder<?> labTestOrder, Method method);


}
