// This entire file is part of my masterpiece.
// Elizabeth Dowd
package commands;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class CommandFactory {
	private Map<String,UserCommand> myUserCommands;

	public CommandFactory(Map<String,UserCommand> userCommands) {
		myUserCommands = userCommands;
	}
	
	public Command getCommand(String command, String expression) {
		if (command.equals("Command")) {
			return myUserCommands.get(expression);
		}
		try {
			Class commandName = Class.forName("commands." + command);
			return (Command) commandName.getDeclaredConstructor(null).newInstance(null);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
