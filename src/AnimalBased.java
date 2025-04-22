/**
 * Abstract class for any animal-based food (such as {@code Meat} or {@code Dairy}). This class inherits the {@code SingleFood} class' methods and fields, 
 * with the additional information on the amount of protein, fat & the food's name.
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public abstract class AnimalBased extends SingleFood {
    
    /**
     * The amount of protein that this food contains
     */
    protected double proteinGr;

    /**
     * The amount of fat that this food contains
     */
    protected double fatGr;

    /**
     * The type name of this food
     */
    protected String name;


    /**
     * Constructor for {@code AnimalBased} food. This class is not meant to be instantiated but to be extended by 
     * subclasses later on.
     * 
     * @param cal : The amount of calories that this food has.
     * @param protein : The amount of protein that this food has.
     * @param fat : The amount of fat that this food has.
     * @param name : The type name of this food.
     * @param description : The description associated with this food.
     */
    protected AnimalBased(int cal, double protein, double fat, String name, String description) {
        super(cal, description);
        proteinGr = protein;
        fatGr = fat;
        this.name = name;
    }

    /**
     * Returns the amount of protein that this food contains.
     * 
     * @return amount of protein
     */
    public double getProteinAmount() {
        return proteinGr;
    }

    /**
     * Returns the amount of fat that this food contains.
     * 
     * @return amount of fat
     */
    public double getFatAmount() {
        return fatGr;
    }

    /**
     * Returns the name that associates with this {@code AnimalBased} food.
     * 
     * @return the name of this food.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a string representation of this {@code AnimalBased} food.
     * 
     * @return a string representation of this food.
     * 
     * @implNote
     * The following string format is returned:
     * <PRE>
     * "description (name, c calories, p g protein, f g fat)"
     * </PRE>
     * where <i>'description'</i> & <i>'name'</i> denotes the food's description & name respectively, 
     * <b> c </b> denotes the food's calories, <b> p </b> denotes the food's protein amount and 
     * <b> f </b> denotes the food's fat amount.
     */
    @Override
    public String toString() {
        return String.format("%s (%s, %s, %.1f g protein, %.1f g fat)", description, name, super.toString(), proteinGr, fatGr);
    }

    
}
