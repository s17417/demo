package base.Config;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.CollectionFactory;
import org.springframework.web.context.request.RequestContextListener;

import base.DTO.DTOObjectConstans;
import base.DTO.baza1.AddressDTO;
import base.DTO.baza1.CommentDTO;
import base.Model.baza1.Address;
import base.Model.baza1.PatientComment;
import base.Utils.Exceptions.EntityNotFoundException;


@Configuration
public class Config {
	
	@Bean
	public RequestContextListener requestContextListener(){
	    return new RequestContextListener();
	}

	@Bean
	public ModelMapper modelMapper() {
		var mapper=new ModelMapper();
		mapper.getConfiguration()
		  .setMatchingStrategy(MatchingStrategies.STRICT).setImplicitMappingEnabled(false);	
	    return mapper;
	}
	
	@Bean
	public Converter<String,String> toLowerCaseConverter(){ 
		return mapper -> mapper.getSource()!=null ? mapper.getSource().strip().toLowerCase() : null;
	}
	
	@Bean
	public Converter<String,String> toUpperCaseConverter(){ 
		return mapper -> mapper.getSource()!=null ? mapper.getSource().strip().toUpperCase() : null;
	}
	
	@Bean
	public Converter<String,String> firstLetterUpperCaseConverter(){
		return mapper -> mapper.getSource()!=null ? mapper.getSource().strip().substring(0, 1).toUpperCase() + mapper.getSource().strip().toLowerCase().substring(1) : null;
	}
	
	@Bean
	public Converter<String,String> stripConverter(){
		return mapper -> mapper.getSource()!=null ? mapper.getSource().strip():null;
	}
	
	@Bean
	public Converter <List<AddressDTO>,List<Address>> adressDtoListToEntityConverter(@Autowired @Lazy ModelMapper modelMapper){
		return mapper -> mapper.getSource()
				.stream()
				.map(obj -> modelMapper.getTypeMap(AddressDTO.class, Address.class, DTOObjectConstans.CREATE.name()).map(obj))
				.collect(Collectors.toList());
	}
	
	@Bean
	public Converter <List<CommentDTO>,List<PatientComment>> commentDtoSetToEntityConverter(@Autowired @Lazy ModelMapper modelMapper){
		return mapper -> mapper.getSource()
				.stream()
				.map(obj -> modelMapper.getTypeMap(CommentDTO.class, PatientComment.class, DTOObjectConstans.CREATE.name()).map(obj))
				.collect(Collectors.toList());
	}
	
	@Bean
	public Converter<BigDecimal,BigDecimal> stripTraillingZeroConverter(){
		return conv -> {
		var scale = conv.getSource().stripTrailingZeros().scale();
		return scale > 0 ? conv.getSource().stripTrailingZeros() : 
			 conv.getSource().setScale(0);
		};
	}
	
	
	@SuppressWarnings("unchecked")
	@Bean
	public <X,T extends Collection<X>, R extends Collection<X>> Converter<T,R> collectionTypeConverter(){
		return context -> (R) context.getSource()
				.stream()
				.collect(Collectors.toCollection(() -> CollectionFactory.createCollection(context.getDestinationType(), 0)));
	};
	
	@Bean
	public Condition<String, String> stringNotNullCondition(){
		return condition -> condition.getSource()!=null;
	}	
	
	@Bean
	public <T> Provider<T> laboratoryEntityProvider(@Autowired @Qualifier("laboratoryEntityManagerFactory") EntityManager entityManager) throws EntityNotFoundException {
		return p -> {
			try {
				return Optional.ofNullable(
						entityManager
						.find(p.getRequestedType(), p.getSource().toString()))
						.orElseThrow(() -> new EntityNotFoundException(p.getRequestedType()));
			}
			finally {
				entityManager.close();
				}
		};
	}
}
