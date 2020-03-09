package testing.beanbagreservation;

import testing.Unittest;
import beanbags.*;

public class TestGetLowestPrice implements Unittest {
    public TestGetLowestPrice() {
    }
    public void run() {
        BeanBagReservation testRes = new BeanBagReservation("AB12CD22",5,1337);
        assert Integer.compare(testRes.getLowestPrice(),1337) == 0;
    }
}