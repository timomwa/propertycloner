package propertycloner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import ke.co.technovation.converter.Converter;
import ke.co.technovation.converter.ConverterException;
import ke.co.technovation.converter.GenericConverter;
import ke.co.technovation.example.DieselCar;
import ke.co.technovation.example.DieselCarRepository;
import ke.co.technovation.example.ElectricCar;
import ke.co.technovation.example.ElectricCarConverter;

@SuppressWarnings("unchecked")
public class TestConverters {
	
	@Test
	public void testConverters(){
		
		Converter<ElectricCar> electricCarConverter = GenericConverter.instance( ElectricCarConverter.class  );
		DieselCar dieselCar = DieselCarRepository.findById( 566L );
		
		ElectricCar electricCarConvert = null;
		try {
			electricCarConvert = electricCarConverter.convert( dieselCar );
		} catch (ConverterException e) {
			e.printStackTrace();
		}
		
		assertNotNull("Conversion of diesel car to electric failed!",electricCarConvert);
		assertEquals("Ground clearance has changed after conversion! Probably battery too heavy! Consider lighter batteries or stiffer suspension", dieselCar.getGroundClearance(), electricCarConvert.getGroundClearance());
		assertNull("We've not gotten to the dyno, so we don't know the horsepower yet! Where did you get this value?", electricCarConvert.getHorsePower());
	}

}
