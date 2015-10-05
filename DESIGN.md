###Introduction
*This section describes the problem your team is trying to solve by writing this program, the primary design goals of the project (i.e., where is it most flexible), and the primary architecture of the design (i.e., what is closed and what is open). It should be approximately 200-300 words long and discuss the program at a high-level (i.e., without referencing specific classes, data structures, or code).*

Our goal of writing SLogo is to enable anybody to use and extend SLogo and be able to see their results in real time. This serves the purpose of enpowering people to learn coding and to expand their coding capabilities. 

The primary design objective of this project is to develop an integrated development environment (IDE) to support the SLogo language. We also want the user to be able to extend our IDE with minimum hassle, both on the front-end side and the back-end side. 

We will achieve this objective by implementing a model-view-controller (MVC) architectural pattern. This includes a distinct front-end (View), back-end (Model), and API communications logic (Controller). We intend to communicate through the controller through public APIs, for third-party extensibility. With our public APIs, any future extensions will only require the use of the APIs without having to modify existing classes, thus adhering to the Open-Closed Principle (OCP). We intend for all existing classes to be closed for modification to preserve backwards compatibility. We intend to build upon abstract classes and interfaces in order to provide maximum extensibility. Design patterns such as Factories for the intended inclusion of different commands and Singletons for the production of unique objects will help abstract features away from lower-level logic and decrease development time. 

