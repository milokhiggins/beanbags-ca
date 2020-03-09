package testing.beanbagreservation;

import testing.Unittest;
import beanbags.*;

public class TestGetId implements Unittest {
    public TestGetId() {
    }
    public void run() {
        BeanBagReservation testRes = new BeanBagReservation("1234ABCD",5,3000);
        assert testRes.getId().equals("1234ABCD");
    }
}