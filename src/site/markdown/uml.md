# UML diagrams
The design side of the project, with some UML (Unified Modeling Language) diagrams which allow to understand what is the aim for the project in its integrity. For all the UML diagrams and an easy way to create them, see [here](https://plantuml.com/). The UML diagrams for this project have been created with [draw.io](https://app.diagrams.net/).

## Table of contents
- [Activity diagram](#Activity_diagram)
- [Sequence diagram](#Sequence_diagram)
- [Class diagram](#Class_diagram)

## Activity diagram
The [UML activity diagram](https://plantuml.com/activity-diagram-beta) allows to specify neatly the activities which should happen during the execution of the program. Here some base information on this diagram which could contains:

- the start of the execution represented by full coloured circle
- the execution flow represented by oriented arrows
- the activity represented by a rectangle with rounded edges
- the condition represented by a rhombus
- the parellel processing composed of different internal actions which are parallelly executed enclosed by a full strong line
- the end of the execution with a empty coloured circle with a red surface inscribed by a full coloured circle

In the case of the ArticleAnalyzer project, in this diagram are specified:

- all the possible actions the user can select in order to start the execution. See [here](run.html) if you want more details on the possible actions
- the verification of all the options selected, with the end of the program if:
    - no options received, with the printing of an error message before ending the execution
    - one or more options have not been correctly specified, with the printing of all of the possible options before ending the execution
- how to retrieve the articles, from The Guardian API or from files contained in a particular directory, basing on the response received from the data option
- the analyzation of the all the retrieved articles, from saving each article as an Article object into a Library object depending on which source the articles come from, to elaborating them, extracting their words and their occurrences (counted once in each article) and ordering them by the more occurrences a word has
- the printing of the results according to the chosen output method, the words to exclude from the response and the number of results to show

![ActivityDiagram](../../src/site/UML/ActivityDiagram.drawio.png)

## Sequence diagram
The [UML Sequence diagram](https://plantuml.com/sequence-diagram) allows to specify the different actors and implementation classes involved on the program. Although in this diagram the execution flow is represented by oriented arrows, here are also shown:

- the various interactions between the classes and what each one needs from the others with parameters (straight arrow) and return values (dashed arrow)
- the life line for each actor and class object created, represented by a rectangle perpendicular to the actor or the class
- the alternatives, reperesented by a big top-left-named rectangle which contains the different possible alternatives, enclosed by [], and the activities involved on each alternative

The activities sequence described in the diagram in this project are:

- the actor gives the command line arguments which are retrived by the Main class which start the program execution, demanding, first of all, to the Argparser object created if the options given are valid
    - if one or more options are not valid, the program terminates with an error message
    - if the given options are all correct, the Argparser object scans each option involving the object classes needed in order to proceed with the execution with "question and response calls" established
- after all the options are set, the Main class creates an Outputter object and an ArticleLoader object received by the internal Argparser ones
- the Main class obtains the Library object with all the Article objects and it will be analyzed by the created Elaborator object which will allow to print the results basing on the given options specified before

![SequenceDiagram](../../src/site/UML/SequenceDiagram.drawio.png)

## Class diagram
![ClassDiagram](../../src/site/UML/DesignModel.drawio.png)
