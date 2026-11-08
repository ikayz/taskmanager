/**
 * Represents a single to-do item in the task manager.
 *
 * @property id Unique numeric identifier assigned when the task is created.
 * @property title Brief description of what needs to be done.
 * @property isComplete Tracks whether the task has been finished (defaults to `false`).
 */
data class Task(
    val id: Int,
    val title: String,
    var isComplete: Boolean = false
)

/**
 * Entry point for the console Task Manager application.
 * Maintains the in-memory task list and runs the main menu loop until the user exits.
 */
fun main() {
    val tasks = mutableListOf<Task>()
    var nextId = 1      // Auto-incrementing ID so removed tasks don't cause ID collisions
    var isRunning = true

    println("Welcome to the Kotlin Task Manager!")
    while (isRunning) {
        showMenu()
        print("Choose an option: ")

        // Read user input safely and trim surrounding whitespace before matching
        when (readlnOrNull()?.trim()) {
            "1" -> {
                // Only increment the ID counter when a valid task was actually added
                val addedTask = addTask(tasks, nextId)
                if (addedTask) nextId++
            }
            "2" -> listTasks(tasks)
            "3" -> markTaskComplete(tasks)
            "4" -> removeTask(tasks)
            "5" -> {
                println("Goodbye!")
                isRunning = false
            }
            else -> println("Please enter a number from 1 to 5.")
        }
        println()
    }
}

/**
 * Prints the available menu actions to the console.
 */
fun showMenu() {
    println("1. Add task")
    println("2. List tasks")
    println("3. Mark task complete")
    println("4. Remove task")
    println("5. Exit")
}

/**
 * Prompts the user for a task description and adds a new [Task] to [tasks].
 *
 * @return `true` if the task was added, or `false` if the description was blank.
 */
fun addTask(tasks: MutableList<Task>, id: Int): Boolean {
    print("Enter a task description: ")
    val title = readlnOrNull()?.trim().orEmpty()

    // Reject empty or whitespace-only descriptions
    if (title.isBlank()) {
        println("Task description cannot be empty.")
        return false
    }

    tasks.add(Task(id = id, title = title))
    println("Added task #$id: $title")
    return true
}

/**
 * Displays all current tasks along with their ID, checkbox indicator, and completion status.
 */
fun listTasks(tasks: List<Task>) {
    if (tasks.isEmpty()) {
        println("Your task list is empty.")
        return
    }

    println("Your tasks:")
    for (task in tasks) {
        val status = if (task.isComplete) "complete" else "incomplete"
        val checkMark = if (task.isComplete) "x" else " "
        println("${task.id}. [$checkMark] ${task.title} ($status)")
    }
}

/**
 * Prompts the user to select a task by ID and marks it complete if it isn't already.
 */
fun markTaskComplete(tasks: MutableList<Task>) {
    val task = findTaskById(tasks) ?: return
    if (task.isComplete) {
        println("Task #${task.id} is already complete.")
    } else {
        task.isComplete = true
        println("Marked task #${task.id} complete.")
    }
}

/**
 * Prompts the user to select a task by ID and removes it from [tasks].
 */
fun removeTask(tasks: MutableList<Task>) {
    val task = findTaskById(tasks) ?: return
    tasks.remove(task)
    println("Removed task #${task.id}.")
}

/**
 * Displays the current task list, prompts the user for a task ID, validates the input,
 * and returns the matching [Task] (or `null` if the list is empty, input is invalid, or ID is not found).
 */
fun findTaskById(tasks: List<Task>): Task? {
    if (tasks.isEmpty()) {
        println("There are no tasks to choose from.")
        return null
    }

    listTasks(tasks)
    print("Enter the task number: ")

    // Parse input as an integer; toIntOrNull() avoids throwing NumberFormatException on invalid input
    val id = readlnOrNull()?.trim()?.toIntOrNull()
    if (id == null || id <= 0) {
        println("Please enter a valid positive task number.")
        return null
    }

    val task = tasks.find { it.id == id }
    if (task == null) println("No task found with number $id.")
    return task
}
