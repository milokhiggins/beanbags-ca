package testing.beanbagsstock;

import testing.Unittest;
import beanbags.*;

public class TestAddDate implements Unittest {
    public TestAddDate() {

    }
    public void run() throws InvalidMonthException, IllegalIDException, IllegalNumberOfBeanBagsAddedException {
        BeanBagsStock beanbags = new BeanBagsStock("EA382CD3", "Magic Beans", "Magic Bean Co",
                    (short) 2020, (byte) 5, 5);

        beanbags.addDate((byte)5,(short)1980);
        boolean thrown = false;
        try {
            beanbags.addDate((byte)23,(short)-232);
        } catch (InvalidMonthException e) {
            System.out.println("Error thrown correctly");
            thrown = true;
        }
        assert thrown : "Error (InvalidMonthException) not thrown";
    }
}