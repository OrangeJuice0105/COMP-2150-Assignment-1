## Getting Started

This is my COMP 2150 Assignment 1 submission. This `README` file is written specifically to show how to compile and run the program

## Folder Structure

Inside this zip file (folder once uncompressed) contains 19 Java source code files, this README file and a Makefile. Only the `MealTracker.java` file contains the main method, so this is where you want to run the program.

## How to run 

In order to compile to run the program on Aviary, you can use either of these two options:

1) Compile and run directly:
    - On your Aviary terminal, you can use the following commands to compile and run the Java files:
        javac *.java
        java MealTracker [your_file_here.txt]

2) Compile and run using Makefile:
    - On your Aviary terminal, you can use the following commands to compile and run the Java files:
        make mealTracker
        java MealTracker [your_file_here.txt]

Both requires you to pass an input file via command line argument, otherwise an ArrayIndexOutOfBoundsException will be thrown.
