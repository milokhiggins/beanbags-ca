package beanbags;
import java.io.IOException;

/**
 * Store implements the BeanBagStore interface.
 * 
 * @author SN690024245 & SN680046138
 * @version 1.0
 */
public class Store implements BeanBagStore, java.io.Serializable
{
    private ObjectArrayList beanbags = new ObjectArrayList();
    private ObjectArrayList reservations = new ObjectArrayList();
    private int beanBagReservedTotal = 0;
    private ObjectArrayList soldBeanBags = new ObjectArrayList();
    private int numberBeanBagsSold = 0;
    private int totalPriceSoldBeanBags = 0;

    /**
     *
     * @param num               number of bean bags added
     * @param manufacturer      bean bag manufacturer
     * @param name              bean bag name
     * @param id                ID of bean bag
     * @param year              year of manufacture
     * @param month             month of manufacture
     * @throws IllegalNumberOfBeanBagsAddedException
     * @throws BeanBagMismatchException
     * @throws IllegalIDException
     * @throws InvalidMonthException
     */
    public void addBeanBags(int num, String manufacturer, String name, 
    String id, short year, byte month)
    throws IllegalNumberOfBeanBagsAddedException, BeanBagMismatchException,
    IllegalIDException, InvalidMonthException {
        this.addBeanBags(num, manufacturer, name, id, year, month, "");
    }

    public void addBeanBags(int num, String manufacturer, String name, 
    String id, short year, byte month, String information)
    throws IllegalNumberOfBeanBagsAddedException, BeanBagMismatchException,
    IllegalIDException, InvalidMonthException {
        int indexOfMatch = this.getBeanBagsById(id);
        if (indexOfMatch == -1) {
            //if bean bag does not exist, create new bean bag object
            BeanBags newBeanBags = new BeanBags(id, name, manufacturer, year,
                                              month, num, information);
            this.beanbags.add((Object)newBeanBags);
        } else {
            //if bean bag does exist, increase quantity
            BeanBags existingBeanBags = (BeanBags)this.beanbags.get(indexOfMatch);
            //check that details match with the same bean bag ID
            boolean detailsMatch = existingBeanBags.checkDetailsMatch(name, manufacturer,
                                                                      information);
            if (detailsMatch) {
                existingBeanBags.increaseQuantity(num);
                existingBeanBags.addDate(month, year);
            } else {
                throw new BeanBagMismatchException("The ID entered does not match the details of " +
                        "the bean bag");
            }
        }
    }

    public void setBeanBagPrice(String id, int priceInPence) 
    throws InvalidPriceException, BeanBagIDNotRecognisedException, IllegalIDException {
        if (priceInPence < 1) {
            throw new InvalidPriceException("Price must be greater than 0");
        }
        //check that ID is valid, throw exception if not
        checkId(id);
        int beanBagIndex = this.getBeanBagsById(id);
        //check that the bean bag exists in stock
        if (beanBagIndex == -1) {
            throw new BeanBagIDNotRecognisedException("Bean bag ID " + id + " is not recognised.");
        } else {
            BeanBags beanBags = (BeanBags) this.beanbags.get(beanBagIndex);
            if (beanBags.getQuantityReserved() > 0) {
                ObjectArrayList reservations = this.getReservationsByBeanBagId(id);
                for (int i = 0; i < reservations.size(); i++) {
                    BeanBagReservation reservation = (BeanBagReservation) reservations.get(i);
                    //update lowest price if the new price is lower
                    int lowPrice = reservation.getLowestPrice();
                    if (priceInPence < lowPrice) {
                        reservation.setLowestPrice(priceInPence);
                    }
                }
            }
            beanBags.setPrice(priceInPence);
        }
    }

    public void sellBeanBags(int num, String id) throws BeanBagNotInStockException,
    InsufficientStockException, IllegalNumberOfBeanBagsSoldException,
    PriceNotSetException, BeanBagIDNotRecognisedException, IllegalIDException {
        checkId(id);
        int indexOfMatch = this.getBeanBagsById(id);
        if (indexOfMatch == -1) {
            if (checkBeanBagSold(id)) {
                throw new BeanBagNotInStockException("Bean bag ID "+id+" is no longer in stock.");
            } else {
                throw new BeanBagIDNotRecognisedException("Bean bag ID "+id+" is not recognised");
            }
        } else {
            BeanBags beanbag = (BeanBags)beanbags.get(indexOfMatch);
            int numberUnreserved = beanbag.getQuantityUnreserved();
            if (numberUnreserved < num) {
                throw new InsufficientStockException("Not enough unreserved stock.");
            } else {
                beanbag.decreaseQuantity(num);
                BeanBags beanBagSale = new BeanBags()
            }
        }
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

    /**
     * Static method to check if an ID is valid, if not then throw an IllegalIDException
     * @param id id to check
     * @throws IllegalIDException id is not valid
     */
    private static void checkId(String id) throws IllegalIDException{
        //  checks if the string is positive,
        int hexadecimalNumber;
        //  checks if the string is a hexadecimal number.
        try{
            hexadecimalNumber = Integer.parseInt(id,16);
        }
        catch(NumberFormatException e){
            throw new IllegalIDException("ID must be hexadecimal number");
        }

        //  checks if the ID is positive
        if (hexadecimalNumber < 0 ){
            throw new IllegalIDException("ID must be positive");
        }
        // checks that 8 digits do not include a plus symbol.
        if (id.startsWith("+")){
            throw new IllegalIDException("ID must not contain + symbol");
        }

        // checks if the ID is 8 characters long.
        if (id.length() != 8 ){
            throw new IllegalIDException("ID must be eight characters");
        }
    }

    /**
     * Get the index of the bean bag if it is in stock, otherwise returns -1
     * @param id id of bean bag
     * @return index of bean bag, or -1 if no matching bean bag.
     */
    private int getBeanBagsById (String id) {
        for (int i = 0; i < beanbags.size(); i++) {
            BeanBags beanbag = (BeanBags)beanbags.get(i);
            if (beanbag.getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    /**
     *
     * @param id id of bean bag
     * @return true if bean bag id has been sold before, false otherwise.
     */
    private boolean checkBeanBagSold (String id) {
        for (int i = 0; i < soldBeanBags.size(); i++) {
            BeanBags beanbag = (BeanBags)soldBeanBags.get(i);
            if (beanbag.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
    private ObjectArrayList getReservationsByBeanBagId (String id) {
        return new ObjectArrayList();//fix me later
    }
}
