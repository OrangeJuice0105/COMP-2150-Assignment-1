/**
 * The {@code Meat} type for foods. This class inherits the {@code AnimalBased} class' methods and fields.
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public class Meat extends AnimalBased {

    /**
     * Constructor for {@code Meat} type of {@code Food}. It essentially inherits the {@code AnimalBased} class.
     * @param cal : The amount of calories that this meat has.
     * @param protein : The amount of protein that this meat has.
     * @param fat : The amount of fat that this meat has.
     * @param name : The type name of this meat.
     * @param description : The description associated with this meat.
     */
    public Meat(int cal, double protein, double fat, String name, String description) {
        super(cal, fat, fat, name, description);
    }

    /**
     * Returns a String representaion of this meat.
     * @return the string form of this meat.
     * @implNote
     * The following string format is returned:
     * <PRE>
     * "MEAT description (name, c calories, p g protein, f g fat)"
     * </PRE>
     * where <i>'description'</i> & <i>'name'</i> denotes the food's description & name respectively, 
     * <b> c </b> denotes the food's calories, <b> p </b> denotes the food's protein amount and 
     * <b> f </b> denotes the food's fat amount.
     */
    @Override
    public String toString() {
        return String.format("MEAT %s", super.toString());
    }
}
