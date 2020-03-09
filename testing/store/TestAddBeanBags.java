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

        System.out.println("Number of different bean bags " + store.getNumberOfDifferentBeanBagsInStock());
        store.addBeanBags(3, "manufacturer", "Sarah'sBean", "128AB346",
                (short)1995, (byte)5, "hello!");
        store.setBeanBagPrice("128AB346", 2);

        store.sellBeanBags(3, "128AB346");

        System.out.println("New stock count " + store.beanBagsInStock());
        System.out.println("Sold bean bags " + store.getNumberOfSoldBeanBags());
        System.out.println("Price of sold bean bags " + store.getTotalPriceOfSoldBeanBags());
        store.saveStoreContents("test.ser");
        store.empty();
        System.out.println("Stock count " + store.beanBagsInStock());
        System.out.println("...load from file...");
        store.loadStoreContents("test.ser");
        System.out.println("Stock count " + store.beanBagsInStock());






        System.out.println("BadStore instance successfully made, with "
                + store.beanBagsInStock()
                + " beanbags in stock.");
    }
}