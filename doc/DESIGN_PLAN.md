# Introduction
The goal of this project is to create an IDE that allows a user to type code into a terminal and receive real time visual feedback for that code. Specifically, the language being implemented for this IDE is Simplified Logo (SLogo). The user will be typing SLogo commands into a terminal window and, when run, will see the real time updates on display. The user will also be able to view errors, command history, current variables, language, and access the help menu.

The general architecture of the program will involve a front end, controller, and backend. The front end will keep track of the visual aspects of the program. No state logic will take place in this part of the code. The controller will handle all parsing logic, and the backend will keep track of state. The general flow of logic will be that the front end sends the newest command to the controller; the controller parses the command and tells the backend to update the states; the backend tells the controller the steps to update the front end; and finally the controller passes these steps back to the front end.

The front end will be flexible in general design. It will mostly be focused on how to display text, colors, and objects. The front end will be rigid in that it will never perform state logic. The controller will require the most rigidity as it simply parses and tells the backend to update state. The backend will be flexible in the command steps it returns but rigid in how it keeps track of state.

# Overview
## Back-End External and Internal API
The back-end is split into `Command`, `Controller`, and `Model`.

Specific changes to the `View` are made through the `Command` interface. Parsing of user-input into commands for the `View` are done through the `Controller` interface. Accessing and modifying the state of the program is done through the `Model` interface.

As such, the external API for the back-end aims to convert user-input into specific instructions on changing the visual apperance of the front-end. 

`SpecificCommand` classes implement the `Command` interface, and then define an appropriate `execute()` method depending on what command is represented by this for them that takes a view object as a parameter. 

## Front-End External API
The only public methods contained within the front-end's external API are methods to modify the visual appearance of our GUI.

The external `BasicView` interface for the front-end will be passed to the `Controller`, which is then able to call the methods which change the visual appearance of the GUI.

As the front-end is passed to other classes as an interface specifically designed for this purpose, only methods explicitly defined within the interface will be accessible to the other classes. This allows for encapsulation. 

As the front-end does not store state, the back-end should not require information from the front-end. (Apart from sending user-input.)

The front-end should not throw any errors as all error handling should be complete within the front-end. 
## Front-End Internal API
The front end will follow the below hierarchy:
* GUI
    * TurtleWindowPanel
        * Turtle
        * Line
    * ErrorBoxPanel
    * CommandUIPanel
        * UserInputBox
        * CommandActionButtons
    * ConfigurationUIPanel
        * ConfigViewSelector
        * UserTextView
    * LanguageSelectorPanel
    * UserModifiableParametersPanel
        * SpecificModifiableParameter

The overall GUI will be strucured according to the Composite design pattern. The GUI will support a flexible number of panels. As such, it will be easy for future panels to be added to the GUI as necessary.

Within each GUI panel, abstract superclasses will allow for new features to be easily added. For instance, we currently plan to have the views of user variables, user commands and command history extend the `UserTextView` superclass. Additional types of text views can easily be added by extending this `UserTextView` superclass as well.

In general, we expect any new feature to only require extension of a particular abstract superclass. As such, the general structure of the GUI comprising of multiple `Panel` objects should be fixed.

No errors will be thrown, as all error handling will be completed within the front-end.

