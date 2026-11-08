data class Task(
    val id: Int,
    val title: String,
    var isComplete: Boolean = false
)

fun main() {
    val tasks = mutableListOf<Task>()
    var nextId = 1
    var isRunning = true

    println("Welcome to the Kotlin Task Manager!")
    while (isRunning) {
        showMenu()
        print("Choose an option: ")
        when (readlnOrNull()?.trim()) {
            "1" -> {
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

fun showMenu() {
    println("1. Add task")
    println("2. List tasks")
    println("3. Mark task complete")
    println("4. Remove task")
    println("5. Exit")
}

fun addTask(tasks: MutableList<Task>, id: Int): Boolean {
    print("Enter a task description: ")
    val title = readlnOrNull()?.trim().orEmpty()
    if (title.isBlank()) {
        println("Task description cannot be empty.")
        return false
    }

    tasks.add(Task(id = id, title = title))
    println("Added task #$id: $title")
    return true
}

fun listTasks(tasks: List<Task>) {
    if (tasks.isEmpty()) {
        println("Your task list is empty.")
        return
    }

    println("Your tasks:")
    for (task in tasks) {
        val status = if (task.isComplete) "complete" else "incomplete"
        println("${task.id}. [${if (task.isComplete) "x" else " "}] ${task.title} ($status)")
    }
}

fun markTaskComplete(tasks: MutableList<Task>) {
    val task = findTaskById(tasks) ?: return
    if (task.isComplete) {
        println("Task #${task.id} is already complete.")
    } else {
        task.isComplete = true
        println("Marked task #${task.id} complete.")
    }
}

fun removeTask(tasks: MutableList<Task>) {
    val task = findTaskById(tasks) ?: return
    tasks.remove(task)
    println("Removed task #${task.id}.")
}

fun findTaskById(tasks: List<Task>): Task? {
    if (tasks.isEmpty()) {
        println("There are no tasks to choose from.")
        return null
    }

    listTasks(tasks)
    print("Enter the task number: ")
    val id = readlnOrNull()?.trim()?.toIntOrNull()
    if (id == null || id <= 0) {
        println("Please enter a valid positive task number.")
        return null
    }

    val task = tasks.find { it.id == id }
    if (task == null) println("No task found with number $id.")
    return task
}
