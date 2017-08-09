package tech.ceece.biittersocialnetwork_hashmap;
import java.io.Serializable;
import java.util.HashMap;

/**
 * This class will be the database of Accounts will be stored in a HashMap.
 *
 * @author Yash Jain
 *         SBU ID: 109885836
 *         email: yash.jain@stonybrook.edu
 *         HW 6 CSE 214
 *         Section 10 Daniel Scanteianu
 *         Grading TA: Anand Aiyer
 */
public class AccountDataBase extends HashMap<String,Account> implements Serializable {
    //Method

    /**
     * Adds a User to the AccountDataBase
     * @param email
     *       String - email key to the AccountDataBase Hashmap
     * @param account
     *       Account - account to be added to the AccountDataBase
     */
    public void addAccount(String email, Account account){
        if(this.get(email) != null)
            throw new IllegalArgumentException("Account with the associated email already exists!");
        this.put(email, account);
    }

    /**
     * Retrieves the Account from the table having the indicated email.
     * If the requested email does not exist in the AccountDatabase, return null.
     * @param email
     *      String - email key to the AccountDataBase Hashmap
     * @return
     *      (Account)
     */
    public Account getAccountInformation(String email){
        return this.get(email);
    }

    /**
     * Remove an Account from AccountDataBase
     * @param email
     *      String - email key passed to remove the User
     */
    public void removeAccount(String email){
        if(email == null || this.get(email) == null)
            throw new IllegalArgumentException();
        this.remove(email);
    }


}
