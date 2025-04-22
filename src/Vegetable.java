/**
 * The {@code Vegetable} type for green foods. This class inherits the {@code FruitVeg} class' methods and fields.
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public class Vegetable extends FruitVeg {

    /**
     * Constructor for this {@code Fruit}
     * @param calorie : The amount of calories that this {@code Vegetable} has.
     * @param description : The description associated with this {@code Vegetable}.
     */
    public Vegetable(int calorie, String description) {
        super(calorie, description);
    }

    /**
     * Returns a string representation of this {@code Vegetable}
     * @return a string representation of this vegetable.
     * 
     * @implNote
     * The following string format is returned:
     * <PRE>
     * "VEG description (c calories)"
     * </PRE>
     * where <i>'description'</i> denotes the vegetable's description & <b> c </b> denotes the vegetable's calories.
     */
    @Override
    public String toString() {
        return String.format("VEG %s", super.toString());
    }
    
}
