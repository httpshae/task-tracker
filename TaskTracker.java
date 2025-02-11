import java.io.IOException;

public class TaskTracker {
    public static void main(String[] args) throws IOException {

        Tracker tracker = new Tracker();

        String command = args[0];

        switch(command) {
            case "--help" -> {
                tracker.displayCommands();
                break;
            }
            case "add" -> {
                if(args.length != 2) {
                    if(args.length > 2) {
                        System.out.println("Incorrect usage. Too many arguments detected.");
                        System.out.println("Usage: add <description>");
                        System.out.println("To get the full list of commands, input --help.");
                    } else {
                        System.out.println("Incorrect usage. Please provide a description for the task.");
                        System.out.println("Usage: add <description>");
                        System.out.println("To get the full list of commands, input --help.");
                    }
                    break;
                } else {
                    tracker.addTask(args[1]);
                }
            }
            case "update" -> {
                if(args.length != 3) {
                    if(args.length > 3) {
                        System.out.println("Incorrect usage. Too many arguments detected.");
                        System.out.println("Usage: update <task_id> <new_description>");
                        System.out.println("To get the full list of commands, input --help.");
                    } else {
                        System.out.println("Incorrect usage. Please provide a task ID and a new description for the task.");
                        System.out.println("Usage: update <task_id> <new_description>");
                        System.out.println("To get the full list of commands, input --help.");
                    }
                    break;
                } else {
                    tracker.updateTask(Integer.parseInt(args[1]), args[2]);
                }
            }
            case "delete" -> {
                if(args.length != 2) {
                    if(args.length > 2) {
                        System.out.println("Incorrect usage. Too many arguments detected.");
                        System.out.println("Usage: delete <task_id>");
                        System.out.println("To get the full list of commands, input --help.");
                    } else {
                        System.out.println("Incorrect usage. Please provide a task ID to delete.");
                        System.out.println("Usage: delete <task_id>");
                        System.out.println("To get the full list of commands, input --help.");
                    }
                    break;
                } else {
                    tracker.deleteTask(Integer.parseInt(args[1]));
                }
            }
            case "mark-in-progress" -> {
                if(args.length != 2) {
                    if(args.length > 2) {
                        System.out.println("Incorrect usage. Too many arguments detected.");
                        System.out.println("Usage: mark-in-progress <task_id>");
                        System.out.println("To get the full list of commands, input --help.");
                    } else {
                        System.out.println("Incorrect usage. Please provide a task ID to mark as in progress.");
                        System.out.println("Usage: mark-in-progress <task_id>");
                        System.out.println("To get the full list of commands, input --help.");
                    }
                    break;
                } else {
                    tracker.updateTaskByStatus(Integer.parseInt(args[1]), TaskStatus.IN_PROGRESS);
                }
            }
            case "mark-done" -> {
                if(args.length != 2) {
                    if(args.length > 2) {
                        System.out.println("Incorrect usage. Too many arguments detected.");
                        System.out.println("Usage: mark-done <task_id>");
                        System.out.println("To get the full list of commands, input --help.");
                    } else {
                        System.out.println("Incorrect usage. Please provide a task ID to mark as done.");
                        System.out.println("Usage: mark-done <task_id>");
                        System.out.println("To get the full list of commands, input --help.");
                    }
                    break;
                } else {
                    tracker.updateTaskByStatus(Integer.parseInt(args[1]), TaskStatus.DONE);
                }
            }
            case "list" -> {
                if(args.length == 1) {
                    tracker.listAllTasks();
                } else if(args.length == 2) {
                    switch(args[1]) {
                        case "todo" -> tracker.listTasksByStatus(TaskStatus.TO_DO);
                        case "in-progress" -> tracker.listTasksByStatus(TaskStatus.IN_PROGRESS);
                        case "done" -> tracker.listTasksByStatus(TaskStatus.DONE);
                        default -> {
                            System.out.println("Invalid argument. Please input either todo, in-progress, or done.");
                            System.out.println("Usage: list <task_status>");
                            System.out.println("To get the full list of commands, input --help.");
                        }
                    }
                } else {
                    System.out.println("Incorrect usage. Too many arguments detected.");
                    System.out.println("Usage: list <todo/in-progress/done>");
                    System.out.println("To get the full list of commands, input --help.");
                }
                break;
            }
            default -> System.out.println("Invalid command. For help, input --help.");
        }
    }    
}