## Visual Representation of Relation between Components
![](https://i.imgur.com/QeWpt6M.jpg)

# User Interface
The image below shows the browser version of a Logo IDE. The user interface designed in this project will be very similar to the one below.

![](https://i.imgur.com/LKIViG6.png)

There will be a drop down menu that allows the user to select from any of 10 languages. There will be a display area where the turtle will draw pictures. Below the display will be a text area that allows the user to input code. Unlike in the Logo image above, this project will always require the user to hit a "Run" button in order to start the program instead of sometimes using a "Run" buttom and sometimes using the "Enter" key.

All features that will all be displayed on the UI are below:
* Enter commands to the turtle interactively by entering text commands into a text area
* A "Run" button to start the program and a "Clear" button to clear the text area
* See the results of the turtle executing commands displayed visually
* See errors that may result from entered commands in a user friendly way (i.e., not just printed to the console)
* Set a background color for the turtle's display area
* Set an image to use for the turtle
* Set a color to use for the pen
* See commands previously run in the environment
* See variables currently available in the environment
* See user-defined commands currently available in the environment
* Choose the language in which SLogo commands are understood (here are a few translations)
* Access help about available commands

A hand drawn version of what our user interface might look like can be seen below:

![](https://i.imgur.com/bWmJSFo.jpg)

## Error Handling Capabilities
1. User-input has incorrect syntax (missing parameters etc.)
2. User-input has incorrect commands

# Design Details
## General API
The list of all APIs can be seen in **API as Code** below.

The frontend will consist of a class which implements `MovementView`, `BasicView`, `StaticView`, and `ConfigurationView`. These interfaces define the main functionality of the frontend and how it will interact with the controller and backend.

The controller will extend `Language` and `ControllerInterface`. These two interfaces give the controller a structure that defines how it receives input, sends it to the backend, and returns to the frontend. Additionally, the `Language` interfaces gives the controller a regional language.

The backend implements the `StaticModel`, `MovementModel`, and `ListModel` interfaces. These various interfaces allow for modifying the rotation and position of the turtle's state in the backend. Additionally, the `ListModel` allows the backend to hold a list of commands and return that list to the controller (and then to the frontend).

The general flow is that the front end calls `Controller.parseCommands(COMMAND_STRING)`. The parse method then uses a set of internal methods to turn the string into a set of operational commands. The `Controller` then calls each command on the model. As it does this, the model adds each new `Command` object to a list and updates the turtle's state. When the `Controller` has finished parsing all the commands, it asks the model for it's list. This `List<Command>` is then returned to the frontend. Within the frontend view, each `Command` is looped over and `Command.execute(MODIFIED_VIEW)` is called to update the view's state.

## Specific Features Supported
* Enter commands to the turtle interactively by entering text commands
    * The text box on the user interface allows users to enter commands
* See the results of the turtle executing commands displayed visually
    * The various frontend sends the commands to the backend which then return commands to change the view. The View interfaces allow for the commands to update the turtle to be displayed visually.
* See errors that may result from entered commands in a user friendly way (i.e., not just printed to the console)
    * There is a display error command that is a subclass of the Command interface. This will tell the View to displayErrorMessage() which displays the appropriate error.
* Set a background color for the turtle's display area
    * This is one of the variable values of the front end and can be changed from the picking mechanism on the right side of the screen.
* Set an image to use for the turtle
    * This is one of the variable values of the front end and can be changed from the picking mechanism on the right side of the screen.
* Set a color to use for the pen
    * This is one of the variable values of the front end and can be changed from the picking mechanism on the right side of the screen.
* See commands previously run in the environment
    * The Controller keeps a list of recently run commands. If the user wishes to see this, he can select the dropdown menu on the right side. The frontend will then query the controller for the most recent commands and display the returned list.
* See variables currently available in the environment
    * The Controller keeps a map of all current variables. If the user wishes to see this, he can select the dropdown menu on the right side. The frontend will then query the controller for the current variables andd display the returned mappings.
* See user-defined commands currently available in the environment
    * The Controller keeps a list of user defined commands. If the user wishes to see this, he can select the dropdown menu on the right side. The frontend will then query the controller for the user defined commands and display the returned list.
* Choose the language in which SLogo commands are understood (here are a few translations)
    * The frontend will have a dropdown for language. This will then tell the controller to update its language and the front end will display everything in the new language.
* Access help about available commands
    * This will be one of the dropdown options on the right side of the page.

## Justification
The MovementView, StaticView, and ConfigurationView are needed to provide the basic functionality required for the front end. It was decided to separate these two into separate interfaces so that a class created later on could have only the ability to have private variables updated, objects added, or messages displayed, but not necessarily access to all of these properties simultaneously. For example, if a command only needs to add a line to the stage, this allows the code to cast the wview as a MovementView before passing it to the command. Therefore, the command won't be able to display messages or modify private variables.

The language interface provides properties very different from any other interface. Plus there could be multiple parts of the program that require the language to be changed. Ergo, it was necessary to make this its own interface with its own language functionality.

The ControlInterface was created to define the functionality of the controller. This way the front end will know exactly what public methods the controller has and exactly how it is able to manipulate the controller. This interface is not needed anywhere outside of the controller.

For the same reasons the front end was broken up, the backend was broken up into separate interfaces. The ListModel tells the controller that it is able to get and clear a list from the backend. This is necessary because the Controller needs a list to return to the front end. The MovementModel was created for the backend for the same reasons the MovementView was created for the front end. Ditto for the StaticModel.

The Command interface was created to define the public functionality of a command object. There will be many different commands in this code, but it is necessary that they all have an execute method to update the view.

## Extendability
Our main focus with regards to keeping the code extendable is functionally separating the front end and back end. The goal is to create a model that can create Command Objects given some parsed string information from a controller, which can then be interpreted very simply by the front end. As long as the back end continues to return this list of Command Objects, the front end can work without any changes. This makes it very easy to add new elements and logic to the UI, and also as easy to add new functionality to the back end - for example adding more possible command objects that the model knows how to create.

# API as Code
### View:
```java=
package view;

public interface BasicView {
}


package view;

import utilities.Coordinate;

public interface MovementView extends BasicView {

    void addLine(Coordinate one, Coordinate two);

    void moveTurtle(int turtleIndex, Coordinate one, Coordinate two);

    void rotateTurtle(int turtleIndex, double degrees);

}


package view;

public interface StaticView extends BasicView {

    void changeBackgroundColor(String color);

    void displayErrorMessage(String message);

    void hideTurtle(int turtleIndex);

    void showTurtle(int turtleIndex);

    void clearScreen();

}

package view;

import java.util.List;
import java.util.Map;

public interface ConfigurationView extends BasicView {

    void displayErrorMessage(String message);

    void updateCustomVariables(Map<String,String> variables);

    void showHistory(List<String> history);

    void updateUserCommands(List<String> userCommands);
}

```

### Controller:
```java=
package controller;

public interface Language {

    void changeLanguage(String language);
}


package controller;

import utilities.Command;

import java.util.List;
import java.util.Map;

public interface ControlInterface {

    List<Command> parseCommands(String command);

    Map<String,String> getCurrentVariables();

    List<String> getHistory();

    List<String> getUserCommands();
}
```

### Model:
```java=
package model;

import utilities.Command;

import java.util.List;

public interface ListModel {

    List<Command> getCommandList();

    void clearCommandList();

}

package model;

public interface MovementModel {

    void move(double distance);

    void rotate(double degrees);

    void draw(double distance);

}

package model;

public interface StaticModel {

    void togglePen(boolean pen);

    void showTurtle(boolean turtle);

    void clearScreen();

    void getX();

    void getY();

    void getHeading();

    void getPenShow();

    void getTurtleShow();

}
```
### Utilities:
```java=
package utilities;

import view.old.View;

public interface Command {
    void execute(BasicView view);
}
```

## Exceptions
If the command given to the controller is not readable (i.e. it is given a command that doesn't exist) it will call a compilation error. If the model cannot do a specific command, it will throw a command error for which the controller will catch. All errors would be thrown from the controller to the view so the view can display these errors.

# Design Considerations
## Decision Discussed at Length
The largest decision we discussed at length was how to update the front end state. The two major solutions we discussed were
1. Sending a reduced copy of the front end to the backend and allowing the backend to call specific public methods of the front end
2. Sending the command string to the backend and allowing the backend to send back a set of commands which the front end executes on itelf.

The pros and cons of each are listed below.

## Chosen Solution
We chose solution (2) that implements the front end sending data to the backend, then awaiting for the backend to return a list of commands to execute.

### Pros
The first pro of this solution is that allows the front end API to remain independent of the backend. The backend never has to hold a front end object and is not dependent on the front API. Instead, the backend simply takes data, changes its state, then returns a list of commands to execute.

The second pro for this solution is that it follows a more standard web development practice of sending data between the front end and backend without the backend knowing what is sending it data. In a web application, a backend doesn't require an instance of the front end to work. Instead, it simply receives data and reacts to it as is done here.

### Cons
A con of this solution is that it is slow. It requires data to be sent from the front end to the backend, a set of steps to be calculated in the backend, these steps required back to the front end, and finally the front end to execute them. This is slower than the back end simply calling the front end API.

## Alternate Solution
The alternate solution not used was to send a reduced version of the front end object to the backend and allow the backend to call a specific subset of the front end API.

### Pros
This solution is faster than sending data back and forth. Since the backend already has a copy of the frontend then it can simply call its API without having to worry about sending data back. Additionally there is no worry of data being corrupted en route.

Another pro of this solution is that it is easy to understand. The front end object is passed to the controller, then the backend. Then the backend calls the frontend API to make changes. This is less complex than creating a list of commands and sending those to the front end to be executed.

### Cons
The major con for this solution is that it requires passing instances of the front end around. Although the security risks of this can be reduced via interfaces, the backend still has to keep track of and hold onto a front end object. This does not follow standard web development architecture.

A second con of this solution is that it requires the backend know the front end API. This a large dependency requirement and not one desireable for an amorphous backend solution.

## Assumptions
* The `Command` classes will be aware of the front end API
    * Since the backend will return a list of commands to the front end, and these commands will call the front end API, it will be necessary for the `Command` subclasses to be aware of the frontend API. This will hinder the frontend's ability to modify or update the API later on.
* That there will be only one turtle on the screen
    * This assumption was made intentionally to simplify the basic version of this project. It is very likely that later on the specification will be expanded to include multiple turtles. This design plans for that with a _turtle index_ but does not include any specific API calls that allow for adding/removing a turtle, or performing any other operations related to more than one turtle.

# Team Responsibilities
* Joshua Medway (jwm58)
    * Backend
    * working to parse the commands given from the view/create the proper methods for the model
    * creating some command subclasses
* Sumer Vardhan (sv110)
    * Backend
    * Work to encapsulate the model as well as possible
    * Work on some command subclasses
* Goh Khian Wei (kg189)
    * Front end
    * Creating some command subclasses
* Ben Lawrence (bcl19)
    * Front end
    * Creating some command subclasses

# Use Cases
Clearly show the flow of calls to public methods described in your design needed to complete each example below, indicating in some way which class contains each method called:

* `fd 50`
    * Frontend calls Controller.parseCommands("fd 50")
    * Controller calls Model.draw(50)
    * Controller calls Model.move(50)
    * Controller calls getCommandList()
    * Controller returns a `List<Command>` to Frontend
    * Frontend loops through each command calling Command.execute(MovementView)
    * The Command calls MovementView.addLine(c1, c2)

* `rotate sum 10 10`
    * Frontend calls Controller.parseCommands("rotate sum 10 10")
    * Controller calls Model.rotate(20)
    * Controller calls getCommandList()
    * Controller returns a `List<Command>` to Frontend
    * Frontend loops through each command calling Command.execute(MovementView)
    * The command calls MovementView.rotateTurtle(1, 20)

* `repeat 2 [ fd 10 ]`
    * Frontend calls Controller.parseCommands("repeat 2 [ fd 10 ]")
    * Controller calls Model.draw(10)
    * Controller calls Model.move(10)
    * Controller calls Model.draw(10)
    * Controller calls Model.move(10)
    * Controller calls getCommandList()
    * Controller returns a `List<Command>` to Frontend
    * Frontend loops through each command calling Command.execute(MovementView)
    * The Command calls MovementView.addLine(c1, c2)

* `SETXY COS XCOR SIN YCOR`
    * Frontend calls Controller.parseCommands("SETXY COS XCOR SIN YCOR")
    * Controller calls Model.move(_calculatedX_, _calculatedY_)
    * Controller calls getCommandList()
    * Controller returns a `List<Command>` to Frontend
    * Frontend loops through each command calling Command.execute(MovementView)
    * Command calls MovementView.moveTurtle(1, _calculatedX_, _calculatedY_)

* `fd cat`
    * Frontend calls Controller.parseCommands("fd cat")
    * Controllers realizes this is an error while parsing
    * Controller makes list with only error command object
    * Controller returns a `List<Command>` to Frontend 
    * Frontend loops through each command calling Command.execute(MovementView)
    * Command calls MovementView.displayErrorMessage(error.message)

* Changes background color from "#000000" to "#1EFA33" via UI
    * Frontend calls View.changeBackgroundColor("#1EFA33")