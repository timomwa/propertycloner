package ke.co.technovation.converter;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

import ke.co.technovation.annotations.Ignore;
import ke.co.technovation.annotations.Transferable;


/**
 * 
 * @author Timothy Mwangi Gikonyo
 * on 23 May 2016
 * <p>
 * The abstract class is a basis for type converters.
 * This is mainly for the developers' convenience.
 * Saves us the manual work for copying values from 
 * one class to the other.
 * <p>
 * 
 * <blockquote>Usage</blockquote>
 * <p>Annotate your source class, field or 
 * getter method with <code>com.trademarkea.annotations.Transferable</code>
 * Then call <code>com.trademarkea.converters.GenericConverter.convert(Object, T)<code>
 *  to copy field values from source to destination class where applicable.</p>
 * 
 * @param <T> A class that extends java.io.Serializable
 */
@SuppressWarnings({"all","unchecked"})
public abstract class GenericConverter<T extends Serializable> implements Converter<T> {

	private enum MethodFunction{
		GETTER,SETTER;
	};
	
	private final Class<T> genericClass;
	
	public static Converter instance(Class<? extends GenericConverter> desired){
		try {
			return (Converter) desired.getConstructors()[0].newInstance();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public GenericConverter() {
		this.genericClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	
	/* (non-Javadoc)
	 * @see com.trademarkea.converters.Converter#convert(java.lang.Object, java.lang.Object)
	 */
	@Override
	public T convert(Object source, T destination) throws ConverterException{
		
		if(source==null)
			throw new ConverterException("Source object is null");
		
		Field[] fields = source.getClass().getDeclaredFields();
		
		if(fields==null || fields.length<1)
			throw new ConverterException("There are no declared fields!");
		if(destination==null)
		try {
			Constructor constructor = genericClass.getConstructor();
			destination = (T) constructor.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ConverterException(e);
		}
		
		boolean transfer_classwide = source.getClass().isAnnotationPresent(Transferable.class);
		boolean transfer = true;
		for(Field field: fields){
			
			try {
				
				Field field_ = source.getClass().getDeclaredField(field.getName());
				Transferable transferableannotation = field_.getAnnotation(Transferable.class);
				if( !transfer_classwide ){
					transfer = field_.isAnnotationPresent(Transferable.class);
				}
				
				
					
				if(transfer && !field_.isAnnotationPresent(Ignore.class)){
					String getterMethodName = field_.getName();
					String setterMethodName = field_.getName();
					boolean isactualgettermethodname = false;
					boolean isactualsettermethodname = false;
					if(transferableannotation!=null){
						if(transferableannotation.getterMethodName()!=null && !transferableannotation.getterMethodName().isEmpty()){
							getterMethodName = transferableannotation.getterMethodName();
							isactualgettermethodname = true;
						}
						if(transferableannotation.setterMethodName()!=null && !transferableannotation.setterMethodName().isEmpty()){
							setterMethodName = transferableannotation.setterMethodName();
							isactualsettermethodname = true;
						}
					}
					Method getterFromSource = getMethod(isactualgettermethodname,source.getClass(),getterMethodName, MethodFunction.GETTER);
					Method setterFromDestination = getterFromSource!=null ? getMethod(isactualsettermethodname,destination.getClass(),setterMethodName, MethodFunction.SETTER, getterFromSource.getReturnType()) : null;
					
					if(getterFromSource!=null && setterFromDestination!=null){
						Object source_value = getterFromSource.invoke(source);
						setterFromDestination.invoke(destination, source_value);
					}
					
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new ConverterException(e);
			}
		}
		
		
		Method[] methods = source.getClass().getMethods();
		transfer = true;
		for(Method method : methods){
			
			if( !transfer_classwide )
				transfer = method.isAnnotationPresent(Transferable.class);
			
			if(transfer){
				
				Transferable transferableannotation = method.getAnnotation(Transferable.class);
				String setterMethodName = method.getName();
				boolean isactualsettermethodname = false;
				if(transferableannotation!=null){
					if(transferableannotation.setterMethodName()!=null && !transferableannotation.setterMethodName().isEmpty()){
						setterMethodName = transferableannotation.setterMethodName();
						isactualsettermethodname = true;
					}
				}
				
				Method setterFromDestination = method!=null ? getMethod(isactualsettermethodname,destination.getClass(), setterMethodName , MethodFunction.SETTER, method.getReturnType()) : null;
				if(setterFromDestination!=null){
					try{
						Object source_value = method.invoke(source);
						setterFromDestination.invoke(destination, source_value);
					}catch(IllegalArgumentException ie){
						ie.printStackTrace();
					}catch(InvocationTargetException ite){
						ite.printStackTrace();
					}catch(Exception e){
						e.printStackTrace();
						throw new ConverterException(e);
					}
				}
			}
			
		}
		
		return (T) destination;
	}
	
	/* (non-Javadoc)
	 * @see com.trademarkea.converters.Converter#convert(java.lang.Object)
	 */
	@Override
	public T convert(Object source) throws ConverterException{
		return convert(source,null);
	}

	private Method getMethod(boolean literalmethodName, Class<? extends Object> class1, String fieldName,
			MethodFunction getter, Class<? extends Object>... parameters) throws SecurityException {
		

		if(literalmethodName){
			try{
				if(getter==MethodFunction.GETTER){
					return class1.getMethod(fieldName);
				}
			}catch(NoSuchMethodException nsme){}
			try{
				if(getter==MethodFunction.SETTER)
					return class1.getMethod(fieldName, parameters);
			}catch(NoSuchMethodException nsme){}
		}else{
			try{
				if(getter==MethodFunction.GETTER)
					return class1.getMethod("get"+fieldName.toUpperCase().substring(0, 1)+(fieldName.substring(1,fieldName.length())));
			}catch(NoSuchMethodException nsme){}
			try{
				if(getter==MethodFunction.SETTER)
					return class1.getMethod("set"+fieldName.toUpperCase().substring(0, 1)+(fieldName.substring(1,fieldName.length())), parameters);
			}catch(NoSuchMethodException nsme){}
			try{
				if(getter==MethodFunction.GETTER)
					return class1.getMethod("is"+fieldName.toUpperCase().substring(0, 1)+(fieldName.substring(1,fieldName.length())));
			}catch(NoSuchMethodException nsme){}
		}
		return null;
	}

}
