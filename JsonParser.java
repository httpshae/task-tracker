import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    public static String toJson(Task task) {
        return "\t{"
                + "\n\t\t\"id\": " + task.getId() + ","
                + "\n\t\t\"description\": \"" + task.getDescription() + "\","
                + "\n\t\t\"status\": \"" + task.getStatus().toString() + "\","
                + "\n\t\t\"createdAt\": \"" + task.getCreatedAt() + "\","
                + "\n\t\t\"updatedAt\": \"" + task.getUpdatedAt() + "\""
                + "\n\t}";
    }

    public static String toJsonArray(List<Task> tasks) {
        StringBuilder json = new StringBuilder();
        json.append("[\n");

        // Loop through list of tasks
        // Convert each task to a JSON object and append it to the String
        for(int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            json.append(JsonParser.toJson(task));

            // Add a comma if it's not the last element
            if (i < tasks.size() - 1) {
                json.append(",");
            }

            json.append("\n");
        }
        json.append("]");

        return json.toString();
    }

    public static List<Task> parseJsonToTasks() throws IOException {

        // Read JSON data from file and store it in a StringBuilder
        StringBuilder jsonData = new StringBuilder();
        try(BufferedReader reader = Files.newBufferedReader(Tracker.trackerFile)) {
            String line;
            while((line = reader.readLine()) != null) {
                jsonData.append(line);
            }
        } catch(IOException e) {
            System.out.println("There was an error reading the tracker file");
            e.printStackTrace();
        }

        // Convert StringBuilder to String
        String jsonString = jsonData.toString();
        
        // Debugging: System.out.println(jsonString); 

        // Parse JSON into Task objects
        List<Task> tasks = new ArrayList<>();

        // Remove outer array brackets
        jsonString = jsonString.trim().substring(1, jsonString.length() - 1);

        // Only proceed if the JSON string is not empty
        if(jsonString.length() > 0) {
            // Split into individual task JSON objects
            String[] taskJsonStrings = jsonString.split("},\\s*\\{");

                for (String taskJsonString : taskJsonStrings) {
                    // Add braces back to each task JSON string
                    taskJsonString = "{" + taskJsonString.trim() + "}";

                    // Remove braces and split into key-value pairs
                    taskJsonString = taskJsonString.trim().replaceAll("[{}]", "");
                    String[] keyValuePairs = taskJsonString.split(",");

                    int id = 0;
                    String description = null;
                    TaskStatus status = null;
                    String createdAt = null;
                    String updatedAt = null;

                    for (String pair : keyValuePairs) {
                        String[] keyValue = pair.split(":");
                        String key = keyValue[0].trim().replaceAll("\"", "");
                        String value = keyValue[1].trim().replaceAll("\"", "");

                        switch (key) {
                            case "id" -> id = Integer.parseInt(value);
                            case "description" -> description = value;
                            case "status" -> status = TaskStatus.fromString(value);
                            case "createdAt" -> createdAt = value;
                            case "updatedAt" -> updatedAt = value;
                        }
                    }

                    // Create a Task object and add it to the list
                    Task task = new Task(id, description, status, createdAt, updatedAt);
                    tasks.add(task);
                }
        }
        return tasks;
    }
}
