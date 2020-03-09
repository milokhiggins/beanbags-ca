package testing.beanbagsstock;

import beanbags.*;
import testing.Unittest;

import java.io.IOException;

public class BeanBagsStockTestRunner {
    public void run() throws BeanBagMismatchException, BeanBagIDNotRecognisedException, PriceNotSetException, IllegalIDException, ReservationNumberNotRecognisedException, InsufficientStockException, IllegalNumberOfBeanBagsAddedException, IllegalNumberOfBeanBagsReservedException, IOException, BeanBagNotInStockException, IllegalNumberOfBeanBagsSoldException, ClassNotFoundException, InvalidMonthException, InvalidPriceException {
        Unittest testAddDate = new TestAddDate();
        testAddDate.run();
        Unittest testConstructor = new TestConstructor();
        testConstructor.run();
        Unittest testDecreaseQuantity = new TestDecreaseQuantity();
        testDecreaseQuantity.run();
        Unittest testDetailsMatch = new TestDetailsMatch();
        testDetailsMatch.run();
        Unittest testGetDetails = new TestGetDetails();
        testGetDetails.run();
        Unittest testGetPrice = new TestGetPrice();
        testGetPrice.run();
        Unittest testGetQuantity = new TestGetQuantity();
        testGetQuantity.run();
        Unittest testGetQuantityReserved = new TestGetQuantityReserved();
        testGetQuantityReserved.run();
        Unittest testGetQuantityUnreserved = new TestGetQuantityUnreserved();
        testGetQuantityUnreserved.run();
        Unittest testIncreaseQuantity = new TestIncreaseQuantity();
        testIncreaseQuantity.run();
        Unittest testReserve = new TestReserve();
        testReserve.run();
        Unittest testSetPrice = new TestSetPrice();
        testSetPrice.run();
        Unittest testUnreserve = new TestUnreserve();
        testUnreserve.run();
    }
}
