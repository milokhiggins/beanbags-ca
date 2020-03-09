package testing;

import beanbags.*;

import java.io.IOException;

public interface Unittest {
    void run() throws BeanBagIDNotRecognisedException, IllegalNumberOfBeanBagsAddedException, BeanBagMismatchException, BeanBagNotInStockException, IllegalIDException, IllegalNumberOfBeanBagsReservedException, IllegalNumberOfBeanBagsSoldException, InsufficientStockException, InvalidMonthException, InvalidPriceException, PriceNotSetException, ReservationNumberNotRecognisedException, IOException, ClassNotFoundException;
}
