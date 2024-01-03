In my opinion,the most important part in this project is
to figure out that Different task requirements require the creation of different subclass objects.


According to my project, the Player class is the super class.
In order to meet the task requirements and implement AI selection of different difficulties, I created several subclasses of the player class,
such as ComputerPlayer class for meeting easy AI task, Player1 for meeting enable player  enter a secret code by themselves, HardComputerPlayer class for meeting Hard AI task.
Moreover,i make Player class absract since different object class needs different method in details.
For example, the FilePlayer, subclass of Player, in this class,we need to read guess from file,
so the makeGuess() method needs to be overrided in a different way.
But in HardComputerPlayer class, i can not Let the computer use the algorithm required by the question to make efficient predictions.
Finally,with the help of teacher,i make it.

All in all, this project requires us to apply almost all the knowledge points we learned earlier,
such as inherit, abstract,IO,Array List,loop. It is a really good chance to review the past and learn something
new about Java.