CompSci 308: Slogo Addition
===================
Estimation
=======
I think it will take me 30 minutes-1 hour to implement this feature (adding "WINDOW" and "FENCE" commands).  I will need to create a command class for each of the two new commands.  Because the command factory uses reflection to generate commands, I shouldn't have to update that.  I will also need to update all of the properties files to make sure they have the regular expression for these new commands.  Then I will need to make a couple small changes in the Controller and Turtle classes for the changes to register in the front end.

Review
=======
It took me 20 minutes to add this feature.  I didn't get it exactly right on the first try, but that was because I was unfamiliar with the front end code that I had to modify to accomplish this feature.  Because we used MVC for this project, it was clear to me how I had to update the model, view, and controller to finish the feature.  

I had to update all 8 language resource files, add 2 command classes, update the Controller, and update 2 front end classes (GUI and Turtle).  In total, I updated 5 Java classes and 8 properties files.  (But some of the Java updates were very trivial; I only added one method to Controller and GUI each).

Analysis
=======
I think the fact that it took so little time to add two completely new commands demonstrates that the way I designed the backend for this project is very extensible.  Furthermore, using the MVC design pattern to implement this project made the code more flexible and extensible.  We didn't implement the MVC exactly correctly during this project (because we understood it incorrectly), so one way to improve this project (and feature) would be to slightly refactor the way we structured the MVC.  Also, we desperately need to break the front-end down into more classes so it's more modular.  The front-end code was really what held me up in implementing this feature.

If I were not familiar with the code at all, it might take a little while to understand why I only had to add the two Command classes, but it's cool that someone unfamiliar with the project would not have to interact with any of the backend code at all to add new commands. 