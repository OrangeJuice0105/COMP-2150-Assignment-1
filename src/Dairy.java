/**
 * The {@code Dairy} type for foods. This class inherits the {@code AnimalBased} class' methods and fields.
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public class Dairy extends AnimalBased {

    /**
     * Constructor for {@code Dairy} type of {@code Food}. It essentially inherits the {@code AnimalBased} class.
     * @param cal : The amount of calories that this dairy has.
     * @param protein : The amount of protein that this dairy has.
     * @param fat : The amount of fat that this dairy has.
     * @param name : The type name of this dairy.
     * @param description : The description associated with this dairy.
     */
    public Dairy(int cal, double protein, double fat, String name, String description) {
        super(cal, fat, fat, description, description);
    }

    /**
     * Returns a String representaion of this dairy.
     * @return the string form of this dairy.
     * @implNote
     * The following string format is returned:
     * <PRE>
     * "DAIRY description (name, c calories, p g protein, f g fat)"
     * </PRE>
     * where <i>'description'</i> & <i>'name'</i> denotes the food's description & name respectively, 
     * <b> c </b> denotes the food's calories, <b> p </b> denotes the food's protein amount and 
     * <b> f </b> denotes the food's fat amount.
     */
    @Override
    public String toString() {
        return String.format("DAIRY %s", super.toString());
    }
}
