package testing.store;

import beanbags.*;
import testing.Unittest;

import java.io.IOException;

/**
 * Requires beanbags field in store to be set to public.
 */
public class TestAddBeanBags implements Unittest {
    public TestAddBeanBags (){ }
    public void run() throws BeanBagIDNotRecognisedException, IllegalNumberOfBeanBagsAddedException,
            BeanBagMismatchException, BeanBagNotInStockException, IllegalIDException, IllegalNumberOfBeanBagsAddedException,
            IllegalNumberOfBeanBagsReservedException, IllegalNumberOfBeanBagsSoldException, InsufficientStockException, InvalidMonthException,
            InvalidPriceException, PriceNotSetException, ReservationNumberNotRecognisedException, IOException, ClassNotFoundException {
        Store store = new Store();
        //checks the banbags list is empty
        assert store.beanbags.size() == 0: " This should never happen: Beanbags list does not start empty";
        //try adding a new bean bag beanbag
        store.addBeanBags(5 , "manufacturer", "Sarah'sBean", "128AB346",
                (short)1994, (byte)1,"hello!");
        //check bean bag was added to the bean bag list:
        assert store.beanbags.size() == 1: "did not add beanbag to beanbags list";
        //checks that quantity was added to the beanBagTotalField
        assert store.beanBagsInStock() == 5:"beanBagTotal field is not functional";

        //try adding 10 beanbags with the same ID and same details.
        store.addBeanBags(10 , "manufacturer", "Sarah'sBean", "128AB346",
                (short)1994, (byte)1,"hello!");
        //check bean bag was added to the original bean bag stock:
        assert store.beanbags.size() == 1: "did not add beanbag to same id beanbag";
        //checks quantity of that bean bag ID increases correctly.
        BeanBagsStock SarahBean = (BeanBagsStock)store.beanbags.get(0);
        assert SarahBean.getQuantity() == 15: "Quantity did not increase correctly";
        //checks beanBagTotal increases correctly
        assert store.beanBagsInStock() == 15:"beanBagTotal field is not functional";


        //checks that the additional text is correct.
        assert  "hello!".equals(store.getBeanBagDetails("128AB346"));

        //try adding 10 beanbags with the same ID and different details.
        store.addBeanBags(10 , "manufacturer", "Sarah'sBean", "128AB346",
                (short)1994, (byte)1,"hello!");





        //try adding another beanbag




        try {
        store.addBeanBags(0, "manufacturer", "Milo's Beanbag", "12345678",
                (short)2000, (byte)12);
        } catch (IllegalNumberOfBeanBagsAddedException e) { System.out.println("correctly flags IllegalNumberOfBeanBagsAddedException adding 0 beanbags");

        }

    }
}