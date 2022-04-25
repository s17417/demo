package base.Config.Aspect;

import java.math.BigDecimal;

import org.aspectj.lang.annotation.Aspect;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import base.DTO.baza1.ActiveObjectDTO;
import base.DTO.baza1.AnalyteResultDTO.AbstractAnalyteResultDTO;
import base.DTO.baza1.AnalyteResultDTO.QualitativeAnalyteResultDTO;
import base.DTO.baza1.AnalyteResultDTO.QuantitativeAnalyteResultDTO;
import base.DTO.baza1.MethodDTO.AbstractMethodDTO;
import base.Model.AbstractPersistentClasses.AbstractActiveObject;
import base.Model.baza1.AbstractAnalyteResult;
import base.Model.baza1.Method;
import base.Model.baza1.QualitativeFormatMethod;
import base.Model.baza1.QuantitativeAnalyteResult;
import base.Model.baza1.TextAnalyteResult;

@Component
@Aspect
@Order(0)
public class AnalyteResultDtoConfiguration implements IDtoConfigurtion {

	private Converter<BigDecimal,BigDecimal> stripTraillingZeroConverter;

	public AnalyteResultDtoConfiguration(Converter<BigDecimal, BigDecimal> stripTraillingZeroConverter) {
		this.stripTraillingZeroConverter = stripTraillingZeroConverter;
	}

	@Override
	public void modelMapperConfiguration(ModelMapper mapper) {
		mapper
		.createTypeMap(AbstractAnalyteResult.class, AbstractAnalyteResultDTO.class)
		.implicitMappings();
		
		mapper.createTypeMap(QuantitativeAnalyteResult.class, AbstractAnalyteResultDTO.class)
		.setConverter( conv -> conv
				.getMappingEngine()
				.map(conv.create(conv.getSource(), QuantitativeAnalyteResultDTO.class)))
		.includeBase(AbstractAnalyteResult.class, AbstractAnalyteResultDTO.class)
		.implicitMappings();
		
		mapper.createTypeMap(QuantitativeAnalyteResult.class, QuantitativeAnalyteResultDTO.class)
		.addMappings(map -> {
			map.map(QuantitativeAnalyteResult::getMethod, QuantitativeAnalyteResultDTO::setMethod);
			map.using(stripTraillingZeroConverter).map(QuantitativeAnalyteResult::getResult, QuantitativeAnalyteResultDTO::setResult);
		}).implicitMappings();
		
		mapper.createTypeMap(TextAnalyteResult.class, AbstractAnalyteResultDTO.class)
		.setConverter( conv -> {
			return conv
				.getMappingEngine()
				.map(conv.create(conv.getSource(), QualitativeAnalyteResultDTO.class));})
		.includeBase(AbstractAnalyteResult.class, AbstractAnalyteResultDTO.class)
		.implicitMappings();
		//.implicitMappings();
		
		mapper.createTypeMap(TextAnalyteResult.class,QualitativeAnalyteResultDTO.class)
		.addMappings(map -> {
			map.map(TextAnalyteResult::getMethod, QualitativeAnalyteResultDTO::setMethod);
			map.map(TextAnalyteResult::getResult, QualitativeAnalyteResultDTO::setResult);
		})
		.implicitMappings();
		
		
		mapper.createTypeMap(AbstractAnalyteResultDTO.class, AbstractAnalyteResult.class);
		

		mapper.createTypeMap(QualitativeAnalyteResultDTO.class, AbstractAnalyteResult.class)
		.setConverter(conv -> conv.getMappingEngine().map(conv.create(QualitativeAnalyteResultDTO.class, TextAnalyteResult.class)));
		
		mapper.createTypeMap(QualitativeAnalyteResultDTO.class, TextAnalyteResult.class)
		.addMappings(map -> {
			map.map(QualitativeAnalyteResultDTO::getResult,TextAnalyteResult::setResult);
		})
		.includeBase(AbstractAnalyteResultDTO.class, AbstractAnalyteResult.class);
		
		mapper.createTypeMap(QuantitativeAnalyteResult.class, AbstractAnalyteResult.class)
		.setConverter(conv -> conv.getMappingEngine().map(conv.create(QuantitativeAnalyteResultDTO.class, QuantitativeAnalyteResult.class)));
		
		mapper.createTypeMap(QuantitativeAnalyteResultDTO.class, QuantitativeAnalyteResult.class)
		.addMappings(map -> {
			map.map(QuantitativeAnalyteResultDTO::getResult,QuantitativeAnalyteResult::setResult);
		})
		.includeBase(AbstractAnalyteResultDTO.class, AbstractAnalyteResult.class);
		
		
		
		
	}

}
