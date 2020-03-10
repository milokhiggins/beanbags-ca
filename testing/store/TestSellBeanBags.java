package testing.store;

import testing.Unittest;
import beanbags.*;

public class TestSellBeanBags implements Unittest {
    public TestSellBeanBags() {
    }
    public void run() throws IllegalIDException, BeanBagMismatchException, InvalidMonthException, IllegalNumberOfBeanBagsAddedException, InvalidPriceException, BeanBagIDNotRecognisedException, PriceNotSetException, BeanBagNotInStockException, InsufficientStockException, IllegalNumberOfBeanBagsSoldException, IllegalNumberOfBeanBagsReservedException, ReservationNumberNotRecognisedException {
        Store store = new Store();
        store.addBeanBags(100,"Bean Makers", "Beans", "33221100",(short)2017,(byte)12);
        store.setBeanBagPrice("33221100",1400);
        store.sellBeanBags(10,"33221100");
        assert store.beanBagsInStock() == 90;
        int resNum = store.reserveBeanBags(20,"33221100");
        store.sellBeanBags(resNum);
        assert store.beanBagsInStock() == 70;
        assert store.reservedBeanBagsInStock() == 0;
        assert store.getTotalPriceOfSoldBeanBags() == 42000;

        boolean thrown = false;
        try {
            store.sellBeanBags(-34, "33221100");
        } catch(IllegalNumberOfBeanBagsSoldException e) {
            thrown = true;
        }
        assert thrown : "IllegalNumberOfBeanBagsSoldException not thrown";
        thrown = false;
        try {
            store.sellBeanBags(5, "froggychair");
        } catch(IllegalIDException e ) {
            thrown = true;
        }
        assert thrown : "IllegalIDException not thrown";
        thrown = false;
        try {
            store.sellBeanBags(10, "01010111");
        } catch(BeanBagIDNotRecognisedException e) {
            thrown = true;
        }
        assert thrown : "BeanBagIDNotRecognisedException not thrown";
        thrown = false;
        try {
            store.sellBeanBags(75,"33221100");
        } catch(InsufficientStockException e) {
            thrown = true;
        }
        assert thrown : "InsufficientStockException not thrown";
        thrown = false;
        store.sellBeanBags(70,"33221100");
        try {
            store.sellBeanBags(10,"33221100");
        } catch(BeanBagNotInStockException e) {
            thrown = true;
        }
        assert thrown : "BeanBagNotInStockException not thrown";
        thrown = false;
        store.addBeanBags(100,"Bean Makers", "Green Beans", "33221105",(short)2017,(byte)12);
        try {
            store.sellBeanBags(10,"33221105");
        } catch(PriceNotSetException e) {
            thrown = true;
        }
        assert thrown : "PriceNotSetException not thrown";

    }

}