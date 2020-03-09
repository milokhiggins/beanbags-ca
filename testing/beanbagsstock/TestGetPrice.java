package testing.beanbagsstock;

import beanbags.BeanBagsStock;
import beanbags.IllegalNumberOfBeanBagsAddedException;
import beanbags.InvalidMonthException;
import testing.Unittest;

public class TestGetPrice implements Unittest {
    public TestGetPrice() {
    }
    public void run() throws IllegalNumberOfBeanBagsAddedException, InvalidMonthException {
        BeanBagsStock beans = new BeanBagsStock("44444444", "Magic Bean Bags", "Fairytail Beans LTD",
                (short)340, (byte)3, 30);
    }
}