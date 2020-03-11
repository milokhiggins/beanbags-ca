package testing.beanbagsstock;

import beanbags.BeanBagsStock;
import beanbags.IllegalNumberOfBeanBagsAddedException;
import beanbags.InvalidMonthException;
import testing.Unittest;

public class TestConstructor implements Unittest {
    public TestConstructor() {
    }
    public void run() {

        boolean thrown = false;
        try {
            BeanBagsStock beas = new BeanBagsStock("80808080", "Froggychair","Meme beans",
                    (short)2009, (byte)123, 5);
        } catch(InvalidMonthException e) {
            thrown = true;
        }
        assert thrown : "InvalidMonthException not thrown";
    }
}