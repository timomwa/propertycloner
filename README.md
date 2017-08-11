# PropertyCloner
For lack of a better word, this is a utility to help you copy values from one entity to another
using annotations instead of writing code to copy values one-by one.


Prerequisites
--------------

Tested on;

1. Java version 1.8.0_121
2. Gradle version 3.4


Run tests;
-----------

1.  Navigate to the root of this project and run; Main class being  - ke.co.technovation.example.ExampleMain
	<br /><br />`gradle clean test convert`<br /><br />
2.  How to install;
	<br /><br />`gradle clean build test install`<br /><br />
3. Include in your project.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Gradle
	
		compile group: 'ke.co.technovation', name: 'propertycloner', version: '1.0'  
	

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Maven

		<dependency>
		  <groupId>ke.co.technovation</groupId>
		  <artifactId>propertycloner</artifactId>
		  <version>1.0</version>
		</dependency>
	
Usage
-----

Especially when maintaining legacy code, you will find better way of doing things.
At times, re-writing huge bits of legacy code is not possible.

You may be forced to re-write the functions piece meal.

One of the most time consuming activities is copying values from legacy entities
into newer, more improved / refactored entities / POJOS.

It you might find yourself doing the same when writing web APIs as well.

The below example shows a hypothetical Diesel to Electric car conversion
process.

While some properties are shared between the electric and the diesel car,
some may end up being totally different, while most will remain the same
e.g color, steering, side mirrors.

After Diesel, to electric conversion, the new Electric car will have 
totally different horsepower and torque values, so these values 
must be re-calculated in a dyno.

		/*
			The tiresome way of copying values from one class to another
		*/
		DieselCar dieselCar = DieselCarRepository.findById( 566L );

		ElectricCar newElectricCar = new ElectricCar();
		newElectricCar.setGroundClearance( dieselCar.getGroundClearance() );
		newElectricCar.setPaintCode(  dieselCar.getPaintCode() );
		newElectricCar.setSeatColor(  dieselCar.getSeatColor()  );
		newElectricCar.setSideMirrorStyle( dieselCar.getSideMirrorStyle()  );
		newElectricCar.setSteeringWheelDiameter( dieselCar.getSteeringWheelDiameter()  );
		newElectricCar.setUpholsteryColorCode( dieselCar.getUpholsteryColorCode()  );
		newElectricCar.setWheelCount( dieselCar.getWheelCount()  );
		newElectricCar.setWheelSize( dieselCar.getWheelSize() );
		
		/* Below two values must be computed after the hypothetical conversion */
		newElectricCar.setHorsePower( 600 );
		newElectricCar.setLbTorque( 590 );
		

The above method is straight forward. However, one disadvantage is that if there is
ever need to add new set of values in both entities - that is - ElectricCar and DieselCar,
you will need to update every code snipet where this conversion happens. This adds an extra 
effort maintaining the legacy code.


However, this library can ease the task by;


* Annotate your source entity with `@Transferable` as so;

	..
	import ke.co.technovation.annotations.Ignore;
	
	import ke.co.technovation.annotations.Transferable;
	
	..
	..
	
	@Transferable
	public class DieselCar implements Serializable

* For those properties that are not necessary to transfer to your destination object, annotate with `@Ignore`. In our example, we expect different horsepower and torque values after conversion from Diesel to electric.

	@Ignore
	public Integer horsePower;
	
	@Ignore
	public Integer lbTorque;
	
* Next, instantiate a converter, fetch the diesel car and use converter to convert it to an electric car;


		Converter<ElectricCar> electricCarConverter = GenericConverter.instance( ElectricCarConverter.class  );
		DieselCar dieselCar = DieselCarRepository.findById( 566L );
		ElectricCar electricCarConvert = electricCarConverter.convert( dieselCar );
		
		/**
		 * Even though below properties are common between the
		 * Electric car and the Diesel car,
		 * the values can't be the same after conversion,
		 * so we must key in the new values after conversion.
		 * 
		 * The @Ignore annotation is used at the property level so that
		 * the source values are not copied over.
		 */
		electricCarConvert.setHorsePower( 600 );
		electricCarConvert.setLbTorque( 590 );
		

As you can see above, no need to explicitly call the getters and setter methods of any class, 
except for the values that have `@Ignore` annotation.

This should save you time when refactoring legacy code.




		
		