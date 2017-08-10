package ke.co.technovation.converter;

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
 * gettermethod with <code>com.trademarkea.annotations.Transferable</code>
 * Then call <code>com.trademarkea.converters.GenericConverter.convert(Object, T)<code>
 *  to copy field values from source to destination class where applicable.</p>
 * 
 * @param <T> A class that extends java.io.Serializable
 */
public interface Converter<T> {
	/**
	 * 
	 * @param source - java.lang.Object
	 * @return T - generic type with values from source
	 * @throws ConverterException
	 */
	public T convert(Object source) throws ConverterException;
	
	/**
	 * 
	 * @param source - java.lang.Object
	 * @param destination - T generic type with values from source
	 * @return T - generic type with values from source
	 * @throws ConverterException
	 */
	public T convert(Object source, T destination) throws ConverterException;

}
