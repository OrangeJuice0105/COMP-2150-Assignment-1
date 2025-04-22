/**
 * A list representing a library of foods, backed by a singly linked list implementation. This list has two pointers: one at the front and
 * another at the back of this list. This is to make sure that any operation such as {@link #add(Food)} has constant runtime of O(1) instead
 * of O(n). This list also behaves the same as the {@link java.util.Set} class from the Collections framework as duplicate food items are not 
 * allowed. Instead, any add operations that attempts to add duplicate items only updates the given items and returns false instead.
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public class FoodList {

    private FoodElement front; //Frontal node (for operations that require transversing)
    private FoodElement back; //Back node (for operations that require constant runtime such as add())

    private int size = 0; //The number of food currently in this list.

    private int numGreens = 0; //The number of foods of FruitVeg type
    private int numDairy = 0; //The number of foods of Dairy type
    private int numMeat = 0; //The number of foods of Dairy type
    private int numGrain = 0; //The number of foods of Meat type
    private int numOthers = 0; //The number of foods of Other type

    /**
     * Adds the given {@code Food} to this list if it is not present. If the list already contains the food, its information found in the
     * list is updated and the method returns false. If the food is {@code null}, it also returns false. Otherwise the list's
     * size is increased and the method returns true.
     * 
     * @param newFood : the {@code Food} to be added to this list.
     * 
     * @return {@code true} if the list's size changes as the result of this method call.
     */
    public boolean addIfAbsent(Food newFood) {

        //Represents the former size
        int formerSize = size;

        //If the food is non-null:
        if (newFood != null) {

            if (front == null) {
                
                //If list is empty, adds to front:
                addFront(newFood);

            } else {

                if (!contains(newFood)) {

                    //Otherwise, adds to back:
                    addToBack(newFood);

                } else {

                    //Looks for the node's location, then updates it with this given food:
                    FoodElement location = where(newFood);
                    location.setFood(newFood);

                }

            }

        }

        //Returns a boolean expression indicating if the list's size changes as the result of this method call:
        return size != formerSize;

    }

    /**
     * Adds the given {@code Food} to this list. If the food is {@code null}, the method does nothing then returns false. 
     * Otherwise the list's size is increased and the method returns true.
     * 
     * @param newFood : the {@code Food} to be added to this list.
     * 
     * @return {@code true} if the list's size changes as the result of this method call.
     */
    public boolean add(Food newFood) {

        //Represents the former size
        int formerSize = size;

        //If the food is non-null:
        if (newFood != null) {

            if (front == null) {
                
                //If list is empty, adds to front:
                addFront(newFood);

            } else {

                //Otherwise, adds to back:
                addToBack(newFood);

            }

        }

        //Returns a boolean expression indicating if the list's size changes as the result of this method call:
        return size != formerSize;

    }

    /**
     * Creates the node from the given food, then appends to the front of this list. The food is later evaluated separately.
     * @param food : The food to be added to the front
     */
    private void addFront(Food food) {

        //Creates a new node and assigns both front and back to it if this list is initialy empty:
        FoodElement newNode = new FoodElement(food);
        front = newNode;
        back = newNode;

        //Evaluates the following food and increases the list's size and appropriate number of type:
        foodEvaluation(food);

    }

    /**
     * Creates the node from the given food, then appends to the back of this list. The food is later evaluated separately.
     * @param food : The food to be added to the front
     */
    private void addToBack(Food food) {

        //Creates a new node and assigns back to it if the food is not found:
        FoodElement newNode = new FoodElement(food);
        back.setNext(newNode);
        back = newNode;

        //Evaluates the following food and increases the list's size and appropriate number of type:
        foodEvaluation(food);

    }

    /**
     * Checks if the given {@code Food} can be found in this list or not.
     * 
     * @param food : the {@code Food} to be searched for.
     * 
     * @return {@code true} if the list contains this input {@code Food} instance.
     */
    public boolean contains(Food food) {
        return where(food) != null;
    }

    /**
     * Retrieves the node containing this {@code food} instance. The method is made private so that the data cannot be randomly
     * accessed by other progams.
     * 
     * @param food : the {@code Food} to be searched for.
     * 
     * @return the node containing this {@code food} instance, or {@code null} if there is not any.
     */
    private FoodElement where(Food food) {

        FoodElement currentFood = front; //Current node pointer
        FoodElement index = null; //Location node that contains the given food

        //While loop to traverse until either current node reaches end of list or the appropriate food is found:
        while (currentFood != null && index == null) {

            //If the current node's food has the same description as the input food, assigns the index node to break off the loop. 
            //Otherwise continues traversing:
            if (currentFood.getFood().getDescription().equals(food.getDescription())) {
                index = currentFood;
            } else {
                currentFood = currentFood.getNext();
            }
            
        }

        //Returns the index node:
        return index;

    }

    /**
     * Retrieves the {@code Food} with the corresponding {@code description}.
     * 
     * @param description : the description that is needed to retrieve the {@code Food} instance.
     * 
     * @return the {@code Food} with this description, or {@code null} if there is no such food found.
     */
    public Food get(String description) {

        FoodElement currentFood = front; //Current node pointer
        FoodElement index = null; //Location node that contains the given food

        //While loop to traverse until either current node reaches end of list or the appropriate food is found:
        while (currentFood != null && index == null) {

            //If the current node's food has the corresponding description, assigns the index node to break off the loop. 
            //Otherwise continues traversing:
            if (currentFood.getFood().getDescription().equals(description)) {
                index = currentFood;
            } else {
                currentFood = currentFood.getNext();
            }
            
        }

        //If the index node is null, returns null, otherwise retrieves its Food object then returns it:
        return index == null ? null : index.getFood();

    }

    /**
     * Returns the total number of calories that the foods stored in this list hold.
     * @return the total amount of calories stored in this list.
     */
    public int totalCalories() {

        FoodElement currentFood = front; //Current node pointer
        int totalCal = 0; //Total calories calculated

        //While loop to traverse until the current node reaches end of list:
        while (currentFood != null) {

            //Adds the calories calculated from the node's food then traverses to the next node:
            totalCal += currentFood.getFood().getTotalCalories();
            currentFood = currentFood.getNext();

        }

        //Returns this value:
        return totalCal;

    }

    /**
     * Returns the total amount of protein that the foods stored in this list hold.
     * @return the total amount of protein stored in this list.
     */
    private double totalProtein() {

        FoodElement currentFood = front; //Current node pointer
        double totalProtein = 0; //Total amount of protein calculated

        //While loop to traverse until the current node reaches end of list:
        while (currentFood != null) {

            /*
             * Type safeguard: the following types are considered to contain protein:
             *  - For SingleFood instances: only Grain, AnimalBased and Other classes are allowed.
             *  - For CompositeFood: can be calculated by invoking its FoodList then retrieves the amount using this method.
             * If the types are satisfied, the amount of protein is then added to the total.
             */
            if (currentFood.getFood() instanceof Grain grain) {
                totalProtein += grain.getProteinAmount();
            }
            if (currentFood.getFood() instanceof AnimalBased animalBased) {
                totalProtein += animalBased.getProteinAmount();
            }
            if (currentFood.getFood() instanceof Other other) {
                totalProtein += other.getProteinAmount();
            }
            if (currentFood.getFood() instanceof CompositeFood composite) {
                totalProtein += composite.getFoodComponents().totalProtein();
            }

            //Traveses to next node:
            currentFood = currentFood.getNext();
        }

        //Returns this value:
        return totalProtein;
    }

    /**
     * Returns the total amount of fat that the foods stored in this list hold.
     * @return the total amount of fat stored in this list.
     */
    private double totalFat() {

        FoodElement currentFood = front; //Current node pointer
        double totalFat = 0; //Total amount of fat calculated

        //While loop to traverse until the current node reaches end of list:
        while (currentFood != null) {

            /*
             * Type safeguard: the following types are considered to contain fat:
             *  - For SingleFood instances: only Grain, AnimalBased and Other classes are allowed.
             *  - For CompositeFood: can be calculated by invoking its FoodList then retrieves the amount using this method.
             * If the types are satisfied, the amount of fat is then added to the total.
             */
            if (currentFood.getFood() instanceof Grain grain) {
                totalFat += grain.getFatAmount();
            }
            if (currentFood.getFood() instanceof AnimalBased animalBased) {
                totalFat += animalBased.getFatAmount();
            }
            if (currentFood.getFood() instanceof Other other) {
                totalFat += other.getFatAmount();
            }
            if (currentFood.getFood() instanceof CompositeFood composite) {
                totalFat += composite.getFoodComponents().totalFat();
            }

            //Traveses to next node:
            currentFood = currentFood.getNext();
        }

        //Returns this value
        return totalFat;
    }

    /**
     * Returns the number of foods in this list. Not for marks.
     * @return the number of foods in this list.
     */
    @NotForMarks
    public int size() {
        return size;
    }

    /**
     * Returns the total amount of sugar that the foods stored in this list hold.
     * @return the total amount of sugar stored in this list.
     */
    private double totalSugar() {

        FoodElement currentFood = front; //Current node pointer
        double totalSugar = 0; //Total amount of sugar calculated

        //While loop to traverse until the current node reaches end of list:
        while (currentFood != null) {

            /*
             * Type safeguard: the following types are considered to contain fat:
             *  - For SingleFood instances: only Other class is allowed.
             *  - For CompositeFood: can be calculated by invoking its FoodList then retrieves the amount using this method.
             * If the types are satisfied, the amount of sugar is then added to the total.
             */
            if (currentFood.getFood() instanceof Other other) {
                totalSugar += other.getSugarAmount();
            }
            if (currentFood.getFood() instanceof CompositeFood composite) {
                totalSugar += composite.getFoodComponents().totalSugar();
            }

            //Traveses to next node:
            currentFood = currentFood.getNext();
        }

        //Returns this value
        return totalSugar;

    }

    /**
     * Returns a string displaying the total amount of nutrients that every food carries in this list.
     * @return a string contains the information of all nutrients in this list.
     */
    public String displayTotalAmount() {
        return String.format("%d calories\n%.1f g protein\n%.1f g fat\n%.1f g sugar", 
                    totalCalories(), totalProtein(), totalFat(), totalSugar()).indent(3);
    }

    /**
     * Returns a string displaying the information of all foods in this list. This method iterates the entire list and invokes the 
     * {@link Food#toString()} method in each run and append to the {@code StringBuilder}. 
     * The string built from this builder is then returned.
     * 
     * @return the string representation of this list, displaying all food items.
     */
    @Override
    public String toString() {

        //StringBuilder object that we use to build string (reduces memory usage as StringBuilder is mutable):
        StringBuilder stringBuilder = new StringBuilder(); 

        FoodElement currentFood = front; //Current node pointer

        //While loop to traverse until the current node reaches end of list:
        while (currentFood != null) {

            //Retrieves the current food within this node then appends it to this builder:
            Food current = currentFood.getFood();
            stringBuilder.append(current);

            //Appends a new line character if this is a SingleFood instance:
            if (current instanceof SingleFood) {
                stringBuilder.append("\n");
            }

            //Traveses to next node:
            currentFood = currentFood.getNext();
        }

        //Removes the last new line character in this builder, then invokes toString() to return the String:
        return stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("\n")).toString();

    }

    /**
     * Checks the type of the {@code inputFood}, then in turn updates the appropriate food counters.
     * @param inputFood : the food to be evaluated.
     */
    private void foodEvaluation(Food inputFood) {
        if (inputFood instanceof SingleFood single) {
            if (single instanceof FruitVeg) {
                numGreens++;
            } else if (single instanceof Dairy) {
                numDairy++;
            } else if (single instanceof Meat) {
                numMeat++;
            } else if (single instanceof Grain) {
                numGrain++;
            } else if (single instanceof Other) {
                numOthers++;
            }
        } else if (inputFood instanceof CompositeFood composite) {
            FoodList components = composite.getFoodComponents();
            numGreens += components.numGreens;
            numDairy += components.numDairy;
            numMeat += components.numMeat;
            numGrain += components.numGrain;
            numOthers += components.numOthers;
        }
        size++;
    }

    /**
     * Returns a string displaying the number of food types present in this list. The food types represented are: Fruit & Vegetable,
     * Dairy, Meat, Grain and Other foods.
     * @return a string displaying the number of food types present in this list.
     */
    public String getAllFoodTypes() {
        return String.format("Fruit & Vegetable: %d\nDairy: %d\nMeat: %d\nGrain: %d\nOther: %d", 
                numGreens, numDairy, numMeat, numGrain, numOthers).indent(Indent.INDENT_LIMIT);
    }

}
