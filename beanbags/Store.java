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
    private int beanBagTotal = 0;
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
            BeanBagsStock newBeanBagsStock = new BeanBagsStock(id, name, manufacturer, year,
                                              month, num, information);
            this.beanbags.add((Object)newBeanBagsStock);
            beanBagTotal += num;
        } else {
            //if bean bag does exist, increase quantity
            BeanBagsStock existingBeanBagsStock = (BeanBagsStock)this.beanbags.get(indexOfMatch);
            //check that details match with the same bean bag ID
            boolean detailsMatch = existingBeanBagsStock.checkDetailsMatch(name, manufacturer,
                                                                      information);
            if (detailsMatch) {
                existingBeanBagsStock.increaseQuantity(num);
                existingBeanBagsStock.addDate(month, year);
                beanBagTotal += num;
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
            BeanBagsStock beanBagsStock = (BeanBagsStock) this.beanbags.get(beanBagIndex);
            if (beanBagsStock.getQuantityReserved() > 0) {
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
            beanBagsStock.setPrice(priceInPence);
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
            BeanBagsStock beanbag = (BeanBagsStock)beanbags.get(indexOfMatch);
            int numberUnreserved = beanbag.getQuantityUnreserved();
            if (num < 1) {
                throw new IllegalNumberOfBeanBagsSoldException("Cannot sell less than 1 bean bag");
            } else if (numberUnreserved < num) {
                throw new InsufficientStockException("Not enough unreserved stock.");
            } else {
                beanbag.decreaseQuantity(num);
                int price = beanbag.getPrice();
                Object[] beanBagSold = {id, num, price};
                soldBeanBags.add(beanBagSold);
                numberBeanBagsSold += num;
                beanBagTotal -= num;
                totalPriceSoldBeanBags += (num * beanbag.getPrice());
            }
        }
    }

    /**
     * Reserve bean bags and return the reservation number
     * @param num           number of bean bags to be reserved
     * @param id            ID of bean bags to be reserved
     * @return Reservation number
     * @throws BeanBagNotInStockException
     * @throws InsufficientStockException
     * @throws IllegalNumberOfBeanBagsReservedException
     * @throws PriceNotSetException
     * @throws BeanBagIDNotRecognisedException
     * @throws IllegalIDException
     */
    public int reserveBeanBags(int num, String id) throws BeanBagNotInStockException,
    InsufficientStockException, IllegalNumberOfBeanBagsReservedException,
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
            BeanBagsStock beanbag = (BeanBagsStock) beanbags.get(indexOfMatch);
            int numberUnreserved = beanbag.getQuantityUnreserved();
            if (num < 1) {
                throw new IllegalNumberOfBeanBagsReservedException("Cannot sell less than 1 bean " +
                        "bag");
            } else if (numberUnreserved < num) {
                throw new InsufficientStockException("Not enough unreserved stock.");
            } else {
                beanbag.reserve(num);
                BeanBagReservation reservation = new BeanBagReservation(id, num,
                        beanbag.getPrice());
                reservations.add(reservation);
                beanBagReservedTotal += num;
                return reservation.getReservationNumber();
            }
        }
    }

    public void unreserveBeanBags(int reservationNumber)
    throws ReservationNumberNotRecognisedException {
        int indexOfMatch = getReservationByReservationNumber(reservationNumber);
        if (indexOfMatch == -1) {
            throw new ReservationNumberNotRecognisedException("Reservation number not recognised");
        } else {
            BeanBagReservation reservation = (BeanBagReservation)reservations.get(reservationNumber);
            String id = reservation.getId();
            int quantity = reservation.getQuantity();
            reservations.remove(indexOfMatch);
            int beanBagIndex = getBeanBagsById(id);
            BeanBagsStock beanbag = (BeanBagsStock)beanbags.get(beanBagIndex);
            beanbag.unreserve(quantity);
        }
    }

    public void sellBeanBags(int reservationNumber)
    throws ReservationNumberNotRecognisedException {

    }

    public int beanBagsInStock() { return beanBagTotal; }

    public int reservedBeanBagsInStock() { return beanBagReservedTotal; }

    public int beanBagsInStock(String id) throws BeanBagIDNotRecognisedException,
    IllegalIDException {
        return 0;
    }

    public void saveStoreContents(String filename) throws IOException {

    }

    public void loadStoreContents(String filename) throws IOException,
    ClassNotFoundException {

    }

    public int getNumberOfDifferentBeanBagsInStock() { return beanbags.size(); }

    public int getNumberOfSoldBeanBags() { return numberBeanBagsSold; }

    public int getNumberOfSoldBeanBags(String id) throws
    BeanBagIDNotRecognisedException, IllegalIDException {
        checkId(id);
        int indexOfMatch = this.getBeanBagsById(id);
        if (indexOfMatch == -1) {
            throw new BeanBagIDNotRecognisedException("Bean bag ID " + id + " is not recognised");
        }
        int total = 0;
        for (int i = 0; i < soldBeanBags.size(); i++) {
            //sale = {id, number, price}
            Object[] sale = (Object[])soldBeanBags.get(i);
            String beanBagId = (String)sale[0];
            if (beanBagId.equals(id)) {
                int price = (int)sale[2];
                int quantity = (int)sale[1];
                total += price * quantity;
            }
        }
        return total;
    }

    public int getTotalPriceOfSoldBeanBags() { return totalPriceSoldBeanBags; }

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
            BeanBagsStock beanbag = (BeanBagsStock)beanbags.get(i);
            if (beanbag.getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private int getReservationByReservationNumber(int reservationNumber) {
        for (int i = 0; i < reservations.size(); i++) {
            BeanBagReservation reservation = (BeanBagReservation)reservations.get(i);
            if (reservation.getReservationNumber() == reservationNumber) {
                return i;
            }
        }
        //reservation not found
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

    /**
     * Iterates through bean bag reservations and returns a list of the beanbag reservations for
     * an id.
     * @param id id of bean bag
     * @return list of bean bag reservation objects
     */
    private ObjectArrayList getReservationsByBeanBagId (String id) {
        ObjectArrayList matchingReservations = new ObjectArrayList();
        // iterates through all reservations in reservations list.
        for (int i = 0; i < reservations.size(); i++) {
            BeanBagReservation beanbag = (BeanBagReservation) reservations.get(i);
            // checks if id of selected bean bag matches wanted id.
            if (beanbag.getId().equals(id)) {
                matchingReservations.add(beanbag);
            }
        }
        return matchingReservations;
    }
}
