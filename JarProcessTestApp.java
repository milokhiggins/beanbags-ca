import beanbags.Store;
import beanbags.*;
import beanbags.BeanBagStore;
import beanbags.ObjectArrayList;

import java.io.IOException;

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
			InvalidPriceException, PriceNotSetException, ReservationNumberNotRecognisedException, IOException, ClassNotFoundException {
		Store store = new Store();

		store.addBeanBags(5 , "manufacturer", "Sarah'sBean", "128AB346",
				(short)1994, (byte)1,"hello!");

		System.out.println("BadStore instance successfully made, with "
                           + store.beanBagsInStock()
                           + " beanbags in stock.");
		store.addBeanBags(70, "manufacturer", "Milo's Beanbag", "12345678",
				(short)2000, (byte)12);
		System.out.println("Number of different bean bags " + store.getNumberOfDifferentBeanBagsInStock());
		store.addBeanBags(3, "manufacturer", "Sarah'sBean", "128AB346",
				(short)1995, (byte)5, "hello!");
		store.setBeanBagPrice("128AB346", 2);

		store.sellBeanBags(3, "128AB346");

		System.out.println("New stock count " + store.beanBagsInStock());
		System.out.println("Sold bean bags " + store.getNumberOfSoldBeanBags());
		System.out.println("Price of sold bean bags " + store.getTotalPriceOfSoldBeanBags());
		store.saveStoreContents("test.ser");
		store.empty();
		System.out.println("Stock count " + store.beanBagsInStock());
		System.out.println("...load from file...");
		store.loadStoreContents("test.ser");
		System.out.println("Stock count " + store.beanBagsInStock());



	}
}
