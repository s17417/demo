package base.Model.baza1;

import java.util.Optional;

public interface IResultTypeAssociationCreator {

	Optional<AbstractAnalyteResult<?,?>> createAnalyteResult(LabTestOrder<?> labTestOrder, Method method);

}