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


Backend

* Should each command have its own class extending from a Function superclass, or should each command just be an instance of the Function class? Is there a better method? How does the eventual choice impact the flexibility of the Function class?
* Should we store all of these functions in some kind of data structure? If so, which data structure? What kind of design pattern would maximize the flexibility and extensibility of this program?
* How can/would users add in their own functions into the SLogo program? Should they be granted that power in the first place? How would allowing this into the API impact the design of the parser? 
* What if there are additional commands the backend needs to send to the frontend? How can we send additional commands without stretching the API?
* How would the command classes be organized within the larger project? Would this impact its accessibility by other classes, and if so, how?
* What kind of error checking will be built in to the backend? How will this error checking interface with the frontend? What class/where does this error checking happen? 

Frontend

* How should the frontend send the SLogo information to the backend? Should it send it as one giant chunk of text, or should it send it line by line? Should it have some method of recognizing the code itself so that it can intelligently split up commands to feed to the backend? What impact would these different solutions have on runtime and memory? 
* Should the frontend send additional information about itself to the backend? For example, if the frontend turtle environment was a tiled grid vs. a pixeled canvas, or if there are additional buttons in a toolbar? 
* What kind of features can users extend on the frontend? What kind of features/Are there features should the user not be able to modify or extend? How will this impact the flexibility of the frontend classes? How will any additional functionality be integrated with the backend? Should it all be stored in one class, or broken up into "frontend" and "backend" classes?
* What kind of additional functionality can be integrated into the frontend without editing classes? For example, colorizing the program text in the frontend using a CSS stylesheet upon function recognition? What about writing in different languages using a resource file? 
* How can the user add in or modify parts of the environment to suit their style? How can the user upload different turtle .png files for example? What about obstacle courses (or files describing obstacle courses? Should the underlying functionality be expected to be extensible to prepare for this eventuality? 

###Team Responsibilities

Front-end: Dan and Shari
Back-end: Elizabeth and Ying

Front-end responsibilities: 
- develop the text field for the user to add in the program 
- develop the canvas/tiled grid option for the turtle 
- initialize the environment (set the turtle to position (0,0), etc.)
- add in flexibility for user-created features in the front-end, and be able to pass those user-created features to the backend in a way that will modify the public API the least
- update this feature list as the project progresses

Back-end responsibilities: 
- read information from the text field from the front-end
- parse the information into different command objects 
- be flexible enough in order to read different languages upon the submission of a .properties file (do not hardcode any commands)
- use sequential development of command objects to create sequential list of commands for turtle
- throw exceptions based on correctness of SLogo program, and communicate those exceptions to the front-end in a user-friendly way
- send information about any state changes from the back-end to the front-end
- update this feature list as the project progresses

Team-scope responsibilities:
- development of any public API changes between the front-end to back-end, back-end to front-end, front-end to developers, back-end to developers
- update this feature list as the project progresses

