package com.pooranachandran.tech.exception;
/**
 * Exception occurs When proper change denominations are not available
 *
 * @author Pooranachandran Muthusamy
 * @since 21-Aug-2020
 */
public class ChangeNotAvailableException extends Exception {
    public ChangeNotAvailableException(int changeInCents){
        super("Wallet doesnt have valid change denomination."+changeInCents);
    }
}
