package testing.store;

import testing.Unittest;
import beanbags.*;

public class TestGetBeanBagDetails implements Unittest {
    public TestGetBeanBagDetails() {
    }
    public void run() throws IllegalIDException, BeanBagMismatchException, InvalidMonthException, IllegalNumberOfBeanBagsAddedException, BeanBagIDNotRecognisedException {
        Store store = new Store();
        store.addBeanBags(3, "foo", "bar", "11233344", (short)1666, (byte)6);
        assert store.getBeanBagDetails("11233344").equals("");
        store.addBeanBags(1,"fooz","bazzers","34543000",(short)1914,(byte)5,
                "free text information money");
        assert store.getBeanBagDetails("34543000").equals("free text information money");
    }
}