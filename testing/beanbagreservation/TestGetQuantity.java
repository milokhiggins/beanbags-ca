package testing.beanbagreservation;

import testing.Unittest;
import beanbags.*;

public class TestGetQuantity implements Unittest {
    public TestGetQuantity() {
    }
    public void run() {
        BeanBagReservation testRes = new BeanBagReservation("ABC634D4",30,2399);
        assert Integer.compare(testRes.getQuantity(),30) == 0;
    }
}