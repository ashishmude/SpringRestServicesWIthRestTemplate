package nl.bb.openb.controllers.test;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {
	public static <T> T getObjectFromJsonFile(String fileName, TypeReference<T> typeReference){
		T response = null;

		ObjectMapper mapper = new ObjectMapper();
		File file = new File("./src/test/resources/"+fileName);
		try {
			response = mapper.readValue(file, typeReference);
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return response;
	};
}
