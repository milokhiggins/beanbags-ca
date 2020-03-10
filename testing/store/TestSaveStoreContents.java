package testing.store;

import testing.Unittest;
import beanbags.*;

import java.io.IOException;

public class TestSaveStoreContents implements Unittest {
    public TestSaveStoreContents() {
    }
    public void run() throws IOException, IllegalIDException, BeanBagMismatchException, InvalidMonthException, IllegalNumberOfBeanBagsAddedException, ClassNotFoundException {
        Store store = new Store();
        store.addBeanBags(100,"Bean Makers", "Beans", "33221100",(short)2017,(byte)12);
        store.addBeanBags(50,"BeanCo", "Big Brain Beans", "33221105",(short)2017,(byte)10);
        store.addBeanBags(100,"Bean Makers", "Bread Beans", "33221106",(short)2018,(byte)12);
        store.saveStoreContents("testfile.ser");
        store.empty();
        assert store.beanBagsInStock() == 0;
        store.loadStoreContents("testfile.ser");
        assert store.beanBagsInStock() == 250;
        assert store.getNumberOfDifferentBeanBagsInStock() == 3;


    }
}