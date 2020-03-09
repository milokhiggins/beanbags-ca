package testing.beanbagsstock;

import testing.Unittest;
import beanbags.*;

public class TestUnreserve implements Unittest {
    public TestUnreserve() {
    }
    public void run() throws IllegalNumberOfBeanBagsAddedException, InvalidMonthException {
        BeanBagsStock beans = new BeanBagsStock("0060009", "South African Bean Bag", "World Bean Bags",
                (short)1930, (byte)10,3);
        beans.reserve(10);
        assert beans.getQuantityReserved() == 10;
        beans.unreserve(5);
        assert beans.getQuantityReserved() == 0;
    }
}