###Overview
*This section serves as a map of your design for other programmers to gain a general understanding of how and why the program was divided up, and how the individual parts work together to provide the desired functionality. As such, it should describe the four APIs you intend to create (their purpose with regards to the program's functionality, and how they collaborate with each other) focusing specifically on the behavior, not the internal state. It should also include a picture of how the components are related (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a UML design program). This section should be approximately 1200-1600 words long and discuss specific classes, methods, and data structures, but not individual lines of code.*

We broke down our understanding of this problem in terms of what the user sees and expects our program to do. There are multiple steps of how the user will interface with the program, and how the program will react to the user's inputs. 

*Initialization of program*
When the user launches the program, the program will automatically initialize into a default state. The Main class will launch a Controller object which will, upon its initializaition, initialize a front-end object and a back-end object.

The front-end object will initialize a previously defined default state for the graphical user interface (GUI) to be presented to the user. For the Basic implementation, this will include setting the turtle's position, setting a default color for the pen the turtle is carrying, and set a default image of the turtle. The back-end object will initialize a default language from a properties file (ex. English.properties) and will initialize a singleton parsing object and a data structure to hold command history. 

*User Interaction*
When the program is initialized, the user will be presented with the GUI, which will contain the following information for the Basic implementation: a canvas with a default set of coordinates and a default background color, a text field for entering in the SLogo program, and a toolbar or set of buttons that can display commands previously run in the environment, variables currently available in the environment, user-defined commands available, options to reset the history and canvas, a language dropdown menu for choosing which language the SLogo program will be written in, and access to a detailed HTML help page for Slogo syntax to assist confused users. Finally, the user should have access to the run button to load the program into the back-end. The user can interact with the text field as well as the toolbar directly, and accesses the GUI indirectly through the SLogo program. The front-end should be flexible enough so that it can be styled with a CSS stylesheet and so that additional buttons or other modifications can be added modularly and without changes to the current code.  

*User Input*
The user should be able to write programs directly within the text editor. The user should also be able to cut and paste programs written in another editor into the text editor. The user should not need to save the program in order to run it. The programs should be able to span multiple lines. 

*Upon run action*
When the run button is clicked the program (written in the text field) should be loaded in, syntactically correct, into the parser for development into command objects. If the program is written incorrectly, an exception will be thrown and a relevant error message will be presented to the user. The text field will only clear upon successful completion of a program. Otherwise the text will remain in place so that the user can modify it to the proper syntax without having to retype the command. Along with the text from the text field, the back-end should also be able to load in any other information from the GUI that is relevant to exception handling logic or return logic. For the Basic implementation, the program can be written all at once to the parser in a text file; later implementations may take advantage of writing to the parser line-by-line for more parallelism and greater performance. 

*Upon exception*
The parser will compare the commands and parameters from the SLogo program loaded in with a dictionary of recognized commands; if the command is not within that dictionary, the back-end should return an exception that indicates the command is not supported back to the front-end, which should raise a popup, and stop the execution of the program. If the command has an improper number of parameters, or the parameters are not the correct data types, the back-end should return the proper exception which should display a relevant error message to the user and cease execution of the SLogo program, leaving then text field containing the text that was submitted so that it can be modified to the proper syntax. The back-end should not return any other logic to the front-end besides what is needed for the exception handler to operate; thus the current state of the front-end should be unchanged from when before the SLogo program was loaded. 

*Toolbar*
The toolbar should interact with the backend separately from the text editor and the run button; essentially it should not change the state of the GUI beyond returning the requisite information. There can be a sidebar that can display the state of the variables in play in the program, previous programs that can be run upon clicking (or at least upon clicking the contents of the program are entered into the text field and run from there), and other information displayed to the GUI that is not in the canvas. The HTML page can open within a browser instead of displaying in the GUI, or it can display within the sidebar. Regardless these series of commands should operate independently of the canvas. 

*API*
The API is the composition of all public methods available, broken up into the front-end to back-end, back-end to front-end, back-end to developers, and front-end to developers. The front-end back-end communications as it is currently envisioned is passed through the Controller module in order to isolate any points of failure and to abstract it away from the core back-end and front-end logic. This way, if the API needs to be changed or developed with additional features that affect back-end to front-end communications, the developers will know to change the Controller module alone. Any methods made public in the back-end or front-end logic should not affect any other methods running in the program unless there is an explicit change.

*Back-end Logic - canvas*
When the user hits the run button, the text from the text editor goes into the parser and becomes a series of command objects. These command objects are loaded into a queue or another kind of FIFO data structure where they are sent into a buffer. While the buffer is not empty (for perhaps a command that is repeated x number of times, or several move commands were entered at once), the commands in the buffer are run until completion, then the buffer is cleared for the next animation command. The back-end and front-end logic will have to be synchronized in order to pass state changes efficiently.

*Back-end logic - toolbar*
Unlike the back-end logic for the canvas, the back-end logic for the toolbar should return as soon as it has the correct result. Pressing a button like clear, for instance, should call a clear method in the API that adds the clear to the history, but immediately clears the canvas to its default with an implemented clear method in the API An abstracted event handler can be passed through to the back-end logic, which will determine which event handler it is and thus what type of information needs to be sent over to the front-end display. Ideally a Factory-like design pattern can assist in adding handlers in easily. 

####UML Design
![Image of UML](https://github.com/duke-compsci308-fall2015/slogo_team12/blob/master/UML%20Design.PNG)


###User Interface
Components:
* turtle graphics window 
	* turtle should not be allowed off screen
* history window (maybe on the right, like in the example) -> shows past commands
	* separate clear history button
	* should be some kind of scroll pane
* console/input below the turtle window
	* should be some kind of scroll pane
* VBox next to (to the right of ?) console with "RUN" and "CLEAR" controls
* button to load simulation; when clicked, launches a pop-up to type in file name
	* not sure where this should be located
	* needs to have appropriate error handling

![Image of UI] (https://github.com/duke-compsci308-fall2015/slogo_team12/blob/master/Slogo%20UI.png)

###Design Details
*This section describes each API introduced in the Overview in detail. It should describe how each API supports specific features given in the assignment specification, what resources it might use, how it is intended to be used, and how it could be extended to include additional requirements (from the assignment specification or discussed by your team). Finally, justify the decision to create each class introduced with respect to the design's key goals, principles, and abstractions. This section may be as long as it needs to be and go into as much detail as necessary to cover all your team wants to say.*

#####API Design:
#####External Front-end: This API is how a user enters a command into the GUI and it is sent for action to be taken. 
* go() -> passes text input string to back-end (includes an exception for catching invalid text)
* reset() -> completely reinitializes all values (history, user-added methods, clear the screen, etc.)

#####External Back-end: This communicates the display changes that need to happen for the turtle after commands have been processed
* sendError() -> throws exception and prompts front-end to launch error message
* updateHistory() -> updates data structure that keeps track of command history
* updateTurtle() -> updates turtle's position and style according to commands

#####Internal Front-end: These functions can be useful beyond our basic implementation, to deisgn building blocks for future features
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


#####Internal Back-end: These methods will create the necessary objects to add to a buffer that the updateTurtle() command will read from.
* parseStringToCommand() -> parses string of code into commands
* makeNewCommand() -> creates user-defined command
* Function class -> knows function command, parameters; contains run() method to perform relevant operations
* FunctionFactory -> given a command, return the operations called in run() for that function (or something along those lines...)


The APIs can be extended with the addition of more public methods, or developing features that employ a variety of the existing methods. 

The classes discussed in the Overview UML Design are a basis for the MVC style program we want to create. Each class describes an important and different function within the design (explored very thoroughly in the Overview) and it seems that this design is the optimal solution for the program we want to develop.

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

