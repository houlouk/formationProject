package generator;

import java.util.ArrayList;
import java.util.List;

public class PojoClassTemplate {
	
	private String modelClass;
	private String modelIdType;
	private String modelIdName;
	private boolean generateId=false;
	
	private List<Attribut> fieldList = new ArrayList<>();
	private List<Attribut> modelFieldList = new ArrayList<>();

	public String getModelClass() {
		return modelClass;
	}

	public void setModelClass(String modelClass) {
		this.modelClass = modelClass;
	}

	public String getModelIdType() {
		return modelIdType;
	}

	public void setModelIdType(String modelIdType) {
		this.modelIdType = modelIdType;
	}

	public String getModelIdName() {
		return modelIdName;
	}

	public void setModelIdName(String modelIdName) {
		this.modelIdName = modelIdName;
	}

	public List<Attribut> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<Attribut> fieldList) {
		this.fieldList = fieldList;
	}

	public boolean isGenerateId() {
		return generateId;
	}

	public void setGenerateId(boolean generateId) {
		this.generateId = generateId;
	}

	public List<Attribut> getModelFieldList() {
		// TODO Auto-generated method stub
		return this.modelFieldList ;
	}

}
