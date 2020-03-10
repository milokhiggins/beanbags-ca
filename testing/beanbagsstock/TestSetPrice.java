package testing.beanbagsstock;

import testing.Unittest;
import beanbags.*;

public class TestSetPrice implements Unittest {
    public TestSetPrice() {
    }
    public void run() throws IllegalNumberOfBeanBagsAddedException, InvalidMonthException, InvalidPriceException, PriceNotSetException {
        BeanBagsStock beans = new BeanBagsStock("34563456","Green Beans", "Bean Farm", (short)1975,
                (byte)7,33);
        beans.setPrice(50);
        assert beans.getPrice() == 50;
        boolean thrown = false;
        try {
            beans.setPrice(-5);
        } catch(InvalidPriceException e) {
            thrown = true;
        }
        assert thrown : "InvalidPriceException not thrown";
    }
}