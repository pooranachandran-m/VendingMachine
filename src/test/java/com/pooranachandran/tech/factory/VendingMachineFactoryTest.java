package com.pooranachandran.tech.factory;

import com.pooranachandran.tech.enums.Product;
import com.pooranachandran.tech.enums.VendingMachineType;
import com.pooranachandran.tech.exception.InvalidProductQuantityException;
import com.pooranachandran.tech.exception.InvalidUserOperationException;
import com.pooranachandran.tech.exception.VendingMachineDesignNotAvailableException;
import com.pooranachandran.tech.service.VendingMachine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for Vending Machine factory
 */
class VendingMachineFactoryTest {
    VendingMachineFactory vendingMachineFactory;

    @BeforeEach
    public void beforeEach() {
        vendingMachineFactory = new VendingMachineFactory();
    }

    @Test
    public void testInstantiationOfVendingMachine() {
        assertNotNull(vendingMachineFactory);
    }

    @Test
    public void testInstantiationOfDeloitteVendingMachine() throws InvalidProductQuantityException, VendingMachineDesignNotAvailableException {
        VendingMachine vm = VendingMachineFactory.manufactureVendingMachine(VendingMachineType.DELOITTE);
        assertNotNull(vm);
    }

    @Test
    public void testInstantiationOfNonExistingVendingMachineThrowsVendingMachineDesignNotAvailableException() {
        assertThrows(VendingMachineDesignNotAvailableException.class, () -> {
            VendingMachineFactory.manufactureVendingMachine(VendingMachineType.BRAND_A);
        });

    }

}