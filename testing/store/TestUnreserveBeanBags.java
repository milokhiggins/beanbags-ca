package testing.store;

import testing.Unittest;
import beanbags.*;

public class TestUnreserveBeanBags implements Unittest {
    public TestUnreserveBeanBags() {
    }
    public void run() throws IllegalIDException, BeanBagMismatchException, InvalidMonthException, IllegalNumberOfBeanBagsAddedException, InvalidPriceException, BeanBagIDNotRecognisedException, BeanBagNotInStockException, InsufficientStockException, IllegalNumberOfBeanBagsReservedException, PriceNotSetException, ReservationNumberNotRecognisedException {
        Store store = new Store();

        store.addBeanBags(5,"Bobs Beans", "Bean Bag", "ABB34666", (short)2015,(byte)5);
        store.setBeanBagPrice("ABB34666",1000);
        int resNum = store.reserveBeanBags(5,"ABB34666");
        assert store.reservedBeanBagsInStock() == 5;
        store.unreserveBeanBags(resNum);
        assert store.reservedBeanBagsInStock() == 0;
        assert store.beanBagsInStock() == 5;
        boolean thrown = false;
        try {
            store.unreserveBeanBags(2323);
        } catch(ReservationNumberNotRecognisedException e) {
            thrown = true;
        }
        assert thrown : "ReservationNumberNotRecognisedException not thrown";
    }
}