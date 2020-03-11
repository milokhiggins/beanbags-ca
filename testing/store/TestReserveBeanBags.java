package testing.store;

import testing.Unittest;
import beanbags.*;

public class TestReserveBeanBags implements Unittest {
    public TestReserveBeanBags() {
    }
    public void run() throws IllegalIDException, BeanBagMismatchException, InvalidMonthException, IllegalNumberOfBeanBagsAddedException, InvalidPriceException, BeanBagIDNotRecognisedException, BeanBagNotInStockException, InsufficientStockException, IllegalNumberOfBeanBagsReservedException, PriceNotSetException, ReservationNumberNotRecognisedException, IllegalNumberOfBeanBagsSoldException {
        Store store = new Store();

        store.addBeanBags(50, "BeanBean", "Squishy beans", "222BACC1", (short)2018,(byte)5);
        store.addBeanBags(10,"BeanBean","Smooth coffee beans", "222BACC2", (short)2017, (byte)7);

        assert store.reservedBeanBagsInStock() == 0;

        store.setBeanBagPrice("222BACC1",5000);

        int num1 = store.reserveBeanBags(5,"222BACC1");
        int num2 = store.reserveBeanBags(10,"222BACC1");

        assert store.reservedBeanBagsInStock() == 5;

        store.setBeanBagPrice("222BACC1", 6000);

        store.sellBeanBags(num1);

        assert store.getTotalPriceOfSoldBeanBags() == (5*5000);

        store.setBeanBagPrice("222BACC1", 3000);

        store.sellBeanBags(num2);

        assert store.getTotalPriceOfSoldBeanBags() == (5*5000)+(10*3000);

        boolean thrown = false;
        try {
            int num3 = store.reserveBeanBags(3, "222BACC2");
        } catch(PriceNotSetException e) {
            thrown = true;
        }
        assert thrown : "PriceNotSetException not thrown";
        thrown = false;
        try {
            store.reserveBeanBags(-3,"222BACC2");
        } catch(IllegalNumberOfBeanBagsReservedException e) {
            thrown = true;
        }
        assert thrown : "IllegalNumberOfBeanBagsReservedException not thrown";
        thrown = false;
        try {
            store.reserveBeanBags(1, "34343434");
        } catch(BeanBagIDNotRecognisedException e) {
            thrown = true;
        }
        assert thrown : "BeanBagIDNotRecognisedException not thrown";
        thrown = false;
        try {
            store.reserveBeanBags(3, "crazy train");
        } catch(IllegalIDException e) {
            thrown = true;
        }
        assert thrown : "IllegalIDException not thrown";
        thrown = false;
        //test stock exception
        try {
            store.reserveBeanBags(300, "222BACC2");
        } catch(InsufficientStockException e) {
            thrown = true;
        }
        assert thrown : "InsufficientStockException not thrown";
        thrown = false;
        store.sellBeanBags(10,"222BACC2");
        try {
            store.reserveBeanBags(5,"222BACC2");
        } catch (BeanBagNotInStockException e) {
            thrown = true;
        }
        assert thrown : "BeanBagNotInStockException not thrown";

    }
}