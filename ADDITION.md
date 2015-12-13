CompSci 308: Slogo Addition
===================
Estimation
=======
I think it will take me 30 minutes-1 hour to implement this feature (adding "WINDOW" and "FENCE" commands).  I will need to create a command class for each of the two new commands.  Because the command factory uses reflection to generate commands, I shouldn't have to update that.  I will also need to update all of the properties files to make sure they have the regular expression for these new commands.  Then I will need to make a couple small changes in the Controller and Turtle classes for the changes to register in the front end.