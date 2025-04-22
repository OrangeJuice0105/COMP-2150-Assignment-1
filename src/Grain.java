/**
 * The {@code Grain} type for foods. This class inherits the {@code SingleFood} class' methods and fields, with the additional information on
 * the amount of protein, fat and plant name that made up this grain.
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public class Grain extends SingleFood {

    private double proteinGr; //The amount of protein that this food contains
    private double fatGr; //The amount of fat that this food contains
    private String plantName; //The plant name of this food

    /**
     * Constructs an instance of a {@code Grain} type object.
     * @param cal : The amount of calories that this grain has.
     * @param protein : The amount of protein that this grain has.
     * @param fat : The amount of fat that this grain has.
     * @param name : The plant name that makes up this grain.
     * @param description : The description associated with this grain.
     */
    public Grain(int cal, double protein, double fat, String name, String description) {
        super(cal, description);
        proteinGr = protein;
        fatGr = fat;
        plantName = name;
    }

    /**
     * Returns the amount of protein that this grain contains.
     * 
     * @return amount of protein
     */
    public double getProteinAmount() {
        return proteinGr;
    }

    /**
     * Returns the amount of fat that this grain contains.
     * 
     * @return amount of fat
     */
    public double getFatAmount() {
        return fatGr;
    }

    /**
     * Returns the plant name associating with this grain.
     * 
     * @return the plant name
     */
    public String getPlantName() {
        return plantName;
    }

    /**
     * Returns a String representaion of this grain.
     * @return the string form of this meat.
     * @implNote
     * The following string format is returned:
     * <PRE>
     * "GRAIN description (plantName, c calories, p g protein, f g fat)"
     * </PRE>
     * where <i>'description'</i> & <i>'plantName'</i> denotes the grain's description & plant name respectively, 
     * <b> c </b> denotes the grain's calories, <b> p </b> denotes the grain's protein amount and 
     * <b> f </b> denotes the grain's fat amount.
     */
    @Override
    public String toString() {
        return String.format("GRAIN %s (%s, %s, %.1f g protein, %.1f g fat)", description, plantName, super.toString(), proteinGr, fatGr);
    }
}
