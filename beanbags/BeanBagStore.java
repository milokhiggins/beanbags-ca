package beanbags;
import java.io.IOException;

/**
 * BeanBagStore interface. The no-argument constructor of a class
 * implementing this interface should initialise the BeanBagStore 
 * as an empty store with no initial bean bags contained within it.
 * 
 * @author Jonathan Fieldsend 
 * @version 1.3
 */

public interface BeanBagStore
{

    /**
     * Method adds bean bags to the store with the arguments as bean bag details.
     * <p>
     * The state of this BeanBagStore must be be unchanged if any exceptions are 
     * thrown.
     *
     * @param num               number of bean bags added
     * @param manufacturer      bean bag manufacturer
     * @param name              bean bag name
     * @param id                ID of bean bag 
     * @param year              year of manufacture
     * @param month             month of manufacture
     * @throws IllegalNumberOfBeanBagsAddedException   if the number to be added 
     *                           is less than 1
     * @throws BeanBagMismatchException if the id already exists (as a current in
     *                           stock bean bag, or one that has been previously 
     *                           stocked in the store, but the other stored 
     *                           elements (manufacturer, name and free text) do 
     *                           not match the pre-existing version
     * @throws IllegalIDException   if the ID is not a positive eight character 
     *                           hexadecimal number
     * @throws InvalidMonthException    if the month is not in the range 1 to 12
     */
    void addBeanBags(int num, String manufacturer, String name,
    String id, short year, byte month)
    throws IllegalNumberOfBeanBagsAddedException, BeanBagMismatchException,
    IllegalIDException, InvalidMonthException;

    /**
     * Method adds bean bags to the store with the arguments as bean bag details.
     * <p>
     * The state of this BeanBagStore must be be unchanged if any exceptions are
     * thrown.
     *
     * @param num               number of bean bags added
     * @param manufacturer      bean bag manufacturer
     * @param name              bean bag name
     * @param id                ID of bean bag
     * @param year              year of manufacture
     * @param month             month of manufacture
     * @param information       free text detailing bean bag information
     * @throws IllegalNumberOfBeanBagsAddedException   if the number to be added
     *                           is less than 1
     * @throws BeanBagMismatchException if the id already exists (as a current in
     *                           stock bean bag, or one that has been previously 
     *                           stocked in the store, but the other stored 
     *                           elements (manufacturer, name and free text) do 
     *                           not match the pre-existing version
     * @throws IllegalIDException   if the ID is not a positive eight character
     *                           hexadecimal number
     * @throws InvalidMonthException    if the month is not in the range 1 to 12
     */
    void addBeanBags(int num, String manufacturer, String name,
    String id, short year, byte month, String information)
    throws IllegalNumberOfBeanBagsAddedException, BeanBagMismatchException,
    IllegalIDException, InvalidMonthException;

    /**
     * Method to set the price of bean bags with matching ID in stock.
     * <p>
     * The state of this BeanBagStore must be be unchanged if any exceptions are
     * thrown.
     *
     * @param id                ID of bean bags
     * @param priceInPence      bean bag price in pence
     * @throws InvalidPriceException if the priceInPence < 1
     * @throws BeanBagIDNotRecognisedException  if the ID is legal, but does not 
     *                          match any bag in (or previously in) stock
     * @throws IllegalIDException if the ID is not a positive eight character
     *                           hexadecimal number
     */
    void setBeanBagPrice(String id, int priceInPence) throws
    InvalidPriceException, BeanBagIDNotRecognisedException, IllegalIDException;

    /**
     * Method sells bean bags with the corresponding ID from the store and removes
     * the sold bean bags from the stock.
     * <p>
     * The state of this BeanBagStore must be be unchanged if any exceptions are
     * thrown.
     *
     * @param num           number of bean bags to be sold
     * @param id            ID of bean bags to be sold
     * @throws BeanBagNotInStockException   if the bean bag has previously been in
     *                      stock, but is now out of stock
     * @throws InsufficientStockException   if the bean bag is in stock, but not
     *                      enough are available (i.e. in stock and not reserved) 
     *                      to meet sale demand
     * @throws IllegalNumberOfBeanBagsSoldException if an attempt is being made to 
     *                      sell fewer than 1 bean bag
     * @throws PriceNotSetException if the bag is in stock, and there is sufficient
     *                      stock to meet demand, but the price has yet to be set
     * @throws BeanBagIDNotRecognisedException  if the ID is legal, but does not 
     *                          match any bag in (or previously in) stock
     * @throws IllegalIDException   if the ID is not a positive eight character
     *                           hexadecimal number
     */
    void sellBeanBags(int num, String id) throws BeanBagNotInStockException,
    InsufficientStockException, IllegalNumberOfBeanBagsSoldException,
    PriceNotSetException, BeanBagIDNotRecognisedException, IllegalIDException;

    /**
     * Method reserves bean bags with the corresponding ID in the store and returns
     * the reservation number needed to later access the reservation
     * <p>
     * The state of this BeanBagStore must be be unchanged if any exceptions are
     * thrown.
     *
     * @param num           number of bean bags to be reserved
     * @param id            ID of bean bags to be reserved
     * @return              unique reservation number, i.e. one not currently live 
     *                      in the system
     * @throws BeanBagNotInStockException   if the bean bag has previously been in
     *                      stock, but is now out of stock
     * @throws InsufficientStockException   if the bean bag is in stock, but not
     *                      enough are available to meet the reservation demand
     * @throws IllegalNumberOfBeanBagsReservedException if the number of bean bags
     *                      requested to reserve is fewer than 1
     * @throws PriceNotSetException if the bag is in stock, and there is sufficient
     *                      stock to meet demand, but the price has yet to be set
     * @throws BeanBagIDNotRecognisedException  if the ID is legal, but does not 
     *                          match any bag in (or previously in) stock
     * @throws IllegalIDException   if the ID is not a positive eight character
     *                           hexadecimal number
     */
    int reserveBeanBags(int num, String id) throws BeanBagNotInStockException,
    InsufficientStockException, IllegalNumberOfBeanBagsReservedException,
    PriceNotSetException, BeanBagIDNotRecognisedException, IllegalIDException;

    /**
     * Method removes an existing reservation from the system due to a reservation 
     * cancellation (rather than sale). The stock should therefore remain unchanged.
     * <p>
     * The state of this BeanBagStore must be be unchanged if any exceptions are
     * thrown.
     *
     * @param reservationNumber           reservation number
     * @throws ReservationNumberNotRecognisedException  if the reservation number
     *                          does not match a current reservation in the system
     */
    void unreserveBeanBags(int reservationNumber)
    throws ReservationNumberNotRecognisedException;

    /**
     * Method sells beanbags with the corresponding reservation number from
     * the store and removes these sold beanbags from the stock.
     * <p>
     * The state of this BeanBagStore must be be unchanged if any exceptions are
     * thrown.
     *
     * @param reservationNumber           unique reservation number used to find 
     *                                    beanbag(s) to be sold
     * @throws ReservationNumberNotRecognisedException  if the reservation number
     *                          does not match a current reservation in the system
     */
    void sellBeanBags(int reservationNumber)
    throws ReservationNumberNotRecognisedException;

    /**
     * Access method for the number of BeanBags stocked by this BeanBagStore
     * (total of reserved and unreserved stock).
     *
     * @return                  number of bean bags in this store
     */
    int beanBagsInStock();

    /**
     * Access method for the number of reserved bean bags stocked by this
     * BeanBagStore.
     *
     * @return                  number of reserved bean bags in this store
     */
    int reservedBeanBagsInStock();

    /**
     * Method returns number of bean bags with matching ID in stock (total
     * researved and unreserved).
     * <p>
     * The state of this BeanBagStore must be be unchanged if any exceptions are
     * thrown.
     *
     * @param id            ID of bean bags
     * @return              number of bean bags matching ID in stock
     * @throws BeanBagIDNotRecognisedException  if the ID is legal, but does not 
     *                          match any bag in (or previously in) stock
     * @throws IllegalIDException   if the ID is not a positive eight character
     *                           hexadecimal number
     */
    int beanBagsInStock(String id) throws BeanBagIDNotRecognisedException,
    IllegalIDException;

    /**
     * Method saves this BeanBagStore's contents into a serialised file,
     * with the filename given in the argument.
     *
     * @param filename      location of the file to be saved
     * @throws IOException  if there is a problem experienced when trying to save 
     *                      the store contents to the file
     */
    void saveStoreContents(String filename) throws IOException;

    /**
     * Method should load and replace this BeanBagStore's contents with the
     * serialised contents stored in the file given in the argument.
     * <p>
     * The state of this BeanBagStore must be be unchanged if any exceptions are
     * thrown.
     *
     * @param filename      location of the file to be loaded
     * @throws IOException  if there is a problem experienced when trying to load 
     *                      the store contents from the file
     * @throws ClassNotFoundException   if required class files cannot be found when
     *                      loading
     */
    void loadStoreContents(String filename) throws IOException,
    ClassNotFoundException;

    /**
     * Access method for the number of different bean bags currently stocked by this
     * BeanBagStore.
     *
     * @return                  number of different specific bean bags currently in
     *                          this store (i.e. how many different IDs represented
     *                          by bean bags currently in stock, including reserved)
     */
    int getNumberOfDifferentBeanBagsInStock();

    /**
     * Method to return number of bean bags sold by this BeanBagStore.
     *
     * @return                  number of bean bags sold by the store
     */
    int getNumberOfSoldBeanBags();

    /**
     * Method to return number of bean bags sold by this BeanBagStore with
     * matching ID.
     * <p>
     * The state of this BeanBagStore must be be unchanged if any exceptions are
     * thrown.
     *
     * @param id                 ID of bean bags
     * @return                   number bean bags sold by the store with matching ID
     * @throws BeanBagIDNotRecognisedException  if the ID is legal, but does not 
     *                          match any bag in (or previously in) stock
     * @throws IllegalIDException   if the ID is not a positive eight character
     *                           hexadecimal number
     */
    int getNumberOfSoldBeanBags(String id) throws
    BeanBagIDNotRecognisedException, IllegalIDException;

    /**
     * Method to return total price of bean bags sold by this BeanBagStore
     * (in pence), i.e. income that has been generated by these sales).
     *
     * @return                  total cost of bean bags sold (in pence)
     */
    int getTotalPriceOfSoldBeanBags();

    /**
     * Method to return total price of bean bags sold by this BeanBagStore
     * (in pence) with  matching ID (i.e. income that has been generated 
     * by these sales).
     * <p>
     * The state of this BeanBagStore must be be unchanged if any exceptions are
     * thrown.
     *
     * @param id                ID of bean bags
     * @return                  total cost of bean bags sold (in pence) with
     *                          matching ID
     * @throws BeanBagIDNotRecognisedException  if the ID is legal, but does not 
     *                          match any bag in (or previously in) stock
     * @throws IllegalIDException   if the ID is not a positive eight character
     *                           hexadecimal number
     */
    int getTotalPriceOfSoldBeanBags(String id) throws
    BeanBagIDNotRecognisedException, IllegalIDException;

    /**
     * Method to return the total price of reserved bean bags in this BeanBagStore
     * (i.e. income that would be generated if all the reserved stock is sold 
     * to those holding the reservations).
     *
     * @return                  total price of reserved bean bags
     */ 
    int getTotalPriceOfReservedBeanBags();

    /**
     * Method to return the free text details of a bean bag in stock. If there
     * are no String details for a bean bag, there will be an empty String
     * instance returned.
     * <p>
     * The state of this BeanBagStore must be be unchanged if any exceptions are
     * thrown.
     *
     * @param id                ID of bean bag
     * @return                  any free text details relating to the bean bag
     * @throws BeanBagIDNotRecognisedException  if the ID is legal, but does not 
     *                          match any bag in (or previously in) stock
     * @throws IllegalIDException   if the ID is not a positive eight character
     *                           hexadecimal number
     */
    String getBeanBagDetails(String id) throws
    BeanBagIDNotRecognisedException, IllegalIDException;

    /**
     * Method empties this BeanBagStore of its contents and resets
     * all internal counters.
     */
    void empty();

    /**
     * Method resets the tracking of number and costs of all bean bags sold.
     * The stock levels of this BeanBagStore and reservations should
     * be unaffected.
     */
    void resetSaleAndCostTracking();
    
    /**
     * Method replaces the ID of current stock matching the first argument with the 
     * ID held in the second argument. To be used if there was e.g. a data entry 
     * error on the ID initially entered. After the method has completed all stock
     * which had the old ID should now have the replacement ID (including 
     * reservations), and all trace of the old ID should be purged from the system 
     * (e.g. tracking of previous sales that had the old ID should reflect the 
     * replacement ID).
     * <p>
     * If the replacement ID already exists in the system, this method will return 
     * an {@link IllegalIDException}.
     * 
     * @param oldId             old ID of bean bags
     * @param replacementId     replacement ID of bean bags
     * @throws BeanBagIDNotRecognisedException  if the oldId does not match any 
     *                          bag in (or previously in) stock
     * @throws IllegalIDException   if either argument is not a positive eight 
     *                          character hexadecimal number, or if the 
     *                          replacementID is already in use in the store as 
     *                          an ID
     */
    void replace(String oldId, String replacementId) 
    throws BeanBagIDNotRecognisedException, IllegalIDException;
}

