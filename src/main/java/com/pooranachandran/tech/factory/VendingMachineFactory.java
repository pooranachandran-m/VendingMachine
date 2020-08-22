package com.pooranachandran.tech.factory;

import com.pooranachandran.tech.enums.VendingMachineType;
import com.pooranachandran.tech.exception.InvalidProductQuantityException;
import com.pooranachandran.tech.exception.VendingMachineDesignNotAvailableException;
import com.pooranachandran.tech.service.VendingMachine;
import com.pooranachandran.tech.service.impl.DeloitteVendingMachine;

/**
 * Factory class pattern to create VendingMachine instances
 */
public class VendingMachineFactory {
    public static VendingMachine manufactureVendingMachine(VendingMachineType vendingMachineType) throws InvalidProductQuantityException, VendingMachineDesignNotAvailableException {
        switch (vendingMachineType){
            case DELOITTE:{
                return new DeloitteVendingMachine();
            }
            default:{
                throw new VendingMachineDesignNotAvailableException(vendingMachineType);
            }
        }
    }
}
