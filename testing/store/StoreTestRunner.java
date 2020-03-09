package testing.store;

import beanbags.*;
import testing.Unittest;

import java.io.IOException;

public class StoreTestRunner {
    public void run() throws BeanBagMismatchException, BeanBagIDNotRecognisedException, PriceNotSetException, IllegalIDException, ReservationNumberNotRecognisedException, InsufficientStockException, IllegalNumberOfBeanBagsAddedException, IllegalNumberOfBeanBagsReservedException, IOException, BeanBagNotInStockException, IllegalNumberOfBeanBagsSoldException, ClassNotFoundException, InvalidMonthException, InvalidPriceException {
        Unittest testAddBeanBags = new TestAddBeanBags();
        testAddBeanBags.run();
        Unittest testBeanBagsInStock = new TestBeanBagsInStock();
        testBeanBagsInStock.run();
        Unittest testEmpty = new TestEmpty();
        testEmpty.run();
        Unittest testGetBeanBagDetails = new TestGetBeanBagDetails();
        testGetBeanBagDetails.run();
        Unittest testGetNumberOfDifferentBeanBagsInStock = new TestGetNumberOfDifferentBeanBagsInStock();
        testGetNumberOfDifferentBeanBagsInStock.run();
        Unittest testGetNumberOfReservedBeanBags = new TestGetNumberOfReservedBeanBags();
        testGetNumberOfReservedBeanBags.run();
        Unittest testGetNumberOfSoldBeanBags = new TestGetNumberOfSoldBeanBags();
        testGetNumberOfSoldBeanBags.run();
        Unittest testGetTotalPriceOfSoldBeanBags = new TestGetTotalPriceOfSoldBeanBags();
        testGetTotalPriceOfSoldBeanBags.run();
        Unittest testGetTotalPriceOfReservedBeanBags = new TestGetTotalPriceOfReservedBeanBags();
        testGetTotalPriceOfReservedBeanBags.run();
        Unittest testLoadStoreContents = new TestLoadStoreContents();
        testLoadStoreContents.run();
        Unittest testReplace = new TestReplace();
        testReplace.run();
        Unittest testReserveBeanBags = new TestReserveBeanBags();
        testReserveBeanBags.run();
        Unittest testReservedBeanBagsInStock = new TestReservedBeanBagsInStock();
        testReservedBeanBagsInStock.run();
        Unittest testResetSaleAndCostTracking = new TestResetSaleAndCostTracking();
        testResetSaleAndCostTracking.run();
        Unittest testSaveStoreContents = new TestSaveStoreContents();
        testSaveStoreContents.run();
        Unittest testSellBeanBags = new TestSellBeanBags();
        testSellBeanBags.run();
        Unittest testSetBeanBagPrice = new TestSetBeanBagPrice();
        testSetBeanBagPrice.run();
        Unittest testUnreserveBeanBags = new TestUnreserveBeanBags();
        testUnreserveBeanBags.run();

    }
}
