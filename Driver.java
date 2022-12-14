/*
 * Gravino, Andrew
 * CS3560.03 Object-Oriented Programming and Design
 * Yu Sun
 * Project 2 due 11/10/22
 */

 /*
  * Understand:
  Using Java Swing to create a GUI
  - In the GUI, we have an admin control panel that allows us to create users and user groups and provides statistics
  - Users have unique elements to them that display their correlation to other users
  - Users can be part of user groups
  - Tweets being display will be available for all to see as long as they are subscribed to a certain user

  Running
  - Buttons will make everything happen through the admin panel
  - Creation of anything while running is mandatory but deletion isn't
  - User feeds should be updated and refreshed automatically without having to open pages first

  * Match:
  - Hierarchies and patterns
  - Interfaces
  - Patterns required for usage are singleton, observer, visitor, and composite
  - focus on logic first before the presentation of visuals! 
  - We start with a pattern and try to fit these design patterns into the ui elements
  - Implementation of the root tree will be helpful in iterating through the loops and grabbing all the data necessary
  */

public class Driver{
    public static void main(String[] args){ //This is the main method that will retrieve an instance of the admin's control panel and then make it visible
        Admin.getInstance().runWindow();
    }
}