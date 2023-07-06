# UML diagrams
The design side of the project, with some UML (Unified Modeling Language) diagrams which allow to understand what is the aim for the project in its integrity. For all the UML diagrams and an easy way to create them, see [here](https://plantuml.com/). The UML diagrams for this project have been created with [draw.io](https://app.diagrams.net/).

## Table of contents
- [Activity diagram](#Activity_diagram)
- [Sequence diagram](#Sequence_diagram)
- [Class diagram](#Class_diagram)
    - [Cardinality examples](#Cardinality_examples)

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
The [UML Class diagram](https://plantuml.com/class-diagram) allows to specify the features of all the project classes, regarding all their attributes and the methods and constructors' signature and the interconnections between them. Each attribute, method and class can assume three types of visibility (private, public, protected), basing on which other classes can see it and which not. Each class described in this diagram is principally composed of:

- the class name
- the name and the type of each attribute. If an attribute is underlined, it means that it is a constant
- the name, the return type and the eventual parameter types of the methods and constructors

Some classes can be grouped by a package. In this project, the classes in this diagram are grouped by the same packages of the effective [Java](https://docs.oracle.com/javase/8/docs/api/) files.

Each class is interconnected by one or more classes with an association composed of a cardinality and a name for each side of the association. The name describes the connection between the classes, the cardinality represents the number of object class that can be created in the class within is connected.

These are the possible cardinalities in the class diagram:

- 1, one and only one
- 0..1, zero or one
- M..N, from M to N (integer numbers)
- 0..*, from zero to an any positive integer number
- 1..*, from one to an any positive integer number

### Cardinality examples
Here examples of some cardinalities described in this project's class diagram.

- Each Article object belongs to various Library objects, but at least one. Each Library object contains various Article objects, but at least one
- Each Library object belongs to different Elaborator objects, but at least one. Each Elaborator object contains one and only one Library object
- Each Downloader object belongs to various Argparser objects, but at least one. Each Argparser object has one and only one Downloader object

![ClassDiagram](../../src/site/UML/DesignModel.drawio.png)
