# slogo

A development environment that helps users write SLogo programs.

### Names of all people who worked on the project

Goh Khian Wei, Ben Lawrence, Joshua Medway, Sumer Vardhan

### Date you started, date you finished, and an estimate of the number of hours worked on the project

Date started: 8/10/19
Date completed: 29/10/19

**Goh Khian Wei**
Estimated number of hours spent: 40 hours

**Ben Lawrence**
About 40 hours

**Joshua Medway**
Hours: 45 hours

**Sumer Vardhan**

### Each person's role in developing the project
**Goh Khian Wei**
* Helped create the overall design of the project during the planning phase
* Helped create the external API of the frontend during the initial basic phase
* Helped design the `View` class which contained the Controller and Model, and which processed interactions between the GUI and the backend.
* Helped Ben with file reading, css formatting for several of his frontend classes.
* Created all files in `view/commandgenerator`, `view/commandline`, `view/mainpane`, `selector/variabletextfeature` (together with Ben), `selectorpane`, `statepane`,`turtlearena`.

**Ben Lawrence**
* Worked on general API during planning phase
* Created commands to be executed on front end in utilities package
* Created workspace builder and the ability to have multiple tabs with different turtle screens
* Worked on general view class and API which interfaced backend with different front end parts
* Added reading and writing of files for custom commands
* Added a number of the features such as command history, environment variables, user commands, language, etc

**Joshua Medway**
Creating the parser, as well as the back end commands

**Sumer Vardhan**

### Any books, papers, online, or human resources that you used in developing the project

Significant use of JavaFX documentation to understand how the various default view objects worked, such as the specific details of the `GridPane` versus `HBox`/`VBox` versus `StackPane` etc. 

Significant use of JavaFX css documentation to understand what parameters could be configured in css files and what had to be configured in the java files explicitly.

StackOverflow was used a couple of times along with a few other websites which are referenced via comments in the code.

### Files used to start the project (the class(es) containing main)
/slogo/Main.java is the only class with `public static void main`.

### Files used to test the project and errors you expect your program to handle without crashing
All given files were used to test to program. And the program is expected to display a window which shows "error" but does not crash the program. It will stop parsing all commands after the error and move the turtle up to that point.

### Any data or resource files required by the project (including format of non-standard files)

All required properties or css files are found within the various `resources` packages within the project. No additional resources directory is necessary.

### Any information about using the program (i.e., required resource files, key inputs, interesting example data files, or easter eggs)

The turtle arena pane is dynamically resizeable through clicking and dragging.

Editable text fields in the Turtle Properties pane can be edited, with (correctly formatted) changes reflected by the simulation, as a way of graphically controlling the turtles.

### Any decisions, assumptions, or simplifications you made to handle vague, ambiguous, or conflicting requirements
The requirements requested for users to be able to put views in any order they want. The decision was made to restrict views to conform to a grid with fixed number of columns, instead of a completely freeform order. This is to improve the visual design of the project as well as for ease of implementation.

The requirements request us to provide a way to move the current turtle(s) graphically and to provide a way to change the pen's current properties graphically. Instead of using just graphic buttons that would severely limit the flexibility of such an approach to graphical modifications, users are able to modify the turtles' properties which are displayed in the properties pane. These changes in state will then be reflected by the simulator.


When adding turtles to the turtle arena, it was unclear where a turtle should start. We decided to make all new turtles start at the center even if that meant they were on top of each other.

### Any known bugs, crashes, or problems with the project's functionality
All the example files given were run. All of them run successfully except for `multiple_turtles/multiPizza`, `multiple_turtles/spokedWheel`, and `multiple_turtles/spokedWheel2`. These three do not cause the program to crash. They just do not execute correctly for some reason.

Loading files does not work correctly. If the user tries to load a workspace file, the program with give an alert bug saying that this does not work but it will not crash.

There is no known way of causing the program to crash.

### Any extra features included in the project
To allow for the turtle to move to any location (without stopping at visual bounds) while not implementing the visually-confusing wrapping, we decided to allow the turtle to move out of bounds, similar to what is done in the online Logo simulator.

However, we implemented an extra feature of allowing the user to dynamically resize the turtle stage by clicking and dragging the window, which causes the turtle arena to dynamically resize while maintaining a constant center (0,0). This allows for the stage to take on a range of sizes. The user can, for example, expand the stage if the turtle is slightly out of bounds and be able to view the turtle again.


### Your impressions of the assignment to help improve it in the future
Basic and complete requirements for the frontend had wildly different workloads. Would have been better to even out the workload by putting more requirements from the complete to the basic sprint. 

The visual end result of cell society was more exciting than Slogo.


Ben: I enjoyed this assignment. I think this has been my favorite thus far. My only regret is not getting to work on the backend as well as the front end as that looked very fun as well.