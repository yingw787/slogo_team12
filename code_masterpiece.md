
CS308 Code Masterpiece: SLogo
===================

> Take part of the project and refactor it to demonstrate what you now think of as good design. It should represent a feature or component within your larger project that is small enough to be workable, but large enough to have some interesting design element. I use the term "component", rather than "class", because it may be several classes that work together, e.g., a superclass and its sub-classes or a few classes that work together. Note, if your component is specifically intended to be superclass, it must include enough detail that it is clear how to extend it and at least one example subclass to show it in "action".

> In addition to the code, include a section at the end of your written analysis describing the component's purpose and why you think it is well designed (i.e., what you think it shows that you have learned so far in the course). Your code masterpiece must be something you wrote or something you refactored significantly. If you choose to refactor someone else's code, strongly justify your decision in your written analysis.

----------

In SLogo, I refactored CommandFactory.java as well as created SyntaxFileParser.java and LanguageFileParser.java. SyntaxFileParser.java and LanguageFileParser.java are a refactoring of RegexUtil.java, but formats the output as a hashmap that can take in whatever string the text interpreter returns and returns the command string. I created two parsers because the SyntaxFileParser uses regexes and the LanguageFileParser does not. 

I significantly refactored Elizabeth Dowd's CommandFactory.java because I felt that there could be a far simpler and more flexible way of finding and returning Command objects than using a switch case tree. In class, we discussed reflections, the ability to find classes and generate objects by only using a string input. While we discussed this after Elizabeth created CommandFactory.java, I thought this would be perfect for refactoring, since I had thought beforehand of trying to create a Command object from a string but didn't know how. 

My code for CommandFactory.java is about 70 lines long including comments, whitespaces, import statements, syntax, and a main function that is used for unit testing; the important code is a mere 14 lines long. This is a significant contrast from our original code which was around 180+ lines long, and makes it far more readable and flexible. Since there are no hardcoded data structures, user defined commands can be created in the commands package, and a string added to the hashmap for lookup. This program also runs significantly faster since hashmap lookups are a very likely O(1) due to well-written hashing functions while switch-case trees are a significantly possible O(N) with no possible optimizations. 

This code is important due to its critical nature in our interpreter; every command that needs to be parsed runs through LanguageFileParser.java as well as CommandFactory.java. Optimizing the performance of this code is essential to reducing the obvious bottlenecks that would come from processing thousands of commands if it came to it, and providing simplicity and ease of use is also important in the maintainability of the code as well. 






 
