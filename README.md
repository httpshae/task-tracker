# Java Task Tracker CLI App

## Author: Shae Ferguson

## Description
This is a command-line application developed in Java to track and manage tasks.
This program is a solution to: https://roadmap.sh/projects/task-tracker

### Command Usage
- --help                              list all commands
- add `description`                   add a new task with the provided description
- update `task_id` `new_description`  update an existing task with a new description
- delete `task_id`                    delete the task
- mark-in-progress                    update the task status to "in progress"
- mark-done                           update the task status to "done"
- list                                list all tasks
- list `task_status`                  list all tasks with a status of <task_status>
                                    where task_status can be "todo", "in-progress" or "done"

## How to run this program locally
### Method 1 - Without an alias command
1. Clone the git repo: https://github.com/httpshae/task-tracker
2. Compile the program: 
Open a terminal and navigate to the project directory.
Run following command to compile all of the Java files in the project:
> javac *.java
3. After compiling, run the main class using the following command:
>java TaskTracker <command> <args>

### Method 2 - With an alias command
Setting up a command alias allows us to use a unique identifier to run a command. This is especially convenient when executing long or complex commands. 
Example: Instead of running `java TaskTracker <command> <args>` everytime we want to run the program, we can set up an alias command to run the program in a single command. (Note: Arguments will still have to be provided.)

Setting up a command alias is done by updating the shell configuration file.

#### Setting up an alias command (on Mac with zsh and Vi)
1. Open a terminal.
2. Open the shell configuration file by running the following command:
> vi ~/.zshrc
3. Type 'i' to enter into insert mode.
4. Add the following line anywhere in the file:
>alias task-tracker='java TaskTracker'
**Bonus (optional):** Consider setting up an alias to navigate directly to the project directory.
Example:
>alias open-task-tracker="cd <project_directory>"
6. Click the 'esc' key to exit insert mode.
7. Enter the following command to save and exit the file:
>:wq!
8. Apply the changes by running the below command:
>source ~/.zshrc

You should now be able to run the program using the command alias.
