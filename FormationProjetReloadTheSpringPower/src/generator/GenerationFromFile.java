package generator;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class GenerationFromFile implements ParcourableString {

	private String templateFile;
	
	 public GenerationFromFile(String templateFile) {
		this.templateFile = templateFile;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String parcourir(Class<?> clazz, String packageName) {

		CreatePojoClassTemplate cpct = new CreatePojoClassTemplate();
		PojoClassTemplate pojo = cpct.generer(clazz, packageName);
		ParametrizedPropertiesFileReader parametrizedFileReader = new ParametrizedPropertiesFileReader(pojo);
		
		File templateFileReal = new File(templateFile);
		String result="";
		
		try {
			 result = parametrizedFileReader.getProperties( FileUtils.readFileToString(templateFileReal, "UTF-8"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		


	

		return result;

	}



}
