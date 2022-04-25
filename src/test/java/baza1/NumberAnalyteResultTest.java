package baza1;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import base.Model.baza1.AbstractAnalyteResult;
import base.Model.baza1.Analyte;
import base.Model.baza1.LabQualityControl;
import base.Model.baza1.LabTestOrder;
import base.Model.baza1.LaboratoryTest;
import base.Model.baza1.Method;
import base.Model.baza1.QuantitativeAnalyteResult;
import base.Model.baza1.QuantitativeFormatMethod;
import base.Model.baza1.OrderResult;
import base.Model.baza1.PatientOrder;
import base.Model.baza1.ResultType;
import base.Model.baza1.TextAnalyteResult;

public class NumberAnalyteResultTest {
	
	@Test
	public void numberTest() {
		var m=new QuantitativeFormatMethod();
		var a = new Analyte();
		var test = new LaboratoryTest();
		m.setAnalyte(a);
		m.setLaboratoryTest(test);
		var l = new OrderResult();
		l.setLaboratoryTest(test);
		m.setResultType(ResultType.QUANTITATIVE_ANALYTE_RESULT);
				var z = l.createAnalyteResults();		
				z.stream().forEach(obj -> System.out.println(obj.getClass()));
		}
		
		
	}


