###Introduction

###Overview


##API Design:
##External Front-end
* go() -> passes text input string to back-end
* reset() -> completely reinitializes all values (history, user-added methods, clear the screen, etc.)

##External Back-end
* sendError() -> throws exception and prompts front-end to launch error message
* updateHistory() -> updates data structure that keeps track of command history
* updateTurtle() -> updates turtle's position and style according to commands

##Internal Front-end
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

##Internal Back-end
* parseStringToCommand() -> parses string of code into commands
* makeNewCommand() -> creates user-defined command
* Function class -> knows function command, parameters; contains run() method to perform relevant operations
* FunctionFactory -> given a command, return the operations called in run() for that function (or something along those lines...)

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

I worked pretty much exclusively on front-end for Cell Society, so I am pretty comfortable with the UI side of JavaFX. That said, I also have some ideas for how to implement the back-end for this project, so I am totally happy to take on whatever needs to be done! Also, I'm TAing a class on functional programming, so I would be comfortable trying to work with some of that (I think that would be mostly back-end).
