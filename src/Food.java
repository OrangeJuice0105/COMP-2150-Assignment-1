import java.io.BufferedReader;
import java.io.IOException;

/**
 * The root of the {@code Food} hierarchy. This class is not meant to be instantiated and provides information that its subclasses must 
 * implement later on. The following methods are implemented as it is subclassed:
 * <PRE>
 * public String toString(); //String representation of this Food.
 * public String getDescription(); //Gets the description of this Food.
 * public int getTotalCalories(); //Returns the total amount of calories that this Food holds.
 * </PRE>
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public abstract class Food {

    /**
     * The description associated with this food.
     */
    protected String description;

    /**
     * Protected constructor for subclasses of food to implement. 
     * @implSpec
     * The initial constructor contains only the food's description. Detailed information must be implemented later on.
     * @param description : The description associated with this food.
     */
    protected Food(String description) {
        this.description = description;
    }

    /**
     * Returns a String representation of this food.
     * 
     * @implSpec
     * The default {@code toString} method of a single food only returns a String representing the amount of calories for this food.
     * For composite foods, consider invoking more information of their components' {@code toString} first.
     * @return  a string representation of this food.
     */
    public abstract String toString();

    /**
     * Returns the food's description.
     * @return this food's description.
     */
    public abstract String getDescription();

    /**
     * Returns the total amount of energy (in calories) that this food holds.
     * @return the amount of energy for this food
     */
    public abstract int getTotalCalories();

    /**
     * Factory method to construct {@code SingleFood} objects. Unless otherwise specified, some of the arguments may be 0 for primitive or null 
     * for references depending on the input types.
     * 
     * @param type : The food type to be constructed
     * @param calories : The amount of calories that the food holds
     * @param protein : The amount protein that the food holds if food is either {@code AnimalBased}, {@code Grain} or {@code Other}
     * @param fat : The amount fat that the food holds if food is either {@code AnimalBased}, {@code Grain} or {@code Other}
     * @param sugar : The amount fat that the food holds if food is {@code Other}
     * @param greenType : The greenType to be distinguish as a fruit or vegetable if food is {@code FruitVeg}
     * @param name : The name given to this food if there is any
     * @param descriptions : The food's description (stored as an array of tokens)
     * 
     * @return A new food based on the valid {@code type}, or null if the type is invalid.
     */
    public static Food foodFactory(String type, int calories, double protein, double fat, double sugar, 
        String greenType, String name, String... descriptions) {

        //Creates a single description from the given tokens.
        String detailedDescription = String.join(" ", descriptions);

        //Switch expression to return food with appropriate type:
        return switch (type) {
            case "FRUITVEG" -> yieldFruitOrVegetable(calories, greenType, detailedDescription); //type is "FRUITVEG"
            case "DAIRY" -> new Dairy(calories, protein, fat, name, detailedDescription); //type is "DAIRY"
            case "MEAT" -> new Meat(calories, protein, fat, name, detailedDescription); //type is "MEAT"
            case "GRAIN" -> new Grain(calories, protein, fat, name, detailedDescription); //type is "GRAIN"
            case "OTHER" -> new Other(calories, protein, fat, sugar, detailedDescription); //type is "OTHER"
            default -> null; //null if type is invalid
        };
    }

    /**
     * Factory method to construct {@code CompositeFood} objects. Using the input {@code BufferedReader} to read the required {@code numLines} then
     * search for the equivalent foods in the input {@code FoodList}, then places them a new {@code FoodList} to associate with the newly created
     * {@code CompositeFood}. If any food is not found in the list, the {@code BufferedReader} reads the rest of the remaining lines then returns 
     * {@code null} instead.
     * 
     * @param bufferedReader : The reader to read each line (assuming each line is a description to look for)
     * @param numLines : The number of lines the reader must read before stopping
     * @param foodList : The food list to search for the equivalent foods.
     * @param foodDescriptions : The food's description (stored as an array of tokens)
     * 
     * @return A new food based on the given foods found, or {@code null} if there is one component that cannot be found in this list.
     *
     * @throws IOException If any I/O operations error occurred.
     */
    public static Food createCompositeFood(BufferedReader bufferedReader, int numLines, 
        FoodList foodList, String... foodDescriptions) throws IOException {

        FoodList singleFoodComponents = new FoodList(); //Food list to be associated with this food.
        String name = String.join(" ", foodDescriptions); //Creates a single description from the given tokens.

        boolean trouble = false; //Flag to denote whether a food cannot be found to create such composite food.
        for (int i = 0; i < numLines && !trouble; i++) {

            //Reads one line per iteration then gets the food with this description:
            String description = bufferedReader.readLine();           
            Food food = foodList.get(description);

            if (food != null) {
                
                //Adds if found:
                singleFoodComponents.addIfAbsent(food);

            } else {

                //Uh-oh: food not found. Switches flag to true then reads the rest of the given lines without any other actions.
                trouble = true;
                while (++i < numLines) {
                    bufferedReader.readLine();
                }

            }
        }

        //If a trouble is encountered, returns null, otherwise created a composite food based on the given information and returns it:
        return trouble ? null : new CompositeFood(singleFoodComponents, name);
    }

    /**
     * Returns the appropriate {@code FruitVeg} food based on the given name, calories and description.
     * @param calories : The amount of calories that the food holds
     * @param name : The type name
     * @param detailedDescription : The food's description
     * @return A new {@code Fruit} or {@code Vegetable} based on the given name.
     */
    private static FruitVeg yieldFruitOrVegetable(int calories, String name, String detailedDescription) {
        return switch (name) {
            case "FRUIT" -> new Fruit(calories, detailedDescription);
            case "VEG" -> new Vegetable(calories, detailedDescription);
            default -> null;
        };
    }


}
