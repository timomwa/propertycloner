package ke.co.technovation.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DieselCarRepository {
	
	public static ObjectMapper mapper = new ObjectMapper();
	public static String content = "";
	static{
		try {
			content = new String(  Files.readAllBytes(Paths.get(getPath(Locations.RESOURCES_FOLDER).concat( File.separator ).concat( "DieselCars.json") ) )   );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static DieselCar findById(Long id) {
		try {
			
			JSONObject jsob = new JSONObject(content);
			JSONArray jsonArray = jsob.getJSONArray( "dieselCars" );
			List<DieselCar> dieselCars = mapper.readValue( jsonArray.toString() , new TypeReference<List<DieselCar>>(){});
			
			for(DieselCar car : dieselCars)
				if(car.getId().compareTo(id)==0)
					return car;
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String getPath(Locations resourcesFolder) {
		if(resourcesFolder == Locations.RESOURCES_FOLDER)
			return System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources";
		return null;
	}

}
