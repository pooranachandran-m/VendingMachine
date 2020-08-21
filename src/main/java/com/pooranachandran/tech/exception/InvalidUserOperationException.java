package com.pooranachandran.tech.exception;

import com.pooranachandran.tech.enums.Product;
/**
 * Exception occurs When User trues remove an Non Existing Product From Cart
 *
 * @author Pooranachandran Muthusamy
 * @since 21-Aug-2020
 */
public class InvalidUserOperationException extends Exception {
    public InvalidUserOperationException(String message){
        super(message);
    }
}
