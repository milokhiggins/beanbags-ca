package beanbags;
import java.io.IOException;


/**
 * BadStore is a minimally compiling, but non-functioning implementor of the
 * BeanBagStore interface.
 * 
 * @author Jonathan Fieldsend 
 * @version 1.1
 */
public class BadStore implements BeanBagStore
{
 
    public void addBeanBags(int num, String manufacturer, String name, 
    String id, short year, byte month)
    throws IllegalNumberOfBeanBagsAddedException, BeanBagMismatchException,
    IllegalIDException, InvalidMonthException {

    }

    public void addBeanBags(int num, String manufacturer, String name, 
    String id, short year, byte month, String information)
    throws IllegalNumberOfBeanBagsAddedException, BeanBagMismatchException,
    IllegalIDException, InvalidMonthException {

    }

    public void setBeanBagPrice(String id, int priceInPence) 
    throws InvalidPriceException, BeanBagIDNotRecognisedException, IllegalIDException {

    }

    public void sellBeanBags(int num, String id) throws BeanBagNotInStockException,
    InsufficientStockException, IllegalNumberOfBeanBagsSoldException,
    PriceNotSetException, BeanBagIDNotRecognisedException, IllegalIDException {

    }

    public int reserveBeanBags(int num, String id) throws BeanBagNotInStockException,
    InsufficientStockException, IllegalNumberOfBeanBagsReservedException,
    PriceNotSetException, BeanBagIDNotRecognisedException, IllegalIDException {
        return 0;
    }

    public void unreserveBeanBags(int reservationNumber)
    throws ReservationNumberNotRecognisedException {

    }

    public void sellBeanBags(int reservationNumber)
    throws ReservationNumberNotRecognisedException {

    }

    public int beanBagsInStock() {
        return 0;
    }

    public int reservedBeanBagsInStock() {
        return 0;
    }

    public int beanBagsInStock(String id) throws BeanBagIDNotRecognisedException,
    IllegalIDException {
        return 0;
    }

    public void saveStoreContents(String filename) throws IOException {

    }

    public void loadStoreContents(String filename) throws IOException,
    ClassNotFoundException {

    }

    public int getNumberOfDifferentBeanBagsInStock() {
        return 0;
    }

    public int getNumberOfSoldBeanBags() {
        return 0;
    }

    public int getNumberOfSoldBeanBags(String id) throws
    BeanBagIDNotRecognisedException, IllegalIDException {
        return 0;
    }

    public int getTotalPriceOfSoldBeanBags() {
        return 0;
    }

    public int getTotalPriceOfSoldBeanBags(String id) throws
    BeanBagIDNotRecognisedException, IllegalIDException {
        return 0;
    }

    public int getTotalPriceOfReservedBeanBags() {
        return 0;
    }

    public String getBeanBagDetails(String id) throws
    BeanBagIDNotRecognisedException, IllegalIDException {
        return "";
    }

    public void empty() {

    }
     
    public void resetSaleAndCostTracking() {

    }
     
    public void replace(String oldId, String replacementId) 
    throws BeanBagIDNotRecognisedException, IllegalIDException {

    }
}
