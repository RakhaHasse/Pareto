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

```text
Consumption = (timeConsumption + energyConsumption) / 2
