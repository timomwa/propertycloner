package ke.co.technovation.example;

import ke.co.technovation.converter.Converter;
import ke.co.technovation.converter.ConverterException;
import ke.co.technovation.converter.GenericConverter;

@SuppressWarnings("unchecked")
public class ExampleMain {
	
	
	public static void main(String[] args) throws Exception {
		
		/**
		 * We hava a legacy diesel car that we'd like to 
		 * copy some of it's properties.
		 * 
		 * Inheritance might not work
		 */
		DieselCar dieselCar = DieselCarRepository.findById( 566L );
		
		/**
		 * Method 1: The obvious one
		 */
		ElectricCar electricCarConvertA = copyViaTiresomeMethod(dieselCar);
		System.out.println( "Method 1: "+electricCarConvertA );
		
		/**
		 * Method 2: Concise. and code does not change with increased number of properties
		 */
		ElectricCar electricCarConvertB = copyViaElegantMethod( dieselCar );
		System.out.println( "Method 2: "+electricCarConvertB );
		
		
		
	}

	private static ElectricCar copyViaTiresomeMethod(DieselCar dieselCar) {
		ElectricCar newElectricCar = new ElectricCar();
		newElectricCar.setGroundClearance( dieselCar.getGroundClearance() );
		newElectricCar.setPaintCode(  dieselCar.getPaintCode() );
		newElectricCar.setSeatColor(  dieselCar.getSeatColor()  );
		newElectricCar.setSideMirrorStyle( dieselCar.getSideMirrorStyle()  );
		newElectricCar.setSteeringWheelDiameter( dieselCar.getSteeringWheelDiameter()  );
		newElectricCar.setUpholsteryColorCode( dieselCar.getUpholsteryColorCode()  );
		newElectricCar.setWheelCount( dieselCar.getWheelCount()  );
		newElectricCar.setWheelSize( dieselCar.getWheelSize() );
		
		/**
		 * Even though below properties are common between the
		 * electric car and the diesel car,
		 * the values can't be the same after conversion,
		 * so we must key in the new values after conversion.
		 * 
		 * The @Ignore annotation is used at the property level in Method 2 below
		 */
		newElectricCar.setHorsePower( 600 );
		newElectricCar.setLbTorque( 590 );
		return newElectricCar;
	}

	/**
	 * 
	 * @param dieselCar - ke.co.technovation.example.DieselCar
	 * @return ke.co.technovation.example.ElectricCar
	 * @throws Exception
	 */
	private static ElectricCar copyViaElegantMethod(DieselCar dieselCar) throws Exception  {

		Converter<ElectricCar> electricCarConverter = GenericConverter.instance( ElectricCarConverter.class  );
		return electricCarConverter.convert( dieselCar );
	}

}
