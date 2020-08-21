package com.pooranachandran.tech.exception;
/**
 * Exception occurs When negative quantity is provided while add product
 *
 * @author Pooranachandran Muthusamy
 * @since 21-Aug-2020
 */
public class InvalidProductQuantityException extends Exception {
    public InvalidProductQuantityException(int quantity){
        super(quantity + " is not a valid quantity");
    }
}
