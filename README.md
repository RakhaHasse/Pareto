# Pareto

**Pareto** is a small Java Swing application for comparing tasks by their **subjective productivity**.

The idea is simple: different tasks require different amounts of time and energy, while also giving different levels of immediate and future value.  
This application helps the user compare such tasks using a compact personal productivity formula.

## Features

- add tasks with custom parameters
- estimate time and energy consumption
- estimate current and expected future result
- automatically calculate:
  - **Consumption**
  - **Result**
  - **Productivity**
- compare several tasks in a table view

## How it works

Each task is described by the following values:

- **Task name**
- **Time consumption**
- **Energy consumption**
- **Now result**
- **Prognosis result**

The app then calculates three derived values.

### Consumption

Consumption is the average of time and energy costs:


Consumption = (timeConsumption + energyConsumption) / 2

### Result

Result is the average of current and expected future benefit:

Result = (nowResult + prognosisResult) / 2
### Productivity

Productivity is calculated as:

Productivity = Result / Consumption

This allows the user to compare tasks not only by effort, but also by their perceived return.

### Example

Let us say you enter the following task:

Task name: Reading
Time consumption: 4
Energy consumption: 2
Now result: 3
Prognosis result: 5

The app will calculate:

Consumption = (4 + 2) / 2 = 3
Result = (3 + 5) / 2 = 4
Productivity = 4 / 3 = 1.33
Project structure
src/
 ├── Task.java
 ├── TasksList.java
 ├── TableModel.java
 └── Frame.java
Pareto.jar
README.md
LICENSE
Main classes
Task

Represents a single task and stores its parameters.
Also calculates consumption, result, and productivity.

TasksList

Stores and manages the collection of tasks.

TableModel

Provides the table model for displaying task data in the interface.

Frame

Contains the main Swing window and user interface logic.

### Requirements

To run the application you need:

Java 8 or newer
Running the application
Option 1 — run the JAR file

If Java is installed, run:

java -jar Pareto.jar
Option 2 — run from source
Clone the repository:
git clone https://github.com/RakhaHasse/Pareto.git
Open the project in your IDE
For example:
IntelliJ IDEA
Eclipse
NetBeans
Build and run the application
How to use
Launch the application
Enter the task name
Fill in:
time consumption
energy consumption
now result
prognosis result
Add the task to the table
Repeat for other tasks
Compare the productivity values
Purpose

This project is a small personal productivity tool.
It is not intended as a scientific measurement system.
Instead, it helps the user reflect on how “worthwhile” different activities feel subjectively.

It can be useful for:

self-analysis
comparing habits and routines
prioritizing personal tasks
reflecting on effort versus return
Future improvements

Possible next steps for the project:

better input validation
editing existing tasks
saving/loading tasks from file
exporting results
improved UI design
stronger separation between UI and business logic
screenshots and demo examples in README
License

This project is licensed under the MIT License.
```text
