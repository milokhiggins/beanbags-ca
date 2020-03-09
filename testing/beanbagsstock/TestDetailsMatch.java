package testing.beanbagsstock;

import beanbags.BeanBagsStock;
import beanbags.IllegalNumberOfBeanBagsAddedException;
import beanbags.InvalidMonthException;
import testing.Unittest;

public class TestDetailsMatch implements Unittest {
    public TestDetailsMatch() {
    }
    public void run() throws IllegalNumberOfBeanBagsAddedException, InvalidMonthException {
        BeanBagsStock beans = new BeanBagsStock("33443322", "Blue Beans", "Blue Beans PLC",
                (short)2013, (byte)2, 10);
        assert beans.checkDetailsMatch("Blue Beans", "Blue Beans PLC", "");
        assert !beans.checkDetailsMatch("Jeff's Beans", "Blue Beans PLC", "");
    }
}