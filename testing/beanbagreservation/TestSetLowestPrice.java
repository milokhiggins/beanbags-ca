package testing.beanbagreservation;

import beanbags.BeanBagReservation;
import testing.Unittest;

public class TestSetLowestPrice implements Unittest {
    public TestSetLowestPrice() {
    }
    public void run() {
        BeanBagReservation testRes = new BeanBagReservation("34F3CA82",8,3200);
        testRes.setLowestPrice(2500);
        assert Integer.compare(testRes.getLowestPrice(),2500) == 0;
        testRes.setLowestPrice(2750);
        assert Integer.compare(testRes.getLowestPrice(),2500) == 0;
    }
}