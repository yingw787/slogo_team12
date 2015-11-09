// this entire file is part of my masterpiece. 
// YING WANG
// YING WANG

package masterpiece;

import java.util.HashMap;
import java.util.Map;
import commands.*;

public class CommandFactory {

	public CommandFactory(){ 
		this(null); // constructor references another constructor with input; no code duplication 
	}
	
	public CommandFactory(Map<String,UserCommand> userCommands) {
		// yada yada yada 
	}
	
	public Command getCommand(String command, String expression) {
		
		/* 
		 * names of objects that are different from the string value in the resource file: 
		 * ListStart, SLogoList; 
		 * Backward, Back; 
		 * SetTowards, Towards
		 * Random, SLogoRandom
		 * Command, myUserCommand.get(expression)
		 * 
		 * Goal is to use reflection in order to find the command in the commands package and return it without having to hard code a switch-case tree.
		 * If the name is not present, return null; this is purely due to time constraints. In an ideal situation, I would have named my classes SLogo_[CommandName], then
		 * just used a StringBuilder to append SLogo_ + stringName in order to compare so that there were no conflicts with java core names of items. 
		 * 
		 * Exception handling is also extremely basic; in the future would implement a ReflectionException or a SLogoException to handle missed cases or doesNotExist cases
		 * 
		 * 
		 */
		
		
		StringBuilder builder = new StringBuilder(); 
		builder.append("commands.");
		builder.append(command);
				
		try {
			Command commandObject = (Command) Class.forName(builder.toString()).getConstructor().newInstance();
			return commandObject;
//			System.out.println(command.getClass().getMethods());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args){ // for unit testing 
		
		// this works 
		
		LanguageFileParser englishParser = new LanguageFileParser("Chinese"); // in the actual program, the "English" would be replaced by a toolbar in the GUI displaying which language the user wants the instructions to parse in.
		HashMap<String, String> map = englishParser.retrieveMap();
		
		String textInterpreterString = "taibi"; // in the actual program this will be a string passed from the interpreter 
		String commandString = map.get(textInterpreterString);
		
		CommandFactory factory = new CommandFactory(); 
		factory.getCommand(commandString, null);
		
		
	}
}
