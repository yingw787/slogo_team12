// this entire file is part of my masterpiece. 
// YING WANG 
// YING WANG

package masterpiece;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/*
 * This class is a resource file parser. The parser should be able to read a resource file, and return a HashMap<String, String> where the key is the possible string 
 * value written in the interpreter and delivered to the backend, and the value is the keyword it matches with. 
 * Example: Forward = forward|fd should have <fd, Forward> and <forward, Forward> in the hashmap that is returned. 
 * This way the CommandFactory doesn't have to be hardcoded to one language. 
 */

public class LanguageFileParser{
	
	private static final String LANGUAGE_FILE_LOCATION = "resources/languages/";
	
	private HashMap<String, String> map = new HashMap<String, String>(); 
	
	public LanguageFileParser(String language){
				
		ClassLoader classLoader = getClass().getClassLoader();
		File languageFile = new File(classLoader.getResource(LANGUAGE_FILE_LOCATION + language + ".properties").getFile());
		readFileIntoMap(languageFile);
		
	}
	
	private void readFileIntoMap(File file){
		try(Scanner scanner = new Scanner(file))
		{
			while(scanner.hasNextLine()){
				String line = scanner.nextLine(); 
				if(line.charAt(0) != '#'){ // if it isn't a comment line 
					String newLine = line.replace("|", " ").replace("\\", "").replace(" = ", " ");
					String[] commands = newLine.split(" ");	
					for(int i = 1; i < commands.length; i++){
						map.put(commands[i], commands[0]);
					}
				}
			}
			scanner.close(); 
		}
		catch (IOException e)
		{
			System.err.println("File not found");
		}
	}
	
	public HashMap<String, String> retrieveMap(){
		return map; 
	}
	
//	public static void main(String[] args){ // for unit testing 
//		
//		LanguageFileParser englishParser = new LanguageFileParser("English");
//		HashMap<String, String> map = englishParser.retrieveMap();
//		for(String key: map.keySet()){
//			System.out.println(key + ", " + map.get(key));
//		}
//	}

}
