package testing.beanbagsstock;

import beanbags.*;
import testing.Unittest;

public class TestGetDetails implements Unittest {
    public TestGetDetails() {
    }
    public void run() throws IllegalNumberOfBeanBagsAddedException, InvalidMonthException, IllegalIDException {
        BeanBagsStock beans = new BeanBagsStock("1234ABCD", "Beans", "Bean Maker",
                (short)2019, (byte)5, 20, "Best bouncy beans bonza");
        ObjectArrayList dets = beans.getDetails();
        assert dets.get(0).equals("1234ABCD");
        assert dets.get(1).equals("Beans");
        assert dets.get(2).equals("Best bouncy beans bonza");
    }
}