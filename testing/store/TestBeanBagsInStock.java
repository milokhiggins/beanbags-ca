package testing.store;

import testing.Unittest;
import beanbags.*;

public class TestBeanBagsInStock implements Unittest {
    public TestBeanBagsInStock() {
    }
    public void run() throws IllegalIDException, BeanBagMismatchException, InvalidMonthException, IllegalNumberOfBeanBagsAddedException {
        Store store = new Store();

        store.addBeanBags(10,"man","nam","12341234",(short)2014,(byte)5);
        assert store.beanBagsInStock() == 10 : "beanBagsInStock failure";
    }
}