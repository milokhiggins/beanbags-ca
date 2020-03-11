package testing.store;
import beanbags.*;
import testing.Unittest;

import java.io.IOException;

public class TestLoadStoreContents implements Unittest {
    public void run() throws IllegalIDException, BeanBagMismatchException, InvalidMonthException, IllegalNumberOfBeanBagsAddedException, IOException, ClassNotFoundException, BeanBagIDNotRecognisedException, PriceNotSetException, BeanBagNotInStockException, InsufficientStockException, IllegalNumberOfBeanBagsSoldException, InvalidPriceException {
        Store store = new Store();
        store.addBeanBags(5, "manufacturer", "beanbag2", "928AB346",(short)1995,(byte)12, "hi");
        store.addBeanBags(12, "manufacturer", "beanbag3", "828AB346",(short)1995,(byte)12, "hello");
        store.setBeanBagPrice("828AB346", 200);
        store.sellBeanBags(12, "828AB346" );
        store.saveStoreContents("hi");
        Store store2 = new Store();
        store2.loadStoreContents("hi");
        assert  store2.getNumberOfDifferentBeanBagsInStock() == 1 : "store did not load store contents";
        assert (store2.getBeanBagDetails("928AB346")).equals("hi") : "addition text did not load in store contents";
        assert store2.getTotalPriceOfSoldBeanBags() == (12*200): "sold bena bags is not loaded";

    }
}