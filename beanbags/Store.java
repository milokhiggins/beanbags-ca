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
     * Adds bean bags to the store.
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
        //Adds an empty string as a parameter and Passes information to other addBeanBags method.
        this.addBeanBags(num, manufacturer, name, id, year, month, "");
    }

    /**
     *
     *
     * @param num               number of bean bags added
     * @param manufacturer      bean bag manufacturer
     * @param name              bean bag name
     * @param id                ID of bean bag
     * @param year              year of manufacture
     * @param month             month of manufacture
     * @param information       free text detailing bean bag information
     * @throws IllegalNumberOfBeanBagsAddedException
     * @throws BeanBagMismatchException
     * @throws IllegalIDException
     * @throws InvalidMonthException
     */
    public void addBeanBags(int num, String manufacturer, String name, 
    String id, short year, byte month, String information)
    throws IllegalNumberOfBeanBagsAddedException, BeanBagMismatchException,
    IllegalIDException, InvalidMonthException {
        //Checks whether bean bag is currently registered in the stock.
        int indexOfMatch = this.getBeanBagsIndexById(id);
        if (indexOfMatch == -1) {
            //if bean bag does not exist, create new bean bag object
            BeanBagsStock newBeanBagsStock = new BeanBagsStock(id, name, manufacturer, year,
                                              month, num, information);
            //add  to list of beanbags
            this.beanbags.add((Object)newBeanBagsStock);
            beanBagTotal += num;
        } else {
            //if bean bag does exist
            BeanBagsStock existingBeanBagsStock = (BeanBagsStock)this.beanbags.get(indexOfMatch);
            //check that details match with the same bean bag ID
            boolean detailsMatch = existingBeanBagsStock.checkDetailsMatch(name, manufacturer,
                                                                      information);
            //If detail match increase quantity
            if (detailsMatch) {
                existingBeanBagsStock.increaseQuantity(num);
                existingBeanBagsStock.addDate(month, year);
                beanBagTotal += num;
            //If details do not match throw error
            } else {
                throw new BeanBagMismatchException("The ID entered does not match the details of " +
                        "the bean bag");
            }
        }
    }

    public void setBeanBagPrice(String id, int priceInPence) 
    throws InvalidPriceException, BeanBagIDNotRecognisedException, IllegalIDException {
        //if bean bag price entered is less than zero throws InvalidPriceException
        if (priceInPence < 1) {
            throw new InvalidPriceException("Price must be greater than 0");
        }
        //check that ID is valid, throw exception if not throws IllegalIdException
        checkId(id);
        int beanBagIndex = this.getBeanBagsIndexById(id);
        //check that the bean bag exists in stock if not throws BeanBagIDNotRecognisedException
        if (beanBagIndex == -1) {
            throw new BeanBagIDNotRecognisedException("Bean bag ID " + id + " is not recognised.");
        }
        //get the bean bag stock record from of the list of bean bags
        BeanBagsStock beanBagsStock = (BeanBagsStock) this.beanbags.get(beanBagIndex);

        // if bean bag has reservations update them if appropriate.
        if (beanBagsStock.getQuantityReserved() > 0) {
        updateReservationLowestPrice(id, priceInPence);
        }
        beanBagsStock.setPrice(priceInPence);
    }

    /**
     * Updates beanbag reservations lowest price if new price is lower.
     *
     * @param id
     * @param priceInPence
     */
    private void updateReservationLowestPrice(String id, int priceInPence){
        //gets reservation(s)
        ObjectArrayList matchingReservations = this.getReservationsByBeanBagId(id);
        //iterates through list containing reservation(s)
        for (int i = 0; i < matchingReservations.size(); i++) {
            BeanBagReservation matchingReservation = (BeanBagReservation) matchingReservations.get(i);
            //update lowest price if the new price is lower
            int lowPrice = matchingReservation.getLowestPrice();
            if (priceInPence < lowPrice) {
                matchingReservation.setLowestPrice(priceInPence);
            }
        }
    }

    /**
     * Sells bean bags.
     *
     * @param num           number of bean bags to be sold
     * @param id            ID of bean bags to be sold
     * @throws BeanBagNotInStockException
     * @throws InsufficientStockException
     * @throws IllegalNumberOfBeanBagsSoldException
     * @throws PriceNotSetException
     * @throws BeanBagIDNotRecognisedException
     * @throws IllegalIDException
     */
    public void sellBeanBags(int num, String id) throws BeanBagNotInStockException,
    InsufficientStockException, IllegalNumberOfBeanBagsSoldException,
    PriceNotSetException, BeanBagIDNotRecognisedException, IllegalIDException {
        //check id is valid, if not throw IllegalIDException
        checkId(id);
        //gets index of bean bag stock in beanbags list
        int indexOfMatch = this.getBeanBagsIndexById(id);
        //if the ID is not found in the beanbags list of bean bag stocks throws error
        if (indexOfMatch == -1) {
            //if the beanbag has been sold before throws BeanBagNotInStockException
            if (checkBeanBagSold(id)) {
                throw new BeanBagNotInStockException("Bean bag ID " + id + " is no longer in stock.");
            }
            //Else throw BeanBagIDNotRecognisedException
            throw new BeanBagIDNotRecognisedException("Bean bag ID " + id + " is not recognised");
        }
        // get matching bean bag stock object from beanbags list.
        BeanBagsStock beanbag = (BeanBagsStock)beanbags.get(indexOfMatch);
        //get quantity of reserved bean bags for that beanbags
        int numberUnreserved = beanbag.getQuantityUnreserved();

        // if number of beanbags attempting to be sold is less than one throws
        // IllegalNumberOfBeanBagsSoldException.
        if (num < 1) {
            throw new IllegalNumberOfBeanBagsSoldException("Cannot sell less than 1 bean bag");
        //checks there are enough unreserved beanbags in stock if not throws InsufficientStockException
        } else if (numberUnreserved < num) {
            throw new InsufficientStockException("Not enough unreserved stock.");
        }
        //decreases number of bean bags in that bean bag stock record.
        beanbag.decreaseQuantity(num);
        //getting the current price of the beanbag
        int price = beanbag.getPrice();
        int priceTotal = price*num
        //Creating a list and adding to bean bags sold list record

        //search i fin list get number increase numebr
        int index = getSoldBeanBagIndexByID(id);

        //if Id is not in list create new Object array and add
       if(index==-1){
           Object[] beanBagSold = {id, num, priceTotal};
           soldBeanBags.add(beanBagSold);
       //if ID is in list add to values in list.
       }else{
           //Get object array from sold bean bags array with matching ID
           Object[] soldBeanBagRecord = (Object[])soldBeanBags.get(index);
           //Update Total price of sold beanbags
           int recordPrice = (int)soldBeanBagRecord[2] + priceTotal;
           soldBeanBagRecord[2] = recordPrice;
           //Update total number of sold beanbags
           int recordNum = (int)soldBeanBagRecord[1] + num;
           soldBeanBagRecord[1] = recordNum;


        }
        //updating store fields numberBeanBagsSold, beanBagTotal, totalPriceSoldBeanBags.
        numberBeanBagsSold += num;
        beanBagTotal -= num;
        totalPriceSoldBeanBags += (priceTotal);

    }

    /**
     * Sells bean bags by reservation number.
     * @param reservationNumber           unique reservation number used to find
     *                                    beanbag(s) to be sold
     * @throws ReservationNumberNotRecognisedException  error thrown if reservation number is not
     * available in reservations list.
     */
    public void sellBeanBags(int reservationNumber)
            throws ReservationNumberNotRecognisedException {
        int matchingReservationIndex = getReservationByReservationNumber(reservationNumber);
        //if no reservation exists with that ID throws ReservationNumberNotRecognisedException
        if (matchingReservationIndex==-1){
            throw new ReservationNumberNotRecognisedException("Reservation number is not recognised.");
        }
        //Get bean bag reservation
        BeanBagReservation matchingReservation = (BeanBagReservation) reservations.get(matchingReservationIndex);
        //getting fields id, num and price form reservation.
        String id = matchingReservation.getId();
        int num = matchingReservation.getNumberOfBeanbags();
        int price = matchingReservation.getLowestPrice();

        //remove quantity from the relevant bean bag stock quantity.
        int beanBagsIndex = this.getBeanBagsIndexById(id);
        BeanBagsStock beanBagStock = (BeanBagsStock)beanbags.get(beanBagsIndex);
        beanBagStock.decreaseQuantity(num);

        int priceTotal= num*price ;
        //updating store fields: numberBeanBagsSold, beanBagTotal, totalPriceSoldBeanBags,
        //beanBagReservedTotal
        numberBeanBagsSold += num;
        beanBagTotal -= num;
        totalPriceSoldBeanBags += (priceTotal);
        beanBagReservedTotal -= num;

        //search i fin list get number increase numebr
        int index = getSoldBeanBagIndexByID(id);
        //Creating a list and adding to bean bags sold list record
        //if Id is not in list create new Object array and add
        if(index==-1){
            Object[] beanBagSold = {id, num, priceTotal};
            soldBeanBags.add(beanBagSold);
            //if ID is in list add to values in list.
        }else {
            //Get object array from sold bean bags array with matching ID
            Object[] soldBeanBagRecord = (Object[]) soldBeanBags.get(index);
            //Update Total price of sold beanbags
            int recordPrice = (int) soldBeanBagRecord[2] + priceTotal;
            soldBeanBagRecord[2] = recordPrice;
            //Update total number of sold beanbags
            int recordNum = (int) soldBeanBagRecord[1] + num;
            soldBeanBagRecord[1] = recordNum;
        }

        //remove from reservations
        reservations.remove(matchingReservationIndex);

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
        int indexOfMatch = this.getBeanBagsIndexById(id);
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
            int beanBagIndex = getBeanBagsIndexById(id);
            BeanBagsStock beanbag = (BeanBagsStock)beanbags.get(beanBagIndex);
            beanbag.unreserve(quantity);
        }
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
        // Checks the Id is valid. If the ID isn't it throws an IllegalIDException.
        checkId(id);
        //checks that the ID exists.
        int indexOfMatch = this.getBeanBagsIndexById(id);
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
    private int getBeanBagsIndexById (String id) {
        for (int i = 0; i < beanbags.size(); i++) {
            BeanBagsStock beanbag = (BeanBagsStock)beanbags.get(i);
            if (beanbag.getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private int getSoldBeanBagIndexByID(String id){
        for (int i = 0; i <soldBeanBags.size(); i++) {
            Object[] soldBeanBagRecord = (Object[]) soldBeanBags.get(i);
            if (((String)soldBeanBagRecord[0]).equals(id)){
                return i;
            }
        }
        //reservation not found
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
