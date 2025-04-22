/**
 * A list representing a library of users, backed by a singly linked list implementation. This list has two pointers: one at the front and
 * another at the back of this list. This is to make sure that any operation such as {@link #add(User)} has constant runtime of O(1) instead
 * of O(n). This list also behaves the same as the {@link java.util.Set} class from the Collections framework as duplicate users are not 
 * allowed. Instead, any add operations that attempts to add duplicate items only updates the given items and returns false instead.
 * @author Student name: Duc Cam Thai, Student number: 7851908
 */
public class UserList {

    private UserNode front; //Frontal node (for operations that require transversing)
    private UserNode back; //Back node (for operations that require constant runtime such as add())

    /**
     * Adds the given {@code User} to this list. If the list already contains the profile, it does nothing and 
     * returns false. Otherwise the list's size is increased and the method returns true.
     * 
     * @param user : the {@code User} to be added to this list.
     * 
     * @return {@code true} if the user is added successfully.
     * 
     * @throws NullPointerException if the {@code User} is null.
     */
    public boolean add(User user) {

        boolean added = false; //Flag to indicate that user has been added


        if (front == null) {

            //Creates a new node and assigns both front and back to it if this list is initialy empty:
            UserNode newNode = new UserNode(user);
            front = newNode;
            back = newNode;

            //Changes the flag to true:
            added = true;

        } else {

            if (!contains(user)) {

                //Creates a new node and assigns back to it if the user is not found:
                UserNode newNode = new UserNode(user);
                back.setNext(newNode);
                back = newNode;

                //Changes the flag to true:
                added = true;
            }
        }

        //Returns this boolean flag:
        return added;

    }

    /**
     * Checks if the given {@code User} can be found in this list or not.
     * 
     * @param user : the {@code User} to be searched for.
     * 
     * @return {@code true} if the list contains this input {@code User} instance.
     */
    public boolean contains(User user) {
        return get(user.getName()) != null;
    }

    /**
     * Retrieves the {@code User} with the corresponding {@code username}.
     * 
     * @param username : the username that is needed to retrieve the {@code User} instance.
     * 
     * @return the {@code User} with this username, or {@code null} if there is no such user found.
     */
    public User get(String username) {

        UserNode currentUser = front; //Current node pointer
        UserNode where = null; //Location node that contains the given user
        
        //While loop to traverse until either current node reaches end of list or the requested user is found:
        while (currentUser != null && where == null) {

            //If the current node's user has the corresponding name, assigns the index node to break off the loop. 
            //Otherwise continues traversing:
            if (currentUser.getUser().getName().equals(username)) {
                where = currentUser;
            } else {
                currentUser = currentUser.getNext();
            }
            
        }

        //If the index node is null, returns null, otherwise retrieves its profile then returns it:
        return where == null ? null : where.getUser();

    }

    /**
     * Returns the string representation for this {@code UserList}. More formally, a string that displays the all users 
     * that are currently in this list.
     * <p>
     * This part is not for marks.
     * 
     * @return a String representation for this {@code UserList}.
     */
    @Override
    @NotForMarks
    public String toString() {

        //StringBuilder object that we use to build string (reduces memory usage as StringBuilder is mutable):
        StringBuilder stringBuilder = new StringBuilder();

        UserNode currentUser = front; //Current node pointer

        //While loop to traverse until the current node reaches end of list:
        while (currentUser != null) {

            //Retrieves the current user within this node then appends it to this builder, followed by a newline character:
            User current = currentUser.getUser();
            stringBuilder.append(current).append("\n");

            //Traveses to next node:
            currentUser = currentUser.getNext();

        }
        //Removes the last new line character in this builder, then invokes toString() to return the String:
        return stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("\n")).toString();

    }
    
}
