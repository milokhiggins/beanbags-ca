package testing.store;

import testing.Unittest;
import beanbags.*;

public class TestBeanBagsInStock implements Unittest {
    public TestBeanBagsInStock() {
    }
    public void run() throws IllegalIDException, BeanBagMismatchException, InvalidMonthException, IllegalNumberOfBeanBagsAddedException, BeanBagIDNotRecognisedException {
        Store store = new Store();

        store.addBeanBags(10,"man","nam","12341234",(short)2014,(byte)5);
        assert store.beanBagsInStock() == 10 : "beanBagsInStock failure";

        assert store.beanBagsInStock("12341234") == 10 : "beanBagsInStock failure";

        boolean thrown = false;
        try {
            store.beanBagsInStock("crazy bananas");
        } catch(IllegalIDException e) {
            thrown = true;
        }
        assert thrown;
        thrown = false;
        try {
            store.beanBagsInStock("00009999");
        } catch (BeanBagIDNotRecognisedException e) {
            thrown = true;
        }
        assert thrown;
    }
}