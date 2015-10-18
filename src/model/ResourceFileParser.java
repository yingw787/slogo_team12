package model;

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

public class ResourceFileParser{
	
	private static final String SYNTAX_FILE_LOCATION = "resources/languages/Syntax.properties";
	private static final String LANGUAGE_FILE_LOCATION = "resources/languages/";
	
	private HashMap<String, String> map = new HashMap<String, String>(); 
	
	public ResourceFileParser(String language){
				
		ClassLoader classLoader = getClass().getClassLoader();
		File syntaxFile = new File(classLoader.getResource(SYNTAX_FILE_LOCATION).getFile()); // TODO: add in syntax.properties stuff into hashmap
		File languageFile = new File(classLoader.getResource(LANGUAGE_FILE_LOCATION + language + ".properties").getFile());
		
		try(Scanner languageFileScanner = new Scanner(languageFile))
		{
			while(languageFileScanner.hasNextLine()){
				String line = languageFileScanner.nextLine(); 
				if(line.charAt(0) != '#'){ // if it isn't a comment line 
					String newLine = line.replace("|", " ").replace("\\", "").replace(" = ", " ");
					String[] commands = newLine.split(" ");	
					for(int i = 1; i < commands.length; i++){
						map.put(commands[i], commands[0]);
					}
				}
			}
			languageFileScanner.close(); 
		}
		catch (IOException e)
		{
			System.err.println("Language file not found in resource file directory");
		}
		
	}
	
	public HashMap<String, String> retrieveMap(){
		return map; 
	}
	
//	public static void main(String[] args){ // for unit testing 
//		
//		ResourceFileParser englishParser = new ResourceFileParser("English");
//		HashMap<String, String> map = englishParser.retrieveMap();
//		for(String key: map.keySet()){
//			System.out.println(key + ", " + map.get(key));
//		}
//	}

}
