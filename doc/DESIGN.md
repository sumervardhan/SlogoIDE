# What are the project's design goals, specifically what kinds of new features did you want to make easy to add
The design goals of this project were to have a linear transfer of commication between the front end and back end with a scalable front end architecture and a scalable back end architecture.

The back end held a separate class for each command and stored each command along with how many arguments it takes in a series of properties files. Depending on the command, the backend was set up in such a way, that new commands could simply be added to the properties files, a new class created and then the new command type was created. The front end consisted of the main elements of a state pane and turtle arena and then a series of feaures. Features are meant to be scalable because they are capable of taking in a interface implemented by the view (and therefore can make edits to the view) while also being instantiated via reflection.

Specifically for the frontend, different command panes should be easy to add. The frontend visualiser does not hardcode the kinds of control panes available for viewing in the GUI. Instead, it is able to display any pane whose node is added to the `ControlVisualiser` using the `addPane()` method.

Within each frontend GUI object, the exact layout of the object is also supposed to be easily modifiable. This is as most objects are initialised from properties files, and so all that is required to modify the layout of these objects is to change the properties file which the object is reading to initialise.

For the backend, different commands are also easy to be implemented, as java reflection is used to determine which command strings correspond to which commands, and so the actual commands available is not hardcoded anywhere in the java files.



# Describe the high-level design of your project, focusing on the purpose and interaction of the core classes
There are three main parts to this design: the ontroller, the model, and the view. The view is the front end object. This is what takes in commands from the user updates the UI, and sends commands to the backend. The controller is what handles taking in the string user commands and making sure those are sent to the parser, the results of the parser and executed, and the model is updated. The model keeps track of the state of the turtle arena. Although completely unconscious of how turtles are displayed on the screen, the model keeps track of the number of turtles, every turtle's state, and how the turtles move and rotate throughout the program.

The front end and backend communicate via a set of utilities called "Commands". These commands follow the [Command Design Pattern](https://www.oodesign.com/command-pattern.html). As the controller in the backend parses and executes the commands, the model adds these command objects to a list of commands. Finally, when the program has finished running in the backend, the list of commands is returned to the front end. The view then loops over every command and calls its execute function which, in turn, tells the view to change its appearance via a public method.

Communication between separate objects in the frontend is done through a simplified Observer Design Pattern (in that `attach()` and`detach()` commands are not implemented but can be easily added if necessary). This is to more closely align with the Single Responsibility Principle, with objects not being responsible for specifically changing other objects, only notifying them if a change within the object has occurred. 

The general flow of logic is 

        (user input)->        (update states)->
    View             Controller                Model
      <-(list of commands)    <-(changes in state)

# What assumptions or decisions were made to simplify your project's design, especially those that affected adding required features
We assumed that every workspace would start out with exactly one turtle and that turtle would be indexed to 0. This allowed the front end and back end to stay synced up on how many turtles were present and where when the user starts using the workspace.

We assumed that communication between the front end and the back end was all one way. The front end calls functions on the controller and the controller sends a list back. The controller nor model never call front end methods.

We made the decision that every command would be executed instantly, and this was a functionality decision that we committed to, never provisioning for non-instant command execution. This turned out to be a poor assumption that made it very difficult to refactor the code to incorporate animations later on.

We decided to not implement a wrapping boundary. This is as a wrapping boundary requires the backend to know even more information about the frontend, namely the dimensions of the turtle arena, which we did not think was necessary. 

Instead, we implemented an infinite turtle arena, which could be displayed by resizing the window on the computer screen. This way, there was better separation of the frontend and backend as the backend and model did not have to keep track of the view's actual display size and update the view accordingly, while still maintaining flexibility in displaying larger or smaller simulations as necessary.

While we stored a command history, we did not store a state history in the model. This is as we made the decision that 'undo' or 'reverse' would not be an allowable operation, as this was not a conventional programming feature. This turned out to make it extremely difficult to accomodate the 'undo' and 'redo' features added in the complete specifications.

# Describe, in detail, how to add new features to your project, especially ones you were not able to complete by the deadline
#### Adding a new command:
Let's assume that we are going to add the command `CHANGEDEFAULTPENCOLOR` to the command list. This command will change the default pen color when new turtles are created. The steps to do this are as follows:

1. Go to `src/resources/languages` and add `ChngDefPenColor=changedefaultpencolor|cdpc` to the `English.properties` file. Then add the corresponding version to all the other language files.
2. Go to `src/controller/operations/` and create a file called `ChngDefPenColor` that extends `Operation`.
3. In the `execute()` method for this class, fill in the apropriate implementation and add a line that says `myModel.addToCommandList(new ChngDefPenColorCommand(index))`.
4. Then go to `src/utilities/Commands` and add a file called `ChngDefPenColorCommand`.
5. Fill in the implementation for this class with the `execute()` method having the line `view.changeDefaultPenColor(index)`.
6. Next, go to `src/view/StaticView.java` and add a method whose signature is `void changeDefaultPenColor(int index)`.
7. Finally, go to `src/view/View.java` and add the method `changeDefaultPenColor(int index)` along with its implementation.

#### New component to the front end
1. If the component can be designed as a `Feature`, ensure it implements `Feature`. Most components can be designed as a `Feature` which has the two methods of `update()` and `getNode()` and can accept a parameter during initialisation, normally some form of `Observer`.
2. Add the necessary information of this new feature into the `features.properties` resource file.
3. This feature will now be added to the selector pane and can be used.
4. If the component cannot be designed as a `Feature`, it'll have to be initialised in the `View` and added to the `MainVisualiser` through the `MainVisualiser` `addPane(Node featureNode, String featureName)` method. It will then be usable.
5. Implement the necessary methods to notify the new component of changes or react to changes in the new component.


**Animation**

To implement animation specifically with a 'single-thread' approach, where no other visual changes can occur while exactly one animation is playing:

* Modify the necessary frontend external API methods (e.g. `addLine()`, `moveTurtle()`, `rotateTurtle()` etc) to return only when animation is complete, which can be done by setting animation's `setOnFinish()` to `return`.
* As every command is already executed sequentially through iteration of a `List<Command>` object, this is sufficient to allow for 'single-threaded' animation.
* Controlling the speed of the animation can be done by making these animation methods, such as the `addLine()` method etc mentioned earlier, set the animation speed at method invokation, instead of hardcoded. Additional view components can be created that signal the `View` to 
adjust the value of `animationSpeed` private variable, similar to existing view components that ask the `View` to change the language of other panes in the view etc.
