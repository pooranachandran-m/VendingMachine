package com.pooranachandran.tech.exception;

import com.pooranachandran.tech.enums.Product;
/**
 * Exception occurs When User trues remove an Non Existing Product From Cart
 *
 * @author Pooranachandran Muthusamy
 * @since 21-Aug-2020
 */
public class InvalidUserOperationException extends Exception {
    public static final String MSG_EMPTY_CART_CHECKOUT="User cart is empty. Please add some product into carts before checkout";
    public static final String MSG_REMOVE_NON_EXISTING_PRODUCT="User cart is empty. Please add some product into carts before checkout";
    public InvalidUserOperationException(String message){
        super(message);
    }
}
