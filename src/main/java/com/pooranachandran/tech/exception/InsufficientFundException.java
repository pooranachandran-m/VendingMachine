package com.pooranachandran.tech.exception;
/**
 * Exception occurs When enough coins are not provided by customer
 *
 * @author Pooranachandran Muthusamy
 * @since 21-Aug-2020
 */
public class InsufficientFundException extends Exception {
    public InsufficientFundException(int overAllCartValueInCents, int userCoinInCents){
        super("Coins doesnt meet the overall cart value. Required cents : "+(overAllCartValueInCents-userCoinInCents)+" . Please try adding coins or adjust products from the cart");
    }
}
