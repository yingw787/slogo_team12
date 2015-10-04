###Introduction
*This section describes the problem your team is trying to solve by writing this program, the primary design goals of the project (i.e., where is it most flexible), and the primary architecture of the design (i.e., what is closed and what is open). It should be approximately 200-300 words long and discuss the program at a high-level (i.e., without referencing specific classes, data structures, or code).*

Our goal of writing SLogo is to enable anybody to use and extend SLogo and be able to see their results in real time. This serves the purpose of enpowering people to learn coding and to expand their coding capabilities. 

The primary design objective of this project is to develop an integrated development environment (IDE) to support the SLogo language. We also want the user to be able to extend our IDE with minimum hassle, both on the front-end side and the back-end side. 

We will achieve this objective by implementing a model-view-controller (MVC) architectural pattern. This includes a distinct front-end (View), back-end (Model), and API communications logic (Controller). We intend to communicate through the controller through public APIs, for third-party extensibility. With our public APIs, any future extensions will only require the use of the APIs without having to modify existing classes, thus adhering to the Open-Closed Principle (OCP). We intend for all existing classes to be closed for modification to preserve backwards compatibility. We intend to build upon abstract classes and interfaces in order to provide maximum extensibility. Design patterns such as Factories for the intended inclusion of different commands and Singletons for the production of unique objects will help abstract features away from lower-level logic and decrease development time. 

###Overview
*This section serves as a map of your design for other programmers to gain a general understanding of how and why the program was divided up, and how the individual parts work together to provide the desired functionality. As such, it should describe the four APIs you intend to create (their purpose with regards to the program's functionality, and how they collaborate with each other) focusing specifically on the behavior, not the internal state. It should also include a picture of how the components are related (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a UML design program). This section should be approximately 1200-1600 words long and discuss specific classes, methods, and data structures, but not individual lines of code.*

We broke down our understanding of this problem in terms of what the user sees and expects our program to do. 


- Initialization of program 
A main class launches a Controller object, which has a run method to initialize a front end GUI/View class (which in itself initializes the various graphical objects) and a Backend/Model class.
- What does the user see? What can the user interact with? (languages, styling, etc.) How are these defined in classes?
The user sees the GUI/View class's graphical components displayed on the screen (the turtle, its canvas, a text field, a go button, a reset program button, a reset history button, and a clickable history section). The user can type things into the text field and submit them with the go button. The user can click on the 
- How does the user input programs? How does the user run programs? 
- How does the backend take in information from the frontend? (text, GUI information, other stuff) 
- Where does the API come into play? 
- How does the back-end return information to the front-end? 
- How do multiple events within this loop interact through time? 

In this project, users will enter commands in a GUI and will see a turtle on the screen move as a result of their commands. 
Logically it follows that we must have a set of classes to deal with everything displayed to the user, and a set of classes to deal with processing the user's command, storing command history, and deciding what actions need to occur to the objects on the screen. It seems to us that the Model-View-Controller approach will best suit the needs of the project. The four APIs described in the introduction will exist as public menthods in the controller and a few counterpart public methods in the front-end and back-end, which will only be used to communicate with the controller. 

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

###Design Details
*This section describes each API introduced in the Overview in detail. It should describe how each API supports specific features given in the assignment specification, what resources it might use, how it is intended to be used, and how it could be extended to include additional requirements (from the assignment specification or discussed by your team). Finally, justify the decision to create each class introduced with respect to the design's key goals, principles, and abstractions. This section may be as long as it needs to be and go into as much detail as necessary to cover all your team wants to say.*

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

