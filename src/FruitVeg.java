/**
 * The abstract class for {@code Fruit} & {@code Vegetable} types in general. This class provides a blueprint that both {@code Fruit} &
 * {@code Vegetable} inherits.
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public abstract class FruitVeg extends SingleFood {

    /**
     * Constructor for {@code FruitVeg}. This class must be inherited by both {@code Fruit} & {@code Vegetable}.
     * @param calorie : The amount of calories that this {@code FruitVeg} has.
     * @param description : The description associated with this {@code FruitVeg}.
     */
    protected FruitVeg(int calorie, String description) {
        super(calorie, description);
    }

    /**
     * Returns a string representation of this green {@code Food}
     * @return a string representation of this food.
     * 
     * @implNote
     * The following string format is returned:
     * <PRE>
     * "description (c calories)"
     * </PRE>
     * where <i>'description'</i> denotes the green food's description & <b> c </b> denotes the green food's calories.
     */
    @Override
    public String toString() {
        return String.format("%s (%s)", description, super.toString());
    }
}
