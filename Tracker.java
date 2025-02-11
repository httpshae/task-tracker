import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Tracker {

    static final Path trackerFile = Paths.get("tasks.json");
    private static List<Task> tasks;

    public Tracker() throws IOException {
        tasks = JsonParser.parseJsonToTasks();
    }

    public static int generateId() {
        return tasks.size() + 1;
    }

    public static void updateIds() {
        for(int i = 0; i < tasks.size(); i++) {
            tasks.get(i).updateId(i+1);
        }
    }

    public void updateTrackerFile() throws IOException {
        String json = JsonParser.toJsonArray(tasks);

        try {
            Files.write(trackerFile, json.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        } catch(IOException e) {
            System.out.println("There was an error writing to the tracker file");
            e.printStackTrace();
        }

        System.out.println("Task added to tracker successfully");
    }

    // triggered by --help
    public void displayCommands() {
        System.out.println("\nUsage: <command> <args>\n");
        System.out.println("command\t\t\targ1\t\t\targ2\t\t\tDescription");
        System.out.println("add\t\t\t\"task description\"\tnull\t\t\tAdd a task");
        System.out.println("update\t\t\ttask_id\t\t\t\"new description\"\tUpdate a task");
        System.out.println("delete\t\t\ttask_id\t\t\tnull\t\t\tDelete a task");
        System.out.println("mark-in-progress\ttask_id\t\t\tnull\t\t\tMark a task as in progress");
        System.out.println("mark-done\t\ttask_id\t\t\tnull\t\t\tMark a task as done");
        System.out.println("list\t\t\tnull\t\t\tnull\t\t\tList all tasks");
        System.out.println("list\t\t\ttodo\t\t\tnull\t\t\tList all todo tasks");
        System.out.println("list\t\t\tin-progress\t\tnull\t\t\tList all tasks in progress");
        System.out.println("list\t\t\tdone\t\t\tnull\t\t\tList all done tasks");
    }

    public void addTask(String description) throws IOException {
        Task task = new Task(description);
        tasks.add(task);
        System.out.printf("Task added successfully (ID: %d)\n", task.getId());
        updateTrackerFile();
    }

    public void updateTask(int taskId, String newDescription) throws IOException {
        tasks.get(taskId-1).setDescription(newDescription);
        tasks.get(taskId-1).setUpdatedAt();
        System.out.printf("Task %d updated successfully\n", taskId);
        updateTrackerFile();
    }

    public void deleteTask(int taskId) throws IOException {
        tasks.remove(taskId-1);
        System.out.printf("Task %d deleted successfully\n", taskId);
        updateIds(); // ensure that ids are still in order
        updateTrackerFile();
    }

    public void updateTaskByStatus(int taskId, TaskStatus status) throws IOException {
        tasks.get(taskId-1).setStatus(status);
        System.out.printf("Task %d marked as %s\n", taskId, status);
        updateTrackerFile();
    }

    public void printTaskDetails(Task task) {
        System.out.print(task.getId() + "\t");
        if(task.getDescription().length() < 8) {
            System.out.print(task.getDescription() + "\t\t\t");
        } else {
            System.out.print(task.getDescription() + "\t\t");
        }
        if(task.getStatus().toString().length() < 8) {
            System.out.print(task.getStatus() + "\t\t");
        } else {
            System.out.print(task.getStatus() + "\t");
        }
        System.out.print(task.getCreatedAt() + "\t\t");
        System.out.print(task.getUpdatedAt() + "\t\t");
        System.out.println();
    }

    public void listAllTasks() {
       if(tasks.isEmpty()) {
           System.out.println("No tasks found");
       } else {
           System.out.println("ID\tDescription\t\tStatus\t\tCreated At\t\tLast Updated At");
           for(Task task : tasks) {
               printTaskDetails(task);
           }
       }
    }

    public void listTasksByStatus(TaskStatus status) {
        if(tasks.isEmpty()) {
            System.out.println("No tasks found");
        } else {
            System.out.println("ID\tDescription\t\tStatus\t\tCreated At\t\tLast Updated At");
            for(Task task : tasks) {
                if(task.getStatus().equals(status)) {
                    printTaskDetails(task);
                }
            }
        }
    }
}