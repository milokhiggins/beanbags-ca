package testing.store;

import beanbags.*;
import testing.Unittest;

public class TestGetTotalPriceOfReservedBeanBags implements Unittest {
    public TestGetTotalPriceOfReservedBeanBags() throws IllegalIDException, BeanBagMismatchException, InvalidMonthException, IllegalNumberOfBeanBagsAddedException, InvalidPriceException, BeanBagIDNotRecognisedException, BeanBagNotInStockException, InsufficientStockException, IllegalNumberOfBeanBagsReservedException, PriceNotSetException {
    }
    public void run() throws IllegalIDException, BeanBagMismatchException, InvalidMonthException, IllegalNumberOfBeanBagsAddedException, InvalidPriceException, BeanBagIDNotRecognisedException, BeanBagNotInStockException, InsufficientStockException, IllegalNumberOfBeanBagsReservedException, PriceNotSetException {

        Store store = new Store();

        assert store.getTotalPriceOfReservedBeanBags() == 0 : "Total price of reserved bean bags initialises incorrectly.";

        store.addBeanBags(5, "manufacturer", "Sarah'sBean", "128AB346",
                (short) 1994, (byte) 1, "hello!");
        store.setBeanBagPrice("128AB346", 200);
        store.reserveBeanBags(5, "128AB346" );

        assert store.getTotalPriceOfReservedBeanBags() == 1000 : "Total price of reserved bean not functioning";
        store.addBeanBags(5, "manufacturer1", "Sarah'sBean", "528AB346",
                (short) 1994, (byte) 1, "hello!");
        store.setBeanBagPrice("528AB346", 100);
        store.reserveBeanBags(5, "528AB346" );
        assert store.getTotalPriceOfReservedBeanBags() == 1500 : "Total price of reserved beans not functioning";
    }
}