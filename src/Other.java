/**
 * 
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public class Other extends SingleFood {
    
    private double proteinGr;
    private double fatGr;
    private double sugarAmount;

    /**
     * Creates an instance of {@code Other} food.
     * @param calorie : The amount of calories that this food has.
     * @param protein : The amount of protein that this food has.
     * @param fat : The amount of fat that this food has.
     * @param sugar : The amount of sugar that this food has.
     * @param desc : The description associated with this food.
     */
    public Other(int calorie, double protein, double fat, double sugar, String desc) {
        super(calorie, desc);
        proteinGr = protein;
        fatGr = fat;
        sugarAmount = sugar;
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
     * Returns the amount of sugar that this food contains.
     * 
     * @return amount of sugar
     */
    public double getSugarAmount() {
        return sugarAmount;
    }

    /**
     * Returns a string representation of this {@code Other} food.
     * 
     * @return a string representation of this food.
     * 
     * @implNote
     * The following string format is returned:
     * <PRE>
     * "OTHER description (c calories, p g protein, f g fat, s g sugar)"
     * </PRE>
     * where 'description' denotes the food's description, <b> c </b> denotes the food's calories,
     * <b> p </b> denotes the food's protein amount, <b> f </b> denotes the food's fat amount and 
     * <b> s </b> denotes the food's sugar amount.
     */
    @Override
    public String toString() {
        return String.format("OTHER %s (%s, %.1f g protein, %.1f g fat, %.1f g sugar)", description, super.toString(), proteinGr, fatGr, sugarAmount);
    }

    
}
