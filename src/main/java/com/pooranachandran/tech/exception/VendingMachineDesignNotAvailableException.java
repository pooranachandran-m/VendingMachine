package com.pooranachandran.tech.exception;

import com.pooranachandran.tech.enums.VendingMachineType;

/**
 * Exception occurs When Factory is requested to manufacture vending machine designs which are not available
 *
 * @author Pooranachandran Muthusamy
 * @since 21-Aug-2020
 */
public class VendingMachineDesignNotAvailableException extends Exception {
    public VendingMachineDesignNotAvailableException(VendingMachineType vendingMachineType){
        super(vendingMachineType.name() + " is not available in our Factory. Please try some other brands");
    }
}
