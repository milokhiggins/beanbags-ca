package testing.store;

import testing.Unittest;
import beanbags.*;

public class TestReservedBeanBagsInStock implements Unittest {
    public TestReservedBeanBagsInStock() {
    }
    public void run() throws IllegalIDException, BeanBagMismatchException, InvalidMonthException, IllegalNumberOfBeanBagsAddedException, InvalidPriceException, BeanBagIDNotRecognisedException, BeanBagNotInStockException, InsufficientStockException, IllegalNumberOfBeanBagsReservedException, PriceNotSetException, ReservationNumberNotRecognisedException {
        Store store = new Store();

        store.addBeanBags(15, "BeanCo", "Blue Beans", "345CCD82", (short)2016, (byte)5);
        store.setBeanBagPrice("345CCD82", 1500);

        int resNum = store.reserveBeanBags(10, "345CCD82");

        assert store.reservedBeanBagsInStock() == 10;

        store.sellBeanBags(resNum);

        assert store.reservedBeanBagsInStock() == 0;
    }
}