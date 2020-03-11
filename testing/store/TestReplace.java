package testing.store;

import testing.Unittest;
import beanbags.*;

public class TestReplace implements Unittest {
    public TestReplace() {
    }
    public void run() throws IllegalIDException, BeanBagMismatchException, InvalidMonthException, IllegalNumberOfBeanBagsAddedException, InvalidPriceException, BeanBagIDNotRecognisedException, PriceNotSetException, BeanBagNotInStockException, InsufficientStockException, IllegalNumberOfBeanBagsSoldException, IllegalNumberOfBeanBagsReservedException {
        Store store = new Store();

        store.addBeanBags(100, "BeanBeanBean", "Bean bean bean bean", "BEA40001", (short)1990,(byte)5);

        store.setBeanBagPrice("BEA40001", 78000);

        store.sellBeanBags(50,"BEA40001");
        store.sellBeanBags(5,"BEA40001");
        int num = store.reserveBeanBags(10, "BEA40001");

        assert store.beanBagsInStock() == 45;
        assert store.reservedBeanBagsInStock() == 10;
        assert store.getTotalPriceOfSoldBeanBags("BEA40001") == (55*78000);

        store.replace("BEA40001", "BEA40011");

        assert store.getTotalPriceOfSoldBeanBags("BEA40011") == (55*78000);
        boolean thrown = false;
        try {
            store.getTotalPriceOfSoldBeanBags("BEA40001");
        } catch (BeanBagIDNotRecognisedException e) {
            thrown = true;
        }
        assert thrown : "ID not completely replaced";


    }
}