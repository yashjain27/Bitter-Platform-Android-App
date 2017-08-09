package tech.ceece.biittersocialnetwork_hashmap;

import java.io.Serializable;

/**
 * This class will represent public information about a User.
 *
 * @author Yash Jain
 *         SBU ID: 109885836
 *         email: yash.jain@stonybrook.edu
 *         HW 6 CSE 214
 *         Section 10 Daniel Scanteianu
 *         Grading TA: Anand Aiyer
 */
public class User implements Serializable{
    //Data field
    private String name;
    private String email;

    //Constructor
    public User(String name, String email){
        this.name = name;
        this.email = email;
    }

    //Accessor

    /**
     * Returns the user's name
     * @return
     *       String
     */
    public String getName(){
        return name;
    }

    /**
     * Returns the User's email
     * @return
     *      a String representing the User's email
     */
    public String getEmail(){
        return email;
    }
}
