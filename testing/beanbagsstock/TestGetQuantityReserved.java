package testing.beanbagsstock;

import testing.Unittest;
import beanbags.*;

public class TestGetQuantityReserved implements Unittest {
    public TestGetQuantityReserved() {
    }
    public void run() throws IllegalNumberOfBeanBagsAddedException, InvalidMonthException {
        BeanBagsStock beans = new BeanBagsStock("33334444", "Boomer Beans", "Old Bean Co.",
                (short)1960,(byte)2,12);
        assert beans.getQuantityReserved() == 0;
        beans.reserve(5);
        assert beans.getQuantityReserved() == 5;
        beans.unreserve(3);
        assert beans.getQuantityReserved() == 2;
    }
}