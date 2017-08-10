package propertycloner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import ke.co.technovation.example.DieselCar;
import ke.co.technovation.example.DieselCarRepository;

public class TestRepository {
	
	
	@Test
	public void checkRepository(){
		Long dieselCarId = 566L;
		DieselCar dieselCar = DieselCarRepository.findById( dieselCarId );
		assertNotNull("Diesel car with id "+dieselCarId+" exists doesn't!" , dieselCar);
		assertEquals("The ID of the diesel car doesn't match expected! ", 566L ,  dieselCar.getId(), 0);
		
	}

}
