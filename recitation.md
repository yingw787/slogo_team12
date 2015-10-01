

###High-Level Decription
Consider the following questions as you discuss the basic design elements of the SLogo project. 
Write a high-level description of the different parts of the project and 
how they can interact without worrying about the implementation details.

-When does parsing need to take place and what does it need to start properly?

Parsing will take place when a user submits/"enters" a command/input file.
A parser needs a pre-initialized factory for methods/interpretations. 
A set of "rules" for how to parse/what kind of input to expect, and error handling.

What is the result of parsing and who receives it?
The result of parsing, a coherent sequential list of functions (which will be accompanied by parameters).
The back engine 

When are errors detected and how are they reported?

Depending on whether the parser will create function objects or not, that object will be the one to detect whether or not to throw an exception because it will be reading from a set of legitimate function calls and relevant parameters. They will be reported as specific exceptions and pass it back to the frontend logic to display. 

What do commands know, when do they know it, and how do they get it?

Commands will know how many and what type of parameters they will pass in. They will know the operations being executed on those parameters, and ----- It will also know all relevant information needed to pass to the backend engine. They will know it their requirements before the user types in the command into the text field. 

How is the GUI updated after a command has completed execution?

The backend logic can pass the completed instruction into a buffer, and when the GUI steps during animation, the frontend logic will execute the instruction within the buffer. 

API Development 

Frontend-to-backend API 

Backend-to-developers API 

Frontend-to-developers API 

Backend-to-frontend API 

