package testing.store;

import beanbags.*;
import testing.Unittest;

import java.io.IOException;

public class TestAddBeanBags implements Unittest {
    public TestAddBeanBags (){ }
    public void run() throws BeanBagIDNotRecognisedException, IllegalNumberOfBeanBagsAddedException,
            BeanBagMismatchException, BeanBagNotInStockException, IllegalIDException, IllegalNumberOfBeanBagsAddedException,
            IllegalNumberOfBeanBagsReservedException, IllegalNumberOfBeanBagsSoldException, InsufficientStockException, InvalidMonthException,
            InvalidPriceException, PriceNotSetException, ReservationNumberNotRecognisedException, IOException, ClassNotFoundException {
        Store store = new Store();

        //try adding beanbag
        store.addBeanBags(5 , "manufacturer", "Sarah'sBean", "128AB346",
                (short)1994, (byte)1,"hello!");
        //checks that the addition text is correct.
        assert  "hello!".equals(store.getBeanBagDetails("128AB346"));

        try {
        store.addBeanBags(0, "manufacturer", "Milo's Beanbag", "12345678",
                (short)2000, (byte)12);
        } catch (IllegalNumberOfBeanBagsAddedException e) {

        }

    }
}