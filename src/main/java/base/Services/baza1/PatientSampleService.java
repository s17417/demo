package base.Services.baza1;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import base.DTO.baza1.PatientSampleDTO.SimplePatientSampleDTO;
import base.Model.baza1.PatientOrder;
import base.Model.baza1.PatientSample;
import base.Repository.Baza1Repository.PatientOrderRepository;
import base.Repository.Baza1Repository.PatientSampleRepository;
import base.Utils.Exceptions.EntityNotFoundException;

@Service
public class PatientSampleService {
	
	private ModelMapper modelMapper;
	
	private PatientSampleRepository patientSampleRepository;
	
	private PatientOrderRepository patientOrderRepository;
	
	public SimplePatientSampleDTO create(
			@NotNull String patientOrderId,
			@NotNull SimplePatientSampleDTO patientSampleDTO
			) throws EntityNotFoundException {
		var patientOrder = patientOrderRepository
				.findById(patientOrderId)
				.orElseThrow(() -> new EntityNotFoundException(PatientOrder.class));		
		var patientSample = new PatientSample(patientOrder);
		modelMapper.map(patientSampleDTO, patientSample);
		patientSample = patientSampleRepository.save(patientSample);
		return modelMapper.map(patientSample, SimplePatientSampleDTO.class);
	}


}
