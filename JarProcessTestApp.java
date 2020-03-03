import beanbags.Store;
import beanbags.*;
import beanbags.BeanBagStore;
import beanbags.ObjectArrayList;
/**
 * Please follow instructions in the ECM1410_CA_jar_walkthrough
 * document in conjunction with this small application to
 * check you have built your jar file correctly
 * <p>
 * You should of course expand this and instantiate a Store
 * instance when checking the performance of the package as
 * your pair develops it
 *
 * @author Jonathan Fieldsend && SN690024245, SN680046138
 * @version 1.0
 */
public class JarProcessTestApp {
	public static void main(String[] args) throws BeanBagIDNotRecognisedException, IllegalNumberOfBeanBagsAddedException,
			BeanBagMismatchException, BeanBagNotInStockException, IllegalIDException, IllegalNumberOfBeanBagsAddedException,
			IllegalNumberOfBeanBagsReservedException, IllegalNumberOfBeanBagsSoldException, InsufficientStockException, InvalidMonthException,
			InvalidPriceException, PriceNotSetException, ReservationNumberNotRecognisedException{
		Store store = new Store();

		store.addBeanBags(5 , "manufacturer", "Sarah'sBean", "128AB346"
				, (short)1994, (byte)1,"hello!");

		System.out.println("BadStore instance successfully made, with "
                           + store.beanBagsInStock()
                           + " beanbags in stock.");




	}
}
