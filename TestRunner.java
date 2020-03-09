import beanbags.*;
import testing.store.StoreTestRunner;
import testing.beanbags.BeanBagsTestRunner;
import testing.beanbagsstock.BeanBagsStockTestRunner;
import testing.beanbagreservation.BeanBagReservationTestRunner;

import java.io.IOException;

public class TestRunner {
    public static void main(String[] args) throws BeanBagMismatchException, BeanBagIDNotRecognisedException, PriceNotSetException, IllegalIDException, ReservationNumberNotRecognisedException, IllegalNumberOfBeanBagsAddedException, IllegalNumberOfBeanBagsReservedException, InvalidPriceException, InsufficientStockException, IllegalNumberOfBeanBagsSoldException, ClassNotFoundException, InvalidMonthException, IOException, BeanBagNotInStockException {
        StoreTestRunner storeTestRunner = new StoreTestRunner();
        storeTestRunner.run();

        BeanBagsTestRunner beanBagTestRunner = new BeanBagsTestRunner();
        beanBagTestRunner.run();

        BeanBagsStockTestRunner beanBagsStockTestRunner = new BeanBagsStockTestRunner();
        beanBagsStockTestRunner.run();

        BeanBagReservationTestRunner beanBagReservationTestRunner = new BeanBagReservationTestRunner();
        beanBagReservationTestRunner.run();

        System.out.println("All tests complete!");
    }
}
