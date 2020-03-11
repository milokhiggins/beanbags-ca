package testing.store;

import beanbags.*;
import testing.Unittest;

public class TestGetTotalPriceOfSoldBeanBags implements Unittest {
    public TestGetTotalPriceOfSoldBeanBags() throws IllegalIDException, BeanBagMismatchException, InvalidMonthException, IllegalNumberOfBeanBagsAddedException, InvalidPriceException, BeanBagIDNotRecognisedException, PriceNotSetException, BeanBagNotInStockException, InsufficientStockException, IllegalNumberOfBeanBagsSoldException {
    }
    public void run() throws IllegalIDException, BeanBagMismatchException, InvalidMonthException, IllegalNumberOfBeanBagsAddedException, InvalidPriceException, BeanBagIDNotRecognisedException, PriceNotSetException, BeanBagNotInStockException, InsufficientStockException, IllegalNumberOfBeanBagsSoldException {
        Store store = new Store();

        assert store.getTotalPriceOfSoldBeanBags() == 0 : "total price of sold bean bags initialises incorrectly.";

        store.addBeanBags(5, "manufacturer", "Sarah'sBean", "128AB346",
                (short) 1994, (byte) 1, "hello!");
        store.setBeanBagPrice("128AB346", 200);
        store.sellBeanBags(5, "128AB346");
        assert store.getTotalPriceOfSoldBeanBags() == 1000 : "total price of sold bags faulty";

        store.addBeanBags(5, "manufacturer", "Sarah'sBean", "128AB349",
                (short) 1994, (byte) 1, "hello!");
        store.setBeanBagPrice("128AB349", 500);
        store.sellBeanBags(5, "128AB349");
        assert store.getTotalPriceOfSoldBeanBags() == (2500+1000) : "total price of sold bean bags faulty";

        boolean thrown = false;
        try {
            store.getTotalPriceOfSoldBeanBags("invalid id");
        } catch(IllegalIDException e) {
            thrown = true;
        }
        assert thrown : "IllegalIDException not recognised";
        assert store.getTotalPriceOfSoldBeanBags("128AB346") == 1000;
    }
}