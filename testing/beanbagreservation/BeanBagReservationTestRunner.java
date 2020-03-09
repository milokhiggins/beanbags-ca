package testing.beanbagreservation;

import beanbags.*;
import testing.Unittest;

import java.io.IOException;

public class BeanBagReservationTestRunner {
    public void run() throws BeanBagMismatchException, BeanBagIDNotRecognisedException, PriceNotSetException, IllegalIDException, ReservationNumberNotRecognisedException, InsufficientStockException, IllegalNumberOfBeanBagsAddedException, IllegalNumberOfBeanBagsReservedException, IOException, BeanBagNotInStockException, IllegalNumberOfBeanBagsSoldException, ClassNotFoundException, InvalidMonthException, InvalidPriceException {
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
