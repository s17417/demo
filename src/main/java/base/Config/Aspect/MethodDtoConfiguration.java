package base.Config.Aspect;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import org.aspectj.lang.annotation.Aspect;
import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import base.DTO.baza1.ActiveObjectDTO;
import base.DTO.baza1.RefferentialRangeDTO;
import base.DTO.baza1.AnalyteDTO.AnalyteDTO;
import base.DTO.baza1.MethodDTO.AbstractMethodDTO;
import base.DTO.baza1.MethodDTO.ListMethodDTO;
import base.DTO.baza1.MethodDTO.ListQualitativeFormatMethodDTO;
import base.DTO.baza1.MethodDTO.ListQuantitativeFormatMethodDTO;
import base.DTO.baza1.MethodDTO.QualitativeFormatMethodDTO;
import base.DTO.baza1.MethodDTO.QuantitativeFormatMethodDTO;
import base.DTO.baza1.PatientDTO.SimplePatientWithCollectionsDTO;
import base.Model.AbstractPersistentClasses.AbstractActiveObject;
import base.Model.baza1.Analyte;
import base.Model.baza1.LaboratoryTest;
import base.Model.baza1.Method;
import base.Model.baza1.Patient;
import base.Model.baza1.QualitativeFormatMethod;
import base.Model.baza1.QuantitativeFormatMethod;
import base.Model.baza1.RefferentialRange;
import base.Utils.Exceptions.EntityNotFoundException;

@Component
@Aspect
@Order(0)
public class MethodDtoConfiguration implements IDtoConfigurtion {
	
	private Condition<Object, Object> objectNotNullCondition;
	
	private Provider<Analyte> analyteProvider;
	
	private Provider<LaboratoryTest> laboratoryTestProvider;
	
	private Converter<List<String>,List<String>> collectionTypeConverter;
	
	private Converter<List<RefferentialRangeDTO>,List<RefferentialRange>> refferentialRangesDtoListToEntityConverter;
	
	private Converter<String,String>  toUpperCaseConverter;
	
	private Converter<String,String> stripConverter;
	
	private Converter<BigDecimal,BigDecimal> stripTraillingZeroConverter;
	
	
	


	public MethodDtoConfiguration(Condition<Object, Object> objectNotNullCondition, Provider<Analyte> analyteProvider,
			Provider<LaboratoryTest> laboratoryTestProvider,
			Converter<List<String>, List<String>> collectionTypeConverter,
			@Qualifier("refferentialRangesDtoListToEntityConverter") Converter<List<RefferentialRangeDTO>, List<RefferentialRange>> refferentialRangesDtoListToEntityConverter,
			Converter<String, String> toUpperCaseConverter, Converter<String, String> stripConverter,
			Converter<BigDecimal, BigDecimal> stripTraillingZeroConverter) {
		this.objectNotNullCondition = objectNotNullCondition;
		this.analyteProvider = analyteProvider;
		this.laboratoryTestProvider = laboratoryTestProvider;
		this.collectionTypeConverter = collectionTypeConverter;
		this.refferentialRangesDtoListToEntityConverter = refferentialRangesDtoListToEntityConverter;
		this.toUpperCaseConverter = toUpperCaseConverter;
		this.stripConverter = stripConverter;
		this.stripTraillingZeroConverter = stripTraillingZeroConverter;
	}





