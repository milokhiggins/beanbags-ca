package testing.beanbagsstock;

import beanbags.BeanBagsStock;
import beanbags.IllegalNumberOfBeanBagsAddedException;
import beanbags.InvalidMonthException;
import testing.Unittest;

public class TestDecreaseQuantity implements Unittest {
    public TestDecreaseQuantity() {
    }
    public void run() throws IllegalNumberOfBeanBagsAddedException, InvalidMonthException {
        BeanBagsStock beans = new BeanBagsStock("BECCA007","Manufeccer","Exploding bean bags",
                (short)2049, (byte)5, 69);
        boolean none = beans.decreaseQuantity(8);
        assert beans.getQuantity() == 61;
        assert beans.getQuantityUnreserved() == 61;
        assert !none;
        boolean zero = beans.decreaseQuantity(61);
        assert beans.getQuantity() == 0;
        assert zero;

    }
}