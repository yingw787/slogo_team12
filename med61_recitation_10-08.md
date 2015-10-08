Elizabeth Dowd (med61), Kevin Wang (kw1910)

1. adding new functions, making translate function extensible enough so that it behaves the same way for each node (that way all of the specific operations are contained invidividually within nodes)
2. the view doesn't have access to any of the functions in the backend except parse(String input) and the data structure/file that is returned with methods for the view to perform)
3. no input string passed back, commands have incorrect parameters; we will deal with this by throwing an error event/exception from the backend which causes the front-end to launch an error box
4. easy for user to create function, easy to add functions, easy to change language

1. (done)
2. 
* parsing a string that is correct
* parsing a string that is incorrect
* running a command from history
* running a command that is user made
3. translating the parse tree
4. creating the parse tree