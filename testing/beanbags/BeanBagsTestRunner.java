package testing.beanbags;

import beanbags.*;
import testing.Unittest;

import java.io.IOException;

public class BeanBagsTestRunner {
    public void run() throws BeanBagMismatchException, BeanBagIDNotRecognisedException, PriceNotSetException, IllegalIDException, ReservationNumberNotRecognisedException, InsufficientStockException, IllegalNumberOfBeanBagsAddedException, IllegalNumberOfBeanBagsReservedException, IOException, BeanBagNotInStockException, IllegalNumberOfBeanBagsSoldException, ClassNotFoundException, InvalidMonthException, InvalidPriceException {
        Unittest testCheckId = new TestCheckId();
        testCheckId.run();
        Unittest testConstructor = new TestConstructor();
        testConstructor.run();
        Unittest testGetId = new TestGetId();
        testGetId.run();
        Unittest testSetId = new TestSetId();
        testSetId.run();
    }
}
