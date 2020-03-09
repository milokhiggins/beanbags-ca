package testing.beanbagreservation;

import testing.Unittest;
import beanbags.*;

public class TestGetReservationNumber implements Unittest {
    public TestGetReservationNumber() {
    }
    public void run() {
        BeanBagReservation testRes = new BeanBagReservation("34343434",5,3000);
        BeanBagReservation testRes2 = new BeanBagReservation("1111AABB",10,7850);
        assert Integer.compare(testRes2.getReservationNumber(),testRes.getReservationNumber()+1) == 0;
    }
}