	@Override
	public void modelMapperConfiguration(ModelMapper mapper) throws EntityNotFoundException {
		mapper
		.createTypeMap(Method.class, AbstractMethodDTO.class)
		.includeBase(AbstractActiveObject.class, ActiveObjectDTO.class)
		.implicitMappings();
		
		mapper.createTypeMap(QuantitativeFormatMethod.class,AbstractMethodDTO.class)
		.setConverter(conv -> conv
				.getMappingEngine()
				.map(conv.create(conv.getSource(),QuantitativeFormatMethodDTO.class)))
		.implicitMappings();
		
		mapper.createTypeMap(QuantitativeFormatMethod.class,ListMethodDTO.class)
		.setConverter(conv -> conv
				.getMappingEngine()
				.map(conv.create(conv.getSource(),ListQuantitativeFormatMethodDTO.class)))
		.implicitMappings();
		
		mapper.createTypeMap(QualitativeFormatMethod.class,AbstractMethodDTO.class)
		.setConverter(conv -> conv
				.getMappingEngine()
				.map(conv.create(conv.getSource(),QualitativeFormatMethodDTO.class)))
		.implicitMappings();
		
		mapper.createTypeMap(QualitativeFormatMethod.class,ListMethodDTO.class)
		.setConverter(conv -> conv
				.getMappingEngine()
				.map(conv.create(conv.getSource(),ListQualitativeFormatMethodDTO.class)))
		.implicitMappings();
		
		mapper.createTypeMap(QuantitativeFormatMethod.class,QuantitativeFormatMethodDTO.class)
		.addMappings( map -> {
			map.using(stripTraillingZeroConverter).map(QuantitativeFormatMethod::getLimitOfDetection,QuantitativeFormatMethodDTO::setLimitOfDetection);
			map.using(stripTraillingZeroConverter).map(QuantitativeFormatMethod::getLimitOfQuantification,QuantitativeFormatMethodDTO::setLimitOfQuantification);
			map.using(stripTraillingZeroConverter).map(QuantitativeFormatMethod::getSensitivity,QuantitativeFormatMethodDTO::setSensitivity);
		})
		.includeBase(Method.class, AbstractMethodDTO.class)
		.implicitMappings();
		
		mapper.createTypeMap(QuantitativeFormatMethod.class,ListQuantitativeFormatMethodDTO.class)
		.implicitMappings();
		
		mapper.createTypeMap(QualitativeFormatMethod.class,QualitativeFormatMethodDTO.class)
		.includeBase(Method.class, AbstractMethodDTO.class)
		.implicitMappings();
		
		mapper.createTypeMap(QualitativeFormatMethod.class,ListQualitativeFormatMethodDTO.class)
		.implicitMappings();
		
		mapper.createTypeMap(AbstractMethodDTO.class, Method.class)
		.addMappings(map -> {
			map.when(objectNotNullCondition).with(analyteProvider).<Analyte>map(src -> src.getAnalyte().getId(), Method::setAnalyte);
			//map.with(laboratoryTestProvider).<LaboratoryTest>map(src -> src.getLaboratoryTest().getId(), Method::setLaboratoryTest);
			map.map(AbstractMethodDTO::getPrintable, Method::setPrintable);
			map.using(toUpperCaseConverter).map(AbstractMethodDTO::getAnalyticalMethodType, Method::setAnalyticalMethodType);
		});
		
		mapper.createTypeMap(QuantitativeFormatMethodDTO.class,QuantitativeFormatMethod.class).addMappings(map ->{
			map.when(objectNotNullCondition).using(refferentialRangesDtoListToEntityConverter).map(QuantitativeFormatMethodDTO::getRefferentialRanges, QuantitativeFormatMethod::setRefferentialRanges);
			map.map(QuantitativeFormatMethodDTO::getLimitOfDetection, QuantitativeFormatMethod::setLimitOfDetection);
			map.map(QuantitativeFormatMethodDTO::getLimitOfQuantification, QuantitativeFormatMethod::setLimitOfQuantification);
			map.map(QuantitativeFormatMethodDTO::getDecimalFormat, QuantitativeFormatMethod::setDecimalFormat);
			map.map(QuantitativeFormatMethodDTO::getRoundingMode, QuantitativeFormatMethod::setRoundingMode);
			map.map(QuantitativeFormatMethodDTO::getSensitivity, QuantitativeFormatMethod::setSensitivity);
			map.map(QuantitativeFormatMethodDTO::getUnits, QuantitativeFormatMethod::setUnits);
		}).includeBase(AbstractMethodDTO.class, Method.class);
		
		mapper.createTypeMap(QuantitativeFormatMethodDTO.class,Method.class)
		.setConverter(conv -> conv
				.getMappingEngine()
				.map(conv.create(conv.getSource(), QuantitativeFormatMethod.class))
				);
		
		mapper.typeMap(QualitativeFormatMethodDTO.class,QualitativeFormatMethod.class)
		.addMappings(map -> {
			map.using(collectionTypeConverter).map(QualitativeFormatMethodDTO::getResultTemplates, QualitativeFormatMethod::setResultTemplates);
		}).includeBase(AbstractMethodDTO.class, Method.class);
		
		mapper.createTypeMap(QualitativeFormatMethodDTO.class,Method.class)
		.setConverter(conv -> conv
				.getMappingEngine()
				.map(conv.create(conv.getSource(),QualitativeFormatMethod.class))
				);
	
	
	}
}
