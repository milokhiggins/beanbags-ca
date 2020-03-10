package testing.store;

import testing.Unittest;
import beanbags.*;

public class TestSetBeanBagPrice implements Unittest {
    public TestSetBeanBagPrice() {
    }
    public void run() throws IllegalIDException, BeanBagMismatchException, InvalidMonthException, IllegalNumberOfBeanBagsAddedException, InvalidPriceException, BeanBagIDNotRecognisedException, PriceNotSetException, BeanBagNotInStockException, InsufficientStockException, IllegalNumberOfBeanBagsSoldException {
        Store store = new Store();
        store.addBeanBags(100,"Bean Makers", "Beans", "33221100",(short)2017,(byte)12);
        store.setBeanBagPrice("33221100",1500);
        store.sellBeanBags(10, "33221100");
        assert store.getTotalPriceOfSoldBeanBags() == 15000;
        boolean thrown = false;
        try {
            store.setBeanBagPrice("EAEAEAFF",1550);
        } catch(BeanBagIDNotRecognisedException e) {
            thrown = true;
        }
        assert thrown : "BeanBagIDNotRecognisedException not thrown";
        thrown = false;
        try {
            store.setBeanBagPrice("33221100", -5);
        } catch(InvalidPriceException e) {
            thrown = true;
        }
        assert thrown : "InvalidPriceException not thrown";
        thrown = false;
        try {
            store.setBeanBagPrice("banana", 69);
        } catch(IllegalIDException e) {
            thrown = true;
        }
        assert thrown : "IllegalIDException not thrown";
    }
}