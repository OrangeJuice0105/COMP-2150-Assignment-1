import java.util.Objects;

/**
 * This class represents a single node for the {@code UserList} class. It consists of a reference to a {@code User} and a link to
 * the next node, with getters and setters for encapsulation.
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public class UserNode {
        
    private User user;
    private UserNode next;

    /**
     * Constructs a node containing the information of the input {@code User}.
     * @param user : The user associated with this node.
     * @throws NullPointerException if the required {@code User} is {@code null}
     */
    public UserNode(User user) {
        this.user = Objects.requireNonNull(user);
        next = null;
    }

    /**
     * Retrieves the {@code User} instance associated with this node.
     * @return the equivalent {@code User}
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets this node's link to point to the input {@code UserNode}.
     * @param nextNode : the node to be linked with this node.
     */
    public void setNext(UserNode nextNode) {
        next = nextNode;
    }

    /**
     * Retrieves the next {@code UserNode} that is currently linked with {@code this} node.
     * @return the next node that this {@code UserNode} is referencing to.
     */
    public UserNode getNext() {
        return next;
    }

}
