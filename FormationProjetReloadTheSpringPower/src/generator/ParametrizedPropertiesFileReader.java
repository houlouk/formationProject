package generator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParametrizedPropertiesFileReader {

	private PojoClassTemplate pojo;

	public ParametrizedPropertiesFileReader(PojoClassTemplate pojo) {
		this.pojo = pojo;
	}

	
	private List<Boolean> getWhenToShowUnitar(String value) {
		List<Boolean> booleanList = new ArrayList<>();
		booleanList.add(!pojo.isGenerateId() || !value.contains("(notGenerated)"));
		return booleanList;
	}

	private Matcher getRegexMatcher(String value, String regexList) {
		Pattern pattern = Pattern.compile(regexList);
		Matcher matcher = pattern.matcher(value);
		return matcher;
	}

	private String replaceAllInListValues(String value, Attribut att) {
		String lvalue = value.replace(ModelForShowConstants.ATT_TYPE, att.getType());

		lvalue = lvalue.replace(ModelForShowConstants.ATT_NAME, att.getName());
		lvalue = lvalue.replace(ModelForShowConstants.ATT_NAME_FIRST, firstLetterOnly(att.getName()));
		return lvalue;
	}

	private String replaceAllUnitarValues(String value) {
		String lvalue=value;
		if(pojo.getModelIdName() !=null && pojo.getModelIdType() != null){
		 lvalue = value.replace(ModelForShowConstants.MODEL_ID_NAME, pojo.getModelIdName());
		lvalue = lvalue.replace(ModelForShowConstants.MODEL_ID_TYPE, pojo.getModelIdType());
		lvalue = lvalue.replace(ModelForShowConstants.MODEL_ID_NAME_FIRST_CAP, firstLetterOnly(pojo.getModelIdName()));

		}

		lvalue = lvalue.replace(ModelForShowConstants.MODEL_CLASS, pojo.getModelClass());
		lvalue = lvalue.replace(ModelForShowConstants.MODEL_NAME, pojo.getModelClass().toLowerCase());
		return lvalue;
	}

	private String firstLetterOnly(String name) {
		// TODO Auto-generated method stub
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	public String getProperties(String templateString) {
		while(templateString.indexOf("[[")!=-1) {
			String replacement="";
			int debut= templateString.indexOf("[[");
			int fin=templateString.indexOf("]]");
			String  sub = templateString.substring(debut, fin+2);

			for(Attribut att:pojo.getFieldList()) {
			
			 replacement +=replaceAllInListValues(sub, att) +"\n";
			
//			System.out.println(testString.substring(debut, fin+2));
			
			}
			replacement=replacement.replace("[[","").replace("]]", "");
			templateString= templateString.replace(sub, replacement);
			System.out.println(replacement);
		}
		
		// TODO Auto-generated method stub
		return replaceAllUnitarValues(templateString);
	}

}
