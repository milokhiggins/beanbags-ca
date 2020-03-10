package testing.store;

import testing.Unittest;
import beanbags.*;

public class TestGetNumberOfReservedBeanBags implements Unittest {
    public TestGetNumberOfReservedBeanBags() {
    }
    public void run() throws IllegalIDException, BeanBagMismatchException, InvalidMonthException, IllegalNumberOfBeanBagsAddedException, InvalidPriceException, BeanBagIDNotRecognisedException, BeanBagNotInStockException, InsufficientStockException, IllegalNumberOfBeanBagsReservedException, PriceNotSetException {
        Store store = new Store();
        store.addBeanBags(10,"Manufactger","Bean bugs", "BECCA555",(short)2005,(byte)11);
        store.setBeanBagPrice("BECCA555",2500);
        store.reserveBeanBags(5,"BECCA555");
        assert store.reservedBeanBagsInStock() == 5;
    }

}