package tech.ceece.biittersocialnetwork_hashmap;

import java.io.Serializable;

/**
 * This class will represent the social network
 *
 * @author Yash Jain
 *         SBU ID: 109885836
 *         email: yash.jain@stonybrook.edu
 *         HW 6 CSE 214
 *         Section 10 Daniel Scanteianu
 *         Grading TA: Anand Aiyer
 */
public class Bitter implements Serializable{
    //Data fields
    private UserDataBase users = new UserDataBase();
    private AccountDataBase accounts = new AccountDataBase();

    //Methods

    /**
     * Manually inserts a User into the UserDatabase and Account into the
     * AccountDatabase both with the specified key
     * @param email
     *      String - email key to the Databases
     * @param user
     *      User - user to be added to the Databases
     * @param account
     *      Account - account to be added to the Databases
     */
    public void addUser(String email, User user, Account account){
        //Check for illegal args
        if(users.getUser(email) != null)
            throw new IllegalArgumentException();

        //Add account and user to the databases
        users.addUser(email, user);
        accounts.addAccount(email, account);
    }

    /**
     * Removes the user and account from the Database.
     * @param email
     *      String - email key passed to remove the User and Account from the Database
     */
    public void removeUser(String email){
        //Check for illegal args
        if(users.getUser(email) == null)
            throw new IllegalArgumentException();

        //Remove user and account from the Database.
        users.removeUser(email);
        accounts.removeAccount(email);
    }

    //Accessors

    /**
     * Returns the UserDataBase object
     * @return
     *      UserDataBase
     */
    public UserDataBase getUsers(){
        return  users;
    }

    /**
     * Returns the AccountDataBase object
     * @return
     *      AccountDataBase
     */
    public AccountDataBase getAccounts(){
        return accounts;
    }

    /**
     * String representation of all the Users in the social network.
     * @return
     *      a String representing all the Users in the social network.
     */
    public String toString(){
        String print = "All Users:\n";
        print += String.format("%-30s%s\n", "Email", "Name");

        for(String s : users.keySet()){
            print += String.format("%-30s%s\n", users.get(s).getEmail(), users.get(s).getName());
        }

        return print += "\n";
    }
}
