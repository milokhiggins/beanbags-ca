package testing.beanbagsstock;

import testing.Unittest;
import beanbags.*;

public class TestGetQuantityUnreserved implements Unittest {
    public TestGetQuantityUnreserved() {
    }
    public void run() throws IllegalNumberOfBeanBagsAddedException, InvalidMonthException {
        BeanBagsStock beans = new BeanBagsStock("00000001","The first Bean", "God",
                (short)1, (byte)1,1);
        assert beans.getQuantityUnreserved() == 1;
        beans.reserve(1);
        assert beans.getQuantityUnreserved() == 0;
    }
}