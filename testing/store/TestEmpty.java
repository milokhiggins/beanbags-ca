package testing.store;

import testing.Unittest;
import beanbags.*;

public class TestEmpty implements Unittest {
    public TestEmpty() {
    }
    public void run() throws IllegalIDException, BeanBagMismatchException, InvalidMonthException, IllegalNumberOfBeanBagsAddedException, InvalidPriceException, BeanBagIDNotRecognisedException, PriceNotSetException, BeanBagNotInStockException, InsufficientStockException, IllegalNumberOfBeanBagsSoldException {
        Store store = new Store();
        store.addBeanBags(10, "bleh", "foo", "01020304",
                (short)2930, (byte)4, "foobaz");
        store.setBeanBagPrice("01020304",500);
        store.sellBeanBags(5, "01020304");
        assert store.beanBagsInStock() > 0;
        store.empty();
        assert store.beanBagsInStock() == 0;
        assert store.getNumberOfDifferentBeanBagsInStock() == 0;
        assert store.getTotalPriceOfSoldBeanBags() == 0;
    }
}