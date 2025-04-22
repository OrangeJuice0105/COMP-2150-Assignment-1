/**
 * Represents a user profile in this meal tracker program. A {@code User} contains two instances: a username that is 5 to 12 characters long
 * with a {@code FoodList} representing theur meal history.
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public class User {

    private String userName; //Username
    private FoodList mealHistory; //Food list to track their meal history


    private static final int SHORTEST_NAME_LENGTH = 5; //Lower bound of a username's length
    private static final int LONGEST_NAME_LENGTH = 12; //Upper bound of a username's length

    /**
     * Creates a profile for the user with given {@code name}. The username must be from 5 to 12 characters long.
     * @param name : The username associated with this profile.
     * @throws IllegalArgumentException if the input username is less than 5 or more than 12 characters long.
     */
    public User(String name) {

        //If the name is not within 5 to 12 characters, throws the exception with this message:
        if (name.length() > LONGEST_NAME_LENGTH || name.length() < SHORTEST_NAME_LENGTH) {
            throw new IllegalArgumentException("Name must be from 5 to 12 characters long");
        }

        //Assigns the value as usual:
        userName = name;
        mealHistory = new FoodList();

    }

    /**
     * Returns the name associated with this user.
     * @return The username
     */
    public String getName() {
        return userName;
    }

    /**
     * Performs eating action for the given {@code food}
     * @param food : the food to be eaten.
     */
    public void eat(Food food) {
        mealHistory.add(food);
    }

    /**
     * Returns the amount of energy consumed from eating foods.
     * @return The total amount of calories consumed
     */
    public int totalCaloriesConsumed() {
        return mealHistory.totalCalories();
    }

    /**
     * Returns the information of this {@code User}'s serving history. The food type count is displayed in the following ordinal pattern:
     * <ul>
     *  <li> <i> 1) Fruit & Vergetable </i>
     *  <li> <i> 2) Dairy </i>
     *  <li> <i> 3) Meat </i>
     *  <li> <i> 4) Grain </i>
     *  <li> <i> 5) Other </i>
     * </ul>
     * </p>
     * @return The user's serving history.
     */
    public String servingHistory() {

        //StringBuilder object that we use to build string (reduces memory usage as StringBuilder is mutable):
        StringBuilder builder = new StringBuilder();

        //Invokes the following operations on the builder, then returns the string from this builder:
        return builder.append(userName) //appends the username
                    .append("\n") //appends the newline character
                    .append(mealHistory.getAllFoodTypes()) //appends the food types count information
                    .deleteCharAt(builder.lastIndexOf("\n")) //removes the last newline character
                    .toString(); //returns the string itself
    }

    /**
     * Returns the information of this {@code User}'s meal history. The foods consumed for this user are displayed first, followed by the total
     * amount of calories, protein, fat and sugar consumed.
     * @return The user's meals history.
     */
    public String mealSummary() {

        //StringBuilder object that we use to build string (reduces memory usage as StringBuilder is mutable):
        StringBuilder stringBuilder = new StringBuilder();

        //Appends the meal history header:
        stringBuilder.append(String.format("Meal history for %s:\n", userName));

        //Appends the entire mealHistory list, followed by a newline character and the total amount of nutrients from this list:
        stringBuilder.append(mealHistory).append('\n').append(mealHistory.displayTotalAmount());

        //Removes the last new line character in this builder, then invokes toString() to return the String:
        return stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("\n")).toString();
    }

    /**
     * Returns the string representation of this user displayed with the number of foods eaten and the total calories after consuming them.
     * <p>
     * This part is not for marks.
     * 
     * @return a String representation for this {@code User}.
     */
    @Override
    @NotForMarks
    public String toString() {
        int numFoods = mealHistory.size();
        return numFoods == 1 ? 
            String.format("User %s has eaten %d food and consumed %d calories", userName, numFoods, mealHistory.totalCalories()) : 
            String.format("User %s has eaten %d foods and consumed %d calories", userName, mealHistory.size(), mealHistory.totalCalories());
    }

}
