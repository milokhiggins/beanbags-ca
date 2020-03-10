package testing.beanbagsstock;

import testing.Unittest;
import beanbags.*;

public class TestReserve implements Unittest {
    public TestReserve() {
    }
    public void run() throws IllegalNumberOfBeanBagsAddedException, InvalidMonthException {
        BeanBagsStock beans = new BeanBagsStock("FFFFFFFF","The Last Bean", "Milliways", (short)9999,
                (byte)12, 999);
        beans.reserve(599);
        assert beans.getQuantityReserved() == 599;
        assert beans.getQuantityUnreserved() == 400;
        beans.reserve(1);
        assert beans.getQuantityReserved() == 600;
    }
}