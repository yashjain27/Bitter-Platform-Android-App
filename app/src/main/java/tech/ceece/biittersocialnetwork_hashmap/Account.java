package tech.ceece.biittersocialnetwork_hashmap;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class will contain the information about a User that is protected by the User’s password.
 *
 * @author Yash Jain
 *         SBU ID: 109885836
 *         email: yash.jain@stonybrook.edu
 *         HW 6 CSE 214
 *         Section 10 Daniel Scanteianu
 *         Grading TA: Anand Aiyer
 */
public class Account implements Serializable{
    //Data fields
    private ArrayList<User> followers;
    private ArrayList<User> following;
    private String name;
    private Password password;

    //Constructor

    /**
     * Constructor for the Account class.
     * @param name
     *      User's real name
     * @param password
     *      User's password
     */
    public Account(String name, Password password){
        this.name = name;
        this.password = password;
        followers = new ArrayList<>();
        following = new ArrayList<>();
    }

    //Accessors

    /**
     * Returns the followers of the following Account
     * @return
     *      ArrayList<User>
     */
    public ArrayList<User> getFollowers(){
        return followers;
    }

    /**
     * Returns the list of the users the Account is following.
     * @return
     *      ArrayList<User>
     */
    public ArrayList<User> getFollowing(){
        return following;
    }

    /**
     * Return the Account's name
     * @return
     *      String
     */
    public String getName(){
        return name;
    }

    /**
     * Returns the password of the account
     * @return
     *      Password
     */
    public Password getPassword(){
        return password;
    }

    //Mutator

    /**
     * Sets the Account's new name
     * @param name\
     *      String - new name of the Account
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Sets the password of the Account
     * @param password
     *      Password - password of the Account
     */
    public void setPassword(Password password){
        this.password = password;
    }


    //Methods

    /**
     * Adds a user the Account's following list the Account chose to follow
     * @param user
     *      a User the Account wants to follow
     */
    public void follow(User user){
        following.add(user);
    }

    /**
     * The Account will unfollow a User
     * @param user
     *      the User the Account wants to unfollow
     */
    public void unfollow(User user){
        following.remove(user);
    }

    /**
     * Adds a User to the Account's followers list who decides to follow this Account.
     * @param user
     *       a User the Account the User decided to follow
     */
    public void receiveFollower(User user){
        followers.add(user);
    }

    /**
     * The Account loses a follower
     * @param user
     *      a User who decides to unfollow this Account
     */
    public void loseFollower(User user){
        followers.remove(user);
    }

    /**
     * Returns a string representation of the Account's following list
     * @return
     *      a String representation of who all the Account is following
     */
    public String printFollowing(){
        String print = "You follow:\n";
        print += String.format("%-30s%s\n", "Email", "Name");

        for(User user : following)
            print += String.format("%-30s%s\n", user.getEmail(), user.getName());


        return print;
    }

    /**
     * Returns a string representation of the Account's followers list
     * @return
     *      a String representation of who all are following this Account
     */
    public String printFollowers(){
        String print = "Your followers:\n";
        print += String.format("%-30s%s\n", "Email", "Name");

        for(User user : followers)
            print += String.format("%-30s%s\n", user.getEmail(), user.getName());

        return print += "\n";
    }

}
