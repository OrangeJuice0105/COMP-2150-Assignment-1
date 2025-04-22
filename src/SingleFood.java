/**
 * This abstract class represents a {@code Food} instance that only contains calories and a single description. It provides a 
 * blueprint for later implementations by other subclasses.
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public abstract class SingleFood extends Food {

    /**
     * The amount of energy (measured in calories) for this food.
     */
    protected int calories;

    /**
     * Constructor for {@code SingleFood}. This class is not meant to be instantiated.
     * @param cal : The amount of calories that this food has.
     * @param description : The description associated with this food.
     */
    protected SingleFood(int cal, String description) {
        super(description);
        calories = cal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTotalCalories() {
        return calories;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * {@inheritDoc}
     * @implNote
     * The default {@code toString()} method for this class contains only the amount of calories. 
     * Subclasses of this class must contain more information for this food instance.
     */
    @Override
    public String toString() {
        return String.format("%d calories", calories);
    }

}
