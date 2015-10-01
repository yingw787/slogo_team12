

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
What do commands know, when do they know it, and how do they get it?
How is the GUI updated after a command has completed execution?
