package testing.store;

import testing.Unittest;
import beanbags.*;

public class TestGetNumberOfDifferentBeanBagsInStock implements Unittest {
    public TestGetNumberOfDifferentBeanBagsInStock() {
    }
    public void run() throws IllegalIDException, BeanBagMismatchException, InvalidMonthException, IllegalNumberOfBeanBagsAddedException {
        Store store = new Store();
        store.addBeanBags(420,"beanmakers","beanbaggies","420420FB", (short)1975,(byte)5);
        store.addBeanBags(200,"beamakers","extra beany beans", "ABCD777E",(short)2004,(byte)3);
        assert store.getNumberOfDifferentBeanBagsInStock() == 2;
    }
}