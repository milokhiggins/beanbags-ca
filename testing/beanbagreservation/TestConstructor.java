package testing.beanbagreservation;

import beanbags.*;
import testing.Unittest;

public class TestConstructor implements Unittest {
    public TestConstructor() {
    }
    public void run() {
        BeanBagReservation testReservation = new BeanBagReservation("345ABC99",1,360);
        BeanBagReservation testReservation2 = new BeanBagReservation("GHIJK563",5,3400);
    }
}