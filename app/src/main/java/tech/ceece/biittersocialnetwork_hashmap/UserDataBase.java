package tech.ceece.biittersocialnetwork_hashmap;

import java.io.Serializable;
import java.util.HashMap;

/**
 * This class will be the database of Users will be stored in a HashMap.
 *
 * @author Yash Jain
 *         SBU ID: 109885836
 *         email: yash.jain@stonybrook.edu
 *         HW 6 CSE 214
 *         Section 10 Daniel Scanteianu
 *         Grading TA: Anand Aiyer
 */
public class UserDataBase extends HashMap<String, User> implements Serializable{

    //Methods

    /**
     * This method adds a User into the UserDatabase using the specified email as the key.
     * @param email
     *       String - key to the UserDataBase
     * @param user
     *       User - user to be added to the UserDataBase
     */
    public void addUser(String email, User user){
        if(this.get(email) != null)
            throw new IllegalArgumentException("Account already exists with the associated email.");
        this.put(email, user);
    }

    /**
     * Returns the User indicated by the key (email)
     * @param email
     *      String - the email key of the User
     * @return
     *      User - returns the User indicated by the email, or null if nothing found
     */
    public User getUser(String email){
        return this.get(email);
    }

    /**
     * Removes the User from the UserDataBase based on the key.
     * @param email
     *      String - email key passed to remove the User
     */
    public void removeUser(String email){
        if(email == null || this.get(email) == null)
            throw new IllegalArgumentException();
        this.remove(email);
    }
}
