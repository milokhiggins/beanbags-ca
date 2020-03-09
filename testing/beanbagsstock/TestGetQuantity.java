package testing.beanbagsstock;

import testing.Unittest;
import beanbags.*;

public class TestGetQuantity implements Unittest {
    public TestGetQuantity() {
    }
    public void run() throws IllegalNumberOfBeanBagsAddedException, InvalidMonthException {
        BeanBagsStock beans = new BeanBagsStock("876543AB","Milo's Beans", "Milo's Bean Machine",
                (short)2020, (byte)3, 17, "Only the finest of beans");
        assert beans.getQuantity() == 17;
    }
}