package tech.ceece.biittersocialnetwork_hashmap;

import java.io.Serializable;

/**
 * This class will essentially be the wrapper class for a valid password to a User’s account.
 *
 * @author Yash Jain
 *         SBU ID: 109885836
 *         email: yash.jain@stonybrook.edu
 *         HW 6 CSE 214
 *         Section 10 Daniel Scanteianu
 *         Grading TA: Anand Aiyer
 */
public class Password implements Serializable{
    //Data fields
    private String password;

    //Constructor

    /**
     * <dd><b>Preconditions:</b></dd>
     * <db>At least 1 upper-case, lower-case, number, and a special charater</db>
     * @param initPassword
     * <dd><b>Postconditions:</b></dd>
     * <db>A password object created.</db>
     */
    public Password(String initPassword){
        boolean upperCase = false, number = false, specialChar = false, lowerCase = false;

        for(int i = 0; i < initPassword.length(); i++) {
            if (initPassword.charAt(i) > 64 && initPassword.charAt(i) < 91)
                upperCase = true;
            if (initPassword.charAt(i) > 96 && initPassword.charAt(i) < 123)
                lowerCase = true;
            if (initPassword.charAt(i) > 47 && initPassword.charAt(i) < 58)
                number = true;
            if (initPassword.charAt(i) == 33 || initPassword.charAt(i) == 64 || initPassword.charAt(i) == 35
                    || initPassword.charAt(i) == 36 || initPassword.charAt(i) == 37 || initPassword.charAt(i) == 94
                    || initPassword.charAt(i) == 38 || initPassword.charAt(i) == 42)
                specialChar = true;
        }
        if(!upperCase || !number || !specialChar || !lowerCase)
            throw new IllegalArgumentException();
        else
            password = initPassword;
    }

    //Accessors

    /**
     * Returns the password associated with the Account
     * @return
     *      a String value representing the password.
     */
    public String getPassword(){
        return password;
    }
}

