###Estimation
I think it will take me 1 hour to complete the feature of adding in an additional window to see the active turtle and it's image and giving the on click ability to change its image.

I think I will have to update 2 files. Mainly the GUI class where the turtle list is kept and then possibly the controller class to call the method that will start another view.

###Review
It took me 1 hour and 21 minutes to complete the feature. I didn't get it totally right the first time. I had it changing on the view but then not in the main Slogo window. I needed to update 3 files which were the GUI, the controller and then the PickImage class.

###Analysis
This exercise reveals that the front end code needs to be refactored quite a bit since pretty much everything that had to do with the front end was in the GUI. At least the buttons were in their own classes and that part was refactored but overall the GUI is kind of a mess. There should have been a way to change the image of the turtle that was on the main GUI from that class instead of passing the whole pane to the pickImage class.

I wasn't really that rusty with the code and it still took me over an hour to make this change. If someone was totally unfamiliar with the code I can imagine it taking much longer than that. It definitely wouldn't have been an enjoyable experience. 
