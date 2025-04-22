/**
 * This class represents a single node for the {@code FoodList} class. It consists of a reference to a {@code Food} object and a link to
 * the next node, with getters and setters for encapsulation.
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public class FoodElement {

    private Food food; //The food associated with this node.
    private FoodElement next; //The link to the next node.

    /**
     * Default constructor for this given node. It stores the current {@code Food} item with no link to other nodes.
     * @param food : The {@code Food} item associates with this node.
     */
    public FoodElement(Food food) {
        this.food = food;
        next = null;
    }

    /**
     * Retrieves the {@code Food} item associated with this node.
     * @return the {@code Food} stored in this node.
     */
    public Food getFood() {
        return food;
    }

    /**
     * Updates the current node with this given {@code Food}.
     * @param newFood : the food to be updated.
     */
    public void setFood(Food newFood) {
        this.food = newFood;
    }

    /**
     * Sets this node's link to point to the input {@code FoodElement}.
     * @param nextNode : the node to be linked with this node.
     */
    public void setNext(FoodElement nextNode) {
        next = nextNode;
    }

    /**
     * Retrieves the next {@code FoodElement} that is currently linked with {@code this} node.
     * @return the next node that this {@code FoodElement} is referencing to.
     */
    public FoodElement getNext() {
        return next;
    }

}
