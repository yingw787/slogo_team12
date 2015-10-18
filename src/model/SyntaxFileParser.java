package model;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SyntaxFileParser {

	private static final String SYNTAX_FILE_LOCATION = "resources/languages/Syntax.properties";
	private HashMap<Pattern, String> map = new HashMap<Pattern, String>();
	
	// TODO: Still need to integrate regexes for ListStart, ListEnd, GroupStart, GroupEnd
	
	public SyntaxFileParser(){
		
		ClassLoader classLoader = getClass().getClassLoader();
		File syntaxFile = new File(classLoader.getResource(SYNTAX_FILE_LOCATION).getFile());
		
		try(Scanner scanner = new Scanner(syntaxFile))
		{
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				if(line.charAt(0) != '#'){
					String newLine = line.replace(" = ", " ");
					String[] commands = newLine.split(" ");
					
					Pattern pattern = Pattern.compile(commands[1]);
//					Matcher matcher = pattern.matcher("# forward");
//					boolean b = matcher.matches();
					map.put(pattern, commands[0]);	
				
				}
			}
		}
		catch(IOException e){
			
		}
		
	}
	
	
	public HashMap<Pattern, String> retrieveMap(){
		return map; 
	}
	
//	public static void main(String[] args){
//
//		SyntaxFileParser parser = new SyntaxFileParser();
//	
//	}
	
	
}
