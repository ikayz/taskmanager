# Overview

This console task manager is a small Kotlin/JVM application for creating and managing a personal task list. It lets a user add tasks, view their status, mark tasks complete, and remove tasks. I built it to practice Kotlin syntax and object-oriented programming through a practical program, including data classes, collections, functions, and control flow.

[Software Demo Video](YouTube)

# Development Environment

- Kotlin 2.0.21 and Java 17 (configured through Gradle)
- Gradle with the Kotlin JVM and application plugins
- No external runtime libraries are used

## Run

Install Gradle and a JDK 17 or newer, then run from the project directory:

```sh
gradle run
```

The program keeps tasks in memory while it is running; the list resets when you exit.

## Kotlin concepts demonstrated

- `val` stores values that are not reassigned, such as task IDs and descriptions; `var` tracks values that change, including a task's completion state and whether the menu should continue.
- Expressions build menu output, calculate display status, and validate parsed task numbers.
- `if` expressions and `when` handle task state and menu choices.
- `while`, `for`, and collection operations search and display tasks.
- Top-level functions divide the menu, add, list, complete, remove, and lookup operations.
- `Task` is a data class, and `mutableListOf<Task>()` stores the task collection as it changes.
- Input is trimmed and checked; blank descriptions, invalid menu choices, nonnumeric IDs, and unknown task IDs are handled with messages.

## Manual test plan

1. Start the program and choose `2`; confirm the empty-list message appears.
2. Choose `1`, enter a blank description; confirm it is rejected and no task is added.
3. Add two tasks with descriptions; choose `2` and confirm both appear as incomplete with distinct numbers.
4. Choose `3`, enter the first task number; list tasks and confirm only that task is marked complete.
5. Try completing the same task again; confirm the program reports it is already complete.
6. Try `3` or `4` with a nonnumeric, nonpositive, and unknown task number; confirm each invalid input is handled without exiting.
7. Choose `4`, remove the second task, and list tasks to confirm it is gone.
8. Choose an invalid menu option, then choose `5`; confirm the invalid choice is explained and the program exits cleanly.

# Useful Websites

- [Kotlin Documentation](https://kotlinlang.org/docs/home.html)
- [Kotlin Basic Syntax](https://kotlinlang.org/docs/basic-syntax.html)
- [Kotlin Collections Overview](https://kotlinlang.org/docs/collections-overview.html)
- [Gradle Application Plugin](https://docs.gradle.org/current/userguide/application_plugin.html)

# Future Work

- Save tasks to a local file so they remain available after exiting.
- Add due dates or priorities and allow filtering by completion status.
- Add automated tests for task operations and input validation.
