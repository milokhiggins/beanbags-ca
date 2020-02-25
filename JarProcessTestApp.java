import beanbags.Store;
import beanbags.BeanBagStore;
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
	public static void main(String[] args) {
		BeanBagStore store = new Store();
		System.out.println("BadStore instance successfully made, with "
                           + store.beanBagsInStock()
                           + " beanbags in stock.");
	}
}
