###Introduction
The goal of this project is to develop IDE to support a simplified version of the Logo language. Our primary design goal is to implement distinct front-end and back-end compenents that comunicate through public APIs. The goal is that after we have completed our project, any future feature addition will require only the use of the APIs, without having to modify previously closed classes. Essentially we wish to adhere to the open-closed principle. We will develop 4 APIs - one for communication between front-end to back-end, one for communication from back-end to front-end, one for the front-end that is designed for future programmers to develop their own uses, and one for the backend for future programmers to develop their own uses. 

The open arcithecture will consist of abstract classes that are open to be impemented in closed subclasses. The API interface will consist of public method mehtods to communicate between a front-end set of classes (which are closed) and a back-end set of classes (also closed).

###Overview

In this project, users will enter commands in a GUI and will see a turtle on the screen move as a result of their commands. 
Logically it follows that we must have a set of classes to deal with everything displayed to the user, and a set of classes to deal with processing the user's command, storing command history, and deciding what actions need to occur to the objects on the screen. Half of the public methods of the main "view" class (which will instantiate all the front-end component classes) will serve as the API for communication from front-end to backend, with the ability to send inputed text 




###User Interface
Components:
* turtle graphics window 
	* should the turtle be able to go off the screen?
* history window (maybe on the right, like in the example) -> shows past commands
	* should this have a separate clear history button?
	* should be some kind of scroll pane
* console/input below the turtle window
	* should be some kind of scroll pane
* VBox next to (to the right of ?) console with "RUN" and "CLEAR" controls
* button to load simulation; when clicked, launches a pop-up to type in file name
	* not sure where this should be located
	* needs to have appropriate error handling
* a way to configure turtle speed (or is that a command that would be typed into the console?)

###Design Details

#####API Design:
#####External Front-end
* go() -> passes text input string to back-end (includes an exception for catching invalid text)
* reset() -> completely reinitializes all values (history, user-added methods, clear the screen, etc.)

#####External Back-end
* sendError() -> throws exception and prompts front-end to launch error message
* updateHistory() -> updates data structure that keeps track of command history
* updateTurtle() -> updates turtle's position and style according to commands

#####Internal Front-end
* makeErrorBox() -> displays error message
* makeButton()
* Turtle {
	* boolean penDown;
	* getTurtle()
	* addTurtle()
	* styleTurtle()
	* moveTurtle()
  }
* displayHistory() -> displays whatever is stored in the "history" data structure in back-end

####Internal Back-end
* parseStringToCommand() -> parses string of code into commands
* makeNewCommand() -> creates user-defined command
* Function class -> knows function command, parameters; contains run() method to perform relevant operations
* FunctionFactory -> given a command, return the operations called in run() for that function (or something along those lines...)
* 

###API Example code

###Design Considerations
* How do we store all of the functions? Should each command have its own class (subclass of Function), or should each command just be an instance of the Function class? Should we store all of them in some kind of data structure? If so, which data structure?
	I'm leaning towards each command being a subclass that extends Function super class. We could keep them all in one package to try and keep things organized. And for user-created functions, maybe those could just be instances of some UserFunction subclass that also extends Function. (Unless we figure out a way to create a method that creates subclasses....)
* How will we get the commands to actually operate on the turtle in the front-end?
	I think that all of the operations performed in the run() method of each Function/Command object should be turtle-specific functions. Maybe there should be an additional class containing all of the turtle operations (like the nitty-gritty aspects of getting the turtle to move to a specifc place), and the run() function in each command would exclusively call those turtle-moving and -styling operations.
* Where do we do error-checking for the console input?
	Maybe in the parser.

###Team Responsibilities

Front-end: Dan and Shari
Back-end: Elizabeth and Ying
I worked pretty much exclusively on front-end for Cell Society, so I am pretty comfortable with the UI side of JavaFX. That said, I also have some ideas for how to implement the back-end for this project, so I am totally happy to take on whatever needs to be done! Also, I'm TAing a class on functional programming, so I would be comfortable trying to work with some of that (I think that would be mostly back-end).
