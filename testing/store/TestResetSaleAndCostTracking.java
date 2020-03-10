package testing.store;

import testing.Unittest;
import beanbags.*;
public class TestResetSaleAndCostTracking implements Unittest {
    public TestResetSaleAndCostTracking() {
    }
    public void run() throws IllegalIDException, BeanBagMismatchException, InvalidMonthException, IllegalNumberOfBeanBagsAddedException, InvalidPriceException, BeanBagIDNotRecognisedException, PriceNotSetException, BeanBagNotInStockException, InsufficientStockException, IllegalNumberOfBeanBagsSoldException {
        Store store = new Store();
        store.addBeanBags(100,"Bean Makers", "Beans", "33221100",(short)2017,(byte)12);
        store.setBeanBagPrice("33221100",500);
        store.sellBeanBags(50,"33221100");
        assert store.getTotalPriceOfSoldBeanBags() == 25000;
        store.resetSaleAndCostTracking();
        assert store.getTotalPriceOfSoldBeanBags() == 0;
        boolean thrown = false;
    }
}