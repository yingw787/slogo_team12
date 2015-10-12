package commands;

public class CommandFactory {

	public CommandFactory() {
		//do nothing
	}
	
	public Command getCommand(String command) {
		switch (command) {
		//BasicSyntax
		case ("Constant"):
			return new Constant();
		//TurtleCommands
		case ("Forward"):
			return new Forward();
		case ("Back"):
			return new Back();
		case ("Left"):
			return new Left();
		case ("Right"):
			return new Right();
		case ("SetHeading"):
			return new SetHeading();
		case ("SetTowards"):
			return new Towards();
		case ("SetPosition"):
			return new SetPosition();
		case ("PenUp"):
			return new PenUp();
		case ("PenDown"):
			return new PenDown();
		case ("ShowTurtle"):
			return new ShowTurtle();
		case ("HideTurtle"):
			return new HideTurtle();
		case ("Home"):
			return new Home();
		case ("ClearScreen"):
			return new ClearScreen();		
		}
		return null;
	}
}
