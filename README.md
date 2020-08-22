# Vending Machine

Vending Machine Implementation in Java using Coins and Product as enums. 

## Problem Statement
Write code to implement a Vending machine that has a bunch of products like chocolates, candy,
cold-drink and accept some coins like Nickle, Dime, Quarter, Cent, etc. Make sure you insert a coin, get a product
back and get your change back. Also, write the Unit test to demonstrate that these common use cases work.

### Running the Application
Its a maven project . Just import the project as a Maven project in respective IDE

Provided com.pooranachandran.tech.DeloitteOffice to launch and test the application directly
```
/**
 * Class to demonstrate the Vending Machine operation in Deloitte Office
 */
public class DeloitteOffice {
    public static void main(String args[]){
        try {
```

Various Products and Coins are provided as enum

```
Coins
    NICKLE(5),
    DIME(10),
    QUARTER(25),
    CENT(1);

Products
    ECLAIRS(1),
    COKE(5),
    MOUNTAINDEW(5),
    DIARYMILK(2),
    NUTS(18);
```

#### 1.Get valid vending machine instance from factory 
```
 //Factory to manufacture vending machines
 VendingMachine deloitteMachine=VendingMachineFactory.manufactureVendingMachine(VendingMachineType.DELOITTE);
```

#### 2.Insert the Coins
```
// Insert 15 Cents
deloitteMachine.insertCoin(DIME);//10
deloitteMachine.insertCoin(NICKLE);//15
```

#### 3.Add Products to the cart
```
// Add Product worth 7 Cents
deloitteMachine.addProductToCart(Product.COKE);//5
deloitteMachine.addProductToCart(Product.DIARYMILK);//2
```

#### 4.Check Out the cart and receive CheckoutBag containing Product and Change Coins
```
//Received the Bag with Product and Coins
CheckOutBag checkOutBag=deloitteMachine.checkOut();
```

#### 5.Print the receipt by passing checkout Bag instance to know detailed summary
```
// Bill 7 Cents and Balance 8 Cents
deloitteMachine.printReceipt(checkOutBag);
```

## Exceptions
Custom tailored exceptions will be thrown runtime so that Vedning machine can handle the circumstance accordingly

```
//When vending machine is not having enough change available in denomination
ChangeNotAvailableException

//When negative product quantity is specified while adding it to inventory
InvalidProductQuantityException

//When Trying to create an Non Exisiting vending machine through factory
VendingMachineDesignNotAvailableException

//When trying to add a product which is not available in the inventory
OutOfStockException

//When User doesnt have enough funds when compared to cart value
InsufficientFundException

//When User tries to perform invalid operations. For example  checkOut an Empty Cart | remove a product which doesnt exixt in cart 
InvalidUserOperationException

```

## Running the tests
Test cases for all the classes are available under respective package name ends with *Test

## Authors
* **Pooranachandran Muthusamy** - *Initial work* - [Pooranachandran Muthusamy](https://github.com/writemechandru)
* **Pooranachandran Muthusamy** - *Initial work* - [www.pooranachandran.com](http://www.pooranachandran.com)
