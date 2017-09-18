package generator;

import java.lang.reflect.Field;
import java.util.List;

public class CreatePojoClassTemplate  {

	public PojoClassTemplate generer(Class<?> clazz,String packageName) {
		List<Class<?>> classes = ClassFinder.find(packageName);
		
			PojoClassTemplate pojo = new PojoClassTemplate();

			String nameClass = Generator.nameOfClass(clazz.getSimpleName());
			
			pojo.setModelClass(nameClass);
 
			
			for (Field f : clazz.getDeclaredFields()) {
			
				String type = Generator.getGeneric(f.getGenericType().getTypeName());

				try {
					if(classes.contains(Class.forName(type))){
						pojo.getModelFieldList().add(new Attribut(f.getName(),Generator.nameOfClass(f.getType().getName())));

					}
					if ((f.getType().isPrimitive() || !classes.contains(Class.forName(type)))
							&& !f.isAnnotationPresent(javax.persistence.GeneratedValue.class)) {
						
						pojo.getFieldList().add(
						new Attribut(f.getName(),Generator.nameOfClass(f.getType().getName())))
						
						
						;
					}

					else if (f.isAnnotationPresent(javax.persistence.GeneratedValue.class)) {
						pojo.setGenerateId(true);
					}
					
					if(f.isAnnotationPresent(javax.persistence.Id.class)) {
						pojo.setModelIdName(f.getName());
						pojo.setModelIdType(Generator.nameOfClass(f.getType().getName()));
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			
			

			return pojo;
		}

	}
