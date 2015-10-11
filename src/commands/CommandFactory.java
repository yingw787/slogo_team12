package commands;

public class CommandFactory {

	public CommandFactory() {
		//do nothing
	}
	
	public Command getCommand(String command) {
		switch (command) {
		case ("Forward"):
			return new Forward();
		case ("Constant"):
			return new Constant();
		}
		return null;
	}
}
