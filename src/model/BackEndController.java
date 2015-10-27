package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import commands.UserCommand;
import engine.Controller;
import exceptions.CommandNotFoundException;
import exceptions.PopupError;

public class BackEndController {
	private static final String PATH = "src/userPrograms/";
	private static final String EXTENSION = ".logo";
	private ParseModel myParser;
	private Controller myController;
	Map<String,UserCommand> myUserCommands;
	private List<Integer> myActiveTurtles;
	
	public BackEndController(Controller controller) {
		myController = controller;
		myUserCommands = new HashMap<String,UserCommand>();
		myActiveTurtles = new ArrayList<Integer>();
	}
	
	public void saveProgram(String input, String programTitle) {
		String filePath = PATH + programTitle + EXTENSION;
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			writer.write(input);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Queue<TurtleStatus> generateTurtleCommands(String input, String language) {
		myParser = new ParseModel(input, "resources/languages/" + language, myUserCommands);
		List<ExpressionNode> parseModel = myParser.createParseModel();
		Translator translator = new Translator(parseModel, myUserCommands, myActiveTurtles, myController.getVariablesMap(), myController);
		try {
			return translator.executeCommands();
		} catch (CommandNotFoundException e) {
			PopupError popup = new PopupError(); 
			popup.generateError("Queue of Turtle Status Objects cannot be generated");
		}
		return null; 
	}

}
