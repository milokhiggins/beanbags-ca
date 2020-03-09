package testing.beanbagreservation;

import testing.Unittest;

public class BeanBagReservationTestRunner {
    public void run() {
        Unittest testConstructor = new TestConstructor();
        testConstructor.run();
        Unittest testGetId = new TestGetId();
        testGetId.run();
        Unittest testGetLowestPrice = new TestGetLowestPrice();
        testGetLowestPrice.run();
        Unittest testGetQuantity = new TestGetQuantity();
        testGetQuantity.run();
        Unittest testGetReservationNumber = new TestGetReservationNumber();
        testGetReservationNumber.run();
        Unittest testSetId = new TestSetId();
        testSetId.run();
        Unittest testSetLowestPrice = new TestSetLowestPrice();
        testSetLowestPrice.run();
    }
}
