package testing.beanbagsstock;

import beanbags.*;
import testing.Unittest;

public class TestGetPrice implements Unittest {
    public TestGetPrice() {
    }
    public void run() throws IllegalNumberOfBeanBagsAddedException, InvalidMonthException, PriceNotSetException, InvalidPriceException {
        BeanBagsStock beans = new BeanBagsStock("44444444", "Magic Bean Bags", "Fairytail Beans LTD",
                (short)340, (byte)3, 30);
        boolean thrown = false;
        try {
            beans.getPrice();
        } catch(PriceNotSetException e) {
            thrown = true;
        }
        assert thrown : "PriceNotSetException not thrown";
        beans.setPrice(5555);
        assert beans.getPrice() == 5555;
    }
}