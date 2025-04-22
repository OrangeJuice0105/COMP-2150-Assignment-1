/**
 * This class represents a {@code Food} instance that is made up of other {@code Food} components. In addition to its description, it
 * contains a {@code FoodList} that this food is made up of.
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public class CompositeFood extends Food {

    /**
     * A food list to keep track of all foods that made up this food
     */
    private FoodList components;

    /**
     * Constructs a {@code CompositeFood} instance based on the given {@code FoodList} and string that describes this food.
     * @param foodList : the list of foods that are used to build this food.
     * @param description : the description associating with this food.
     */
    public CompositeFood(FoodList foodList, String description) {
        super(description);
        components = foodList;
    }

    /**
     * Returns the list containing the {@code Food} components that made up this food.
     * @return the list of foods that made up this food.
     */
    public FoodList getFoodComponents() {
        return components;
    }

    /**
     * Returns a string representation of this {@code CompositeFood}. 
     * 
     * @implSpec
     * Its implementation is described as the following:
     * <PRE>
     * "[description]\n"
     *      "[foodComponents.food]\n"...
     * </PRE>
     * where {@code description} denotes the description associated with this food and {@code foodComponents.food} denotes 
     * each food instance (i.e., {@code toString} invoked from this list method), indented by 3 spaces.
     * 
     * @return the String representation of this composite food
     */
    @Override
    public String toString() {
        return new StringBuilder().append(description).append(":\n")
                .append(components.toString().indent(Indent.INDENT_LIMIT)).toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Returns the total amount of calories that this food holds. It behaves the same as invoking {@link FoodList#totalCalories()} on 
     * the list that this food holds.
     * @return the amount of energy for this food
     */
    @Override
    public int getTotalCalories() {
        return components.totalCalories();
    }


}
