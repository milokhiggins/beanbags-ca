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

	private static void printDetails(Store store) {
		System.out.println("");
		System.out.println("===============================");
		System.out.println(store.getNumberOfDifferentBeanBagsInStock() + " different kinds of bean bags");
		System.out.println("Total of " + store.beanBagsInStock() + " bean bags in stock.");
		System.out.println("And " + store.reservedBeanBagsInStock() + " reserved bean bags.");
	}

	public static void main(String[] args) throws BeanBagIDNotRecognisedException, IllegalNumberOfBeanBagsAddedException,
			BeanBagMismatchException, BeanBagNotInStockException, IllegalIDException, IllegalNumberOfBeanBagsAddedException,
			IllegalNumberOfBeanBagsReservedException, IllegalNumberOfBeanBagsSoldException, InsufficientStockException, InvalidMonthException,
			InvalidPriceException, PriceNotSetException, ReservationNumberNotRecognisedException, IOException, ClassNotFoundException {
		Store store = new Store();
		store.addBeanBags(5,"BeanCo","Blue Bean bags","456123FF",(short)2019,(byte)5);
		store.addBeanBags(3,"BeanCo","Orange Bean bags","423555FF",(short)2019,(byte)6);
		store.addBeanBags(7,"BeanCo","Premium Red Bean bags","FF23342B",(short)2020,(byte)1);
		store.addBeanBags(10,"Beans'R'Us","Magic Beans","00700723",(short)2004,(byte)11,"The magic beans...");

		printDetails(store);

		store.setBeanBagPrice("423555FF", 3000);
		int resNum = store.reserveBeanBags(2, "423555FF");

		System.out.println("Reservation number " + resNum);

		store.setBeanBagPrice("456123FF", 4500);

		store.sellBeanBags(5,"456123FF");

		printDetails(store);

		store.addBeanBags(10,"BeanCo","Orange Bean bags", "423555FF", (short)2019,(byte)7);

		printDetails(store);

		System.out.println("...Selling the reservation...");
		store.sellBeanBags(resNum);

		printDetails(store);






	}
}
