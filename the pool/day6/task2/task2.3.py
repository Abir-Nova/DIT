# Write a recursive program that lists all the files and directories in the current directory, as well as all files
# and directories in its sub-directories and so on.

import os 
# Import the os module which allows us to interact with files & directories

def list_dir(path): # Define a function that takes a directory path as an argument
    for item in os.listdir(path): # Get all items (files & directories) inside the given path & loop through them
        full_path=os.path.join(path,item)
        # Create the complete path by combining the directory path and the item name

        print(item)  ## Display the name of the current file or directory

        if os.path.isdir(full_path): # Check if the current item is a directory
            list_dir(full_path)  # If it is a directory call the function again with that directory 

list_dir(".")  # Start the function from "." which means the current directory.