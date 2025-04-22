/*
 * NAME:            Duc Cam Thai
 * STUDENT NUMBER:  7851908
 * COURSE:          COMP 2150   
 * INSTRUCTOR:      Heather Matheson
 * ASSIGNMENT:      1
 * QUESTION:        1
 * 
 * REMARKS: This program uses a hierarchy of classes to simulate a meal tracker. It contains a 
 * library of foods and a library of profiles for people, where the food consumed by each person is stored 
 * with their profile. Both libraries are implemented using linked list data structure, while the Food objects
 * are formed using class hierarchy and polymorphism. The input command file is read from the command line arguments.
 * Most strings are built by using String.format() method or by calling a StringBuilder instance for string concatenations.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Main driver class for meal tracker operations. It reads a series of lines from a given input file (whose name comes from the command line
 * arguments). It ultilises both global {@code FoodList} and {@code UserList} to keep track of every items and profiles created for tracking.
 * Most exceptions (such as {@link IOException} & {@link IllegalArgumentException}), whether from I/O errors or invalid arguments, are handled 
 * internally so that the program can terminate normally.
 * 
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public class MealTracker {

    private static final int MARK_LIMIT = 50;

    /**
     * Main method to execute our program. Here we use the {@code args} array to input our file name along with the program execution
     * command. Each line is then read to perform certain actions, such as parsing commands or processing comments, etc.
     * @param args : The input arguments to be run along with this program.
     * @throws ArrayIndexOutOfBoundsException if the program is run with no command line arguments.
     */
    public static void main(final String[] args) {

        File inputFile = new File(args[0]); //File object from the given argument name
        FoodList foods = new FoodList(); //Global food list to store informations on foods created
        UserList users = new UserList(); //Global profile list to store informations on profiles created

        try {
            
            //Attempts to read the data file and does actions from each line:
            readDataFile(inputFile, foods, users);

        } catch (IOException ioe) {

            //If IOException is thrown, prints the error message in standard error stream.
            System.err.append("An I/O error has occurred with the following message\n").println(ioe);

        } finally {

            //Prints the terminate message (should be printed if the program does not exit abruptly due to any reasons):
            System.out.println("Program terminated normally");

        }
        
    }

    /**
     * Reads the input file and processes each line. If the line starts with "#", it is known as a comment and simply be printed onto the console
     * without any further actions. Otherwise the action is processed separatedly.
     * </p>
     * The types of actions are specified in the {@link #performAction} method.
     * </p>
     * 
     * @param file : The input file to be read
     * @param foodList : The universal food list to keep track of all {@code Food} items created.
     * @param userList : The universal profile list to keep track of all {@code User} items created.
     * @throws IOException If any I/O operations error occurred.
     */
    public static void readDataFile(File file, FoodList foodList, UserList userList) throws IOException {

        //Try-with-resources block to invoke close() method automatically (to reduce any resource leak problems):
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

            String line; //line read from bufferedReader
            String[] tokens; //tokens splitted from line using whitespace delimitter

            //While loop to read every line until EOF:
            while ((line = bufferedReader.readLine()) != null) {

                if (line.startsWith("#")) {

                    //If the line starts with a hashtag, process it separately and echoes to standard output console:
                    echoComment(line);

                } else {

                    //Parses the command, and handles any IllegalArgumentException that is thrown while executing:
                    try {
                        tokens = line.split(" ");
                        performAction(tokens, bufferedReader, foodList, userList);
                    } catch (IllegalArgumentException ime) {
                        System.err.println(ime.getMessage());
                    }
                }

            }
        }
    }

    /**
     * Parse the action given in array of tokens. The following actions are processed based on the assignment's specification: 
     * <ul>
     *  <li> <i> NEWFOOD SINGLE [TYPE] [CALORIES] [TYPE-DEPENDENT PROPERTIES] [DESCRIPTION] </i>: Adds a new single food item to the food library.
     *  <li> <i> NEWFOOD COMPOSITE [NUM] [DESCRIPTION] </i>: Adds a new composite food item to the food library.
     *  <li> <i> NEWPROFILE [USERNAME] </i>: Adds a new user profile to the system.
     *  <li> <i> EAT [USER] [DESCRIPTION] </i>: Records an item the user has eaten.
     *  <li> <i> PRINTCALORIES [USER] </i>: Prints the total calories that the user has consumed.
     *  <li> <i> PRINTSERVINGS [USER] </i>: Prints the number of servings of each type of food the user has consumed.
     *  <li> <i> PRINTMEALS [USER] </i>: Prints a list of everything the user has consumed.
     *  <li> <i> PRINT [LIST-TYPE] </i>: Prints a list foods or users accumulated (depending on the LIST-TYPE). Not for marks
     *  <li> <i> QUIT </i>: Ends the program if EOF (end of file) is reached.
     * </ul>
     * </p>
     * 
     * Any action that does not match the given specification is guaranteed to throw an {@code IllegalArgumentException}.
     * For any other reasons, this exception is also thrown when the <i>NEWPROFILE</i> command has a username whose length 
     * is out of bounds.
     * 
     * @param tokens : The input tokens to be processed (type of command is at the very front)
     * @param reader : The reader used to create Composite Food (for <i>NEWFOOD COMPOSITE</i> command only) and check for EOF (for <i>QUIT</i> command)
     * @param foodList : The universal food list to keep track of all {@code Food} items created.
     * @param userList : The universal profile list to keep track of all {@code User} items created.
     * @throws IllegalArgumentException If the action or any tokens does not match the specification above.
     * @throws IOException If any I/O operations error occurred.
     */
    public static void performAction(String[] tokens, BufferedReader reader, FoodList foodList, UserList userList) throws IOException {

        int index = 0; //Index to keep track for each token (pre-incremented per run)

        String typeOfCommand = tokens[index]; //Type of command to be processed

        String type = null, //Food type (for Food.foodFactory method)
            greenType = null, //Green type (to determine if the food is fruit or vegetable)
            name = null, //Name (to determine the dairy, meat or grain's name)
            listType = null;

        int calories = 0; //Calories read
        double protein = 0, //Amount of protein
            fat = 0, //Amount of fat
            sugar = 0; //Amount of sugar

        String[] descriptions = null; 
        String username;
        User user;

        //Switch block based on the type of command:
        switch (typeOfCommand) {

            //NEWFOOD command:
            case "NEWFOOD" -> {

                //Determines the food type to do action with:
                String singleOrComposite = tokens[++index];

                //Switch block to perform food creation based on type of food:
                switch (singleOrComposite) {

                    //If the food item is a single food:
                    case "SINGLE" -> {

                        type = tokens[++index]; //Gets the type for this food
                        calories = Integer.parseInt(tokens[++index]); //Gets the calories amount

                        //Switch block to assign the parameters:
                        switch (type) {

                            //If the food is a fruit or vegetable:
                            case "FRUITVEG" -> {

                                greenType = tokens[++index]; //Gets the green type name

                                //Parses the rest of the tokens into its own array for description:
                                descriptions = Arrays.copyOfRange(tokens, ++index, tokens.length); 

                            }

                            //If the food is either dairy, meat or grain:
                            case "DAIRY", "MEAT", "GRAIN" -> {

                                protein = Double.parseDouble(tokens[++index]); //Gets the protein amount
                                fat = Double.parseDouble(tokens[++index]); //Gets the fat amount
                                name = tokens[++index]; //Gets the name

                                //Parses the rest of the tokens into its own array for description:
                                descriptions = Arrays.copyOfRange(tokens, ++index, tokens.length);

                            }

                            //If food type is other:
                            case "OTHER" -> {

                                protein = Double.parseDouble(tokens[++index]); //Gets the protein amount
                                fat = Double.parseDouble(tokens[++index]); //Gets the fat amount
                                sugar = Double.parseDouble(tokens[++index]); //Gets the sugar amount

                                //Parses the rest of the tokens into its own array for description:
                                descriptions = Arrays.copyOfRange(tokens, ++index, tokens.length);

                            }

                            //Throws the exception if the food's type cannot be determined: 
                            default -> throw new IllegalArgumentException("Type cannot be determined");

                        }

                        //Constructs the food using the foodFactory method, then adds the food to this list and prints the corresponding message:
                        Food singleFood = Food.foodFactory(type, calories, protein, fat, sugar, greenType, name, descriptions);
                        System.out.println((foodList.addIfAbsent(singleFood)) ? "NEW FOOD ADDED" : "FOOD UPDATED");

                    }

                    //If the food item is a composite food:
                    case "COMPOSITE" -> {

                        int numComponents = Integer.parseInt(tokens[++index]); //Parses the number of line required to be read

                        //Parses the rest of the tokens into its own array for description:
                        descriptions = Arrays.copyOfRange(tokens, ++index, tokens.length);

                        //Constructs the food using the createCompositeFood method:
                        Food compositeFood = Food.createCompositeFood(reader, numComponents, foodList, descriptions);

                        
                        if (foodList.addIfAbsent(compositeFood)) {

                            //If the food is added successfully, prints the confirmation message:
                            System.out.println("NEW FOOD ADDED");

                        } else if (compositeFood != null) {

                            //If the food is updated with new components, prints the confirmation message:
                            System.out.println("FOOD UPDATED");

                        } else {

                            //If the food cannot be created due to reasons specified in createCompositeFood(), prints the error message:
                            System.err.println("FAILURE TO ADD FOOD");

                        }

                    }

                    //Throws the exception if the food's type cannot be determined:
                    default -> throw new IllegalArgumentException("Food type cannot be determined");

                }

            }

            //NEWPROFILE command:
            case "NEWPROFILE" -> {

                //Retrieves the username and creates a User profile based on this name:
                username = tokens[++index];
                user = new User(username);

                //If the user is new, adds user to the list and prints the confirmation message.
                //Otherwise prints the error message without adding:
                if (userList.add(user)) {
                    System.out.println("NEW USER ADDED");
                } else {
                    System.err.println("DUPLICATE USER NOT ADDED");
                }

            }

            //EAT command:
            case "EAT" -> {

                //Retrieves the username:
                username = tokens[++index];

                //Parses the rest of the tokens into its own array, then joins it into a single string using whitespace delimitter for description:
                descriptions = Arrays.copyOfRange(tokens, ++index, tokens.length);
                String description = String.join(" ", descriptions);

                //Searches for both the corresponding user and food:
                user = userList.get(username);
                Food matched = foodList.get(description);

                //If neither the user nor food is found, prints the corresponding error message. 
                //Otherwise perform eating action and prints the confirmation message
                if (user == null) {
                    System.err.println("USER NOT FOUND");
                } else if (matched == null) {
                    System.err.println("FOOD NOT FOUND");
                } else {
                    user.eat(matched);
                    System.out.println("MEAL RECORDED");
                }

            }

            //PRINTCALORIES command:
            case "PRINTCALORIES" -> {

                //Retrieves the username then retrieves the user that has this name:
                username = tokens[++index];
                user = userList.get(username);

                //If the user is available, prints their total calories consumed. Otherwise prints the error message:
                if (user != null) {
                    System.out.printf("TOTAL CALORIES = %d kcal\n", user.totalCaloriesConsumed());
                } else {
                    System.err.println("USER NOT FOUND");
                }

            }

            //PRINTSERVINGS command:
            case "PRINTSERVINGS" -> {

                //Retrieves the username then retrieves the user that has this name:
                username = tokens[++index];
                user = userList.get(username);

                //If the user is available, prints their serving history. Otherwise prints the error message:
                if (user != null) {
                    System.out.println(user.servingHistory());
                } else {
                    System.err.println("USER NOT FOUND");
                }

            }

            //PRINTMEALS command:
            case "PRINTMEALS" -> {

                //Retrieves the username then retrieves the user that has this name:
                username = tokens[++index];
                user = userList.get(username);

                //If the user is available, prints their meal summary. Otherwise prints the error message:
                if (user != null) {
                    System.out.println(user.mealSummary());
                } else {
                    System.err.println("USER NOT FOUND");
                }
            }

            //PRINT command (not for marks):
            case "PRINT" -> {

                //Retrieves the list type to be printed:
                listType = tokens[1];

                //Prints the appropriate list (not for marks):
                switch (listType) {
                    case "USERS" -> System.out.println(userList);
                    case "FOODS" -> System.out.println(foodList);
                    default -> throw new IllegalArgumentException("Invalid list type");
                }

            }

            //QUIT program command:
            case "QUIT" -> {

                //Should print DONE if reaches end of file (EOF), otherwise prints the error message:
                if (checkEOF(reader)) {
                    System.out.println("DONE");
                } else {
                    System.err.println("Quit failed. Reader has not reached EOF yet.");
                }

            }

            //Throws this exception if command type does not match any of the above:
            default -> throw new IllegalArgumentException("Command not found");

        }
    }

    /**
     * Prints the input {@code line} onto the standard output stream. The line that starts with a hashtag (#) are processed by
     * removing the tag, then removing every leading whitespaces before printing.
     * @param line : the line to be printed onto the console.
     */
    public static void echoComment(String line) {
        System.out.println(line.substring(1).stripLeading());
    }

    /**
     * Checks whether if the {@code BufferedReader} has reached EOF or not.
     * @param bufferedReader : the reader to be checked
     * @return {@code true} if EOF is reached, otherwise resets the reader back to its original position then returns {@code false}.
     * @throws IOException If any I/O operations error occurred.
     */
    private static boolean checkEOF(BufferedReader bufferedReader) throws IOException {

        boolean isEOF = true; //flag to mark EOF
        bufferedReader.mark(MARK_LIMIT); //Marks the last location of this reader to check

        //If the readLine() method does not return null, switches said flag to false and resets the reader back to its position:
        if (bufferedReader.readLine() != null) {
            isEOF = false;
            bufferedReader.reset();
        }

        //Returns this flag:
        return isEOF;

    }

}
