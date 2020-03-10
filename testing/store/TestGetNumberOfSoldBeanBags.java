package testing.store;

import testing.Unittest;
import beanbags.*;

public class TestGetNumberOfSoldBeanBags implements Unittest {
    public void run() throws IllegalIDException, BeanBagMismatchException, InvalidMonthException, IllegalNumberOfBeanBagsAddedException, PriceNotSetException, BeanBagIDNotRecognisedException, BeanBagNotInStockException, InsufficientStockException, IllegalNumberOfBeanBagsSoldException, InvalidPriceException {
        Store store = new Store();
        assert store.getNumberOfSoldBeanBags() == 0 : "number of sold bean bags initialises incorrectly.";
        store.addBeanBags(5, "manufacturer", "Sarah'sBean", "128AB346",
                (short) 1994, (byte) 1, "hello!");
        store.setBeanBagPrice("128AB346", 200);
        store.sellBeanBags(5, "128AB346");
        assert store.getNumberOfSoldBeanBags() == 5 : "number of sold bean bags faulty";
        boolean thrown = false;
        try {
            store.getNumberOfSoldBeanBags("crazy bananas");
        } catch(IllegalIDException e) {
            thrown = true;
        }
        assert thrown : "IllegalIDException not thrown";
    }
}