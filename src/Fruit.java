/**
 * The {@code Fruit} type for green foods. This class inherits the {@code FruitVeg} class' methods and fields.
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public class Fruit extends FruitVeg {

    /**
     * Constructor for this {@code Fruit}
     * @param calorie : The amount of calories that this {@code Fruit} has.
     * @param description : The description associated with this {@code Fruit}.
     */
    public Fruit(int calorie, String description) {
        super(calorie, description);
    }

    /**
     * Returns a string representation of this {@code Fruit}
     * @return a string representation of this fruit.
     * 
     * @implNote
     * The following string format is returned:
     * <PRE>
     * "FRUIT description (c calories)"
     * </PRE>
     * where <i>'description'</i> denotes the fruit's description & <b> c </b> denotes the fruit's calories.
     */
    @Override
    public String toString() {
        return String.format("FRUIT %s", super.toString());
    }

}
