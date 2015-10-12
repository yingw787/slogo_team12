package commands;

public class CommandFactory {

	public CommandFactory() {
		//do nothing
	}
	
	public Command getCommand(String command) {
		switch (command) {
		case ("Forward"):
			return new Forward();
		case ("Back"):
			return new Back();
		case ("Left"):
			return new Left();
		case ("Right"):
			return new Right();
		case ("Constant"):
			return new Constant();
		case ("SetHeading"):
			return new SetHeading();
		case ("SetTowards"):
			return new Towards();
		}
		return null;
	}
}
