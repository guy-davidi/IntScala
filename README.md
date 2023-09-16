# Task Manager Application

**Table of Contents**
- [Overview](#overview)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installing OpenJFX](#installing-openjfx)
  - [Running the Application](#running-the-application)
- [Usage](#usage)
- [Built With](#built-with)
- [Author](#author)
- [License](#license)

## Overview

The Task Manager Application is a simple command-line and JavaFX-based task management tool built with Scala. It allows you to add, list, mark as completed, and delete tasks.

## Features

- Add tasks with titles, descriptions, and due dates.
- List all tasks, including their details.
- Mark tasks as completed.
- Delete tasks from the list.
- Save tasks to a file for persistence.
- Load tasks from a file when starting the application.

## Getting Started

### Prerequisites

To compile and run the application, you need the following installed on your system:

- Scala
- Java Development Kit (JDK)
- JavaFX (OpenJFX)

### Installing OpenJFX

If you haven't already installed OpenJFX, follow these steps:

Update your package repository:

```
  sudo apt update
  sudo apt install openjfx
```
### Running the Application
Clone this repository to your local machine:
```
  git clone https://github.com/yourusername/task-manager-scala.git
```
Navigate to the project directory:
```
  cd task-manager-scala
```
Compile the Scala code:
```
  scalac TaskManager.scala
```
Run the application:
```
  scala TaskManager
```

## Usage

Follow the on-screen prompts to interact with the application.
You can add tasks, list tasks, mark them as completed, and delete tasks.
The tasks are saved to a tasks.txt file for persistence between runs.

## Author
Guy Davidi

## License
This project is licensed under the MIT License - see the LICENSE.md file for details.

