package testing.beanbagsstock;

import testing.Unittest;
import beanbags.*;

public class TestIncreaseQuantity implements Unittest {
    public TestIncreaseQuantity() {
    }
    public void run() throws IllegalNumberOfBeanBagsAddedException, InvalidMonthException {
        BeanBagsStock beans = new BeanBagsStock("00001001", "Dream Beans", "BeanCo", (short)1990,
                (byte)8, 13);
        beans.increaseQuantity(5);
        assert beans.getQuantity() == 18;
        assert beans.getQuantityUnreserved() == 18;
    }
}