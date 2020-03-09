package testing.beanbagreservation;

import beanbags.BeanBagReservation;
import testing.Unittest;

public class TestSetId implements Unittest {
    public TestSetId() {
    }
    public void run() {
        BeanBagReservation testRes = new BeanBagReservation("34F3CA82",8,3200);
        testRes.setId("4545CCEF");
        assert testRes.getId().equals("4545CCEF");
    }
}