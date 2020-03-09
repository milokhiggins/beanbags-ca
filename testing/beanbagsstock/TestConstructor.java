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
            BeanBagsStock beans = new BeanBagsStock("345123BB", "Beanie beans", "Beaners Inc.",
                    (short)1990, (byte)11, -232433434);
        } catch(IllegalNumberOfBeanBagsAddedException e) {
            thrown = true;
        } catch(InvalidMonthException e) {
            assert false : "(TCNH) month is not invalid";
        }
        assert thrown : "IllegalNumberOfBeanBagsAddedException not thrown";

        thrown = false;
        try {
            BeanBagsStock beas = new BeanBagsStock("80808080", "Froggychair","Meme beans",
                    (short)2009, (byte)123, 5);
        } catch(InvalidMonthException e) {
            thrown = true;
        } catch(IllegalNumberOfBeanBagsAddedException e) {
            assert false : "(TCNH) quantity is not invalid";
        }
        assert thrown : "InvalidMonthException not thrown";

    }
}