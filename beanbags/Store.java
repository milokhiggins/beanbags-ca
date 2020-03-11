package beanbags;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

/**
 * Store implements the BeanBagStore interface.
 * 
 * @author SN690024245 & SN680046138
 * @version 1.0
 */
public class Store implements BeanBagStore, java.io.Serializable
{
    public ObjectArrayList beanbags = new ObjectArrayList();
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
     * @throws IllegalNumberOfBeanBagsAddedException Number of bean bags added is less than 1
     * @throws BeanBagMismatchException              Details do not match existing bean bag details
     * @throws IllegalIDException                    ID is invalid (not a positive, 8 digit 
     * hexadecimal number)
     * @throws InvalidMonthException                 Month is not valid (a number between 1 and 12)
     */

    public void addBeanBags(int num, String manufacturer, String name, 
    String id, short year, byte month)
    throws IllegalNumberOfBeanBagsAddedException, BeanBagMismatchException,
    IllegalIDException, InvalidMonthException {
        //Adds an empty string as a parameter and Passes information to other addBeanBags method.
        this.addBeanBags(num, manufacturer, name, id, year, month, "");
    }

    /**
     * Adds bean bags to the store.
     *
     * @param num               number of bean bags added
     * @param manufacturer      bean bag manufacturer
     * @param name              bean bag name
     * @param id                ID of bean bag
     * @param year              year of manufacture
     * @param month             month of manufacture
     * @param information       free text detailing bean bag information
     * @throws IllegalNumberOfBeanBagsAddedException Number of bean bags added is less than 1
     * @throws BeanBagMismatchException              Details do not match existing bean bag details
     * @throws IllegalIDException                    ID is invalid (not a positive, 8 digit
     * hexadecimal number)
     * @throws InvalidMonthException                 Month is not valid (a number between 1 and 12)
     */
    public void addBeanBags(int num, String manufacturer, String name, 
    String id, short year, byte month, String information)
    throws IllegalNumberOfBeanBagsAddedException, BeanBagMismatchException,
    IllegalIDException, InvalidMonthException {
        //checks Id is valid (throws IllegalIDException)
        BeanBags.checkId(id);
        //Checks num is valid
        if (num <= 0) {
            throw new IllegalNumberOfBeanBagsAddedException("Cannot add less than 1 bean bag");
        }
        //Checks whether bean bag is currently registered in the stock.
        int indexOfMatch = this.getBeanBagsIndexById(id);
        if (indexOfMatch == -1) {
            //if bean bag does not exist, create new bean bag object
            //may throw IllegalNumberOfBeanBagsAddedException and InvalidMonthException
            BeanBagsStock newBeanBagsStock = new BeanBagsStock(id, name, manufacturer, year,
                                              month, num, information);
            //add  to list of beanbags
            this.beanbags.add(newBeanBagsStock);
            beanBagTotal += num;

            assert newBeanBagsStock.getQuantity() > 0 : "cannot add zero beanbags";
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

            assert existingBeanBagsStock.getQuantity() > 0 : "cannot add zero beanbags";
        }
    }

    /**
     * Sets the price of a bean bag.
     * @param id                ID of bean bags
     * @param priceInPence      bean bag price in pence
     * @throws InvalidPriceException            Price is not valid (less than 1)
     * @throws BeanBagIDNotRecognisedException  ID is not recognised
     * @throws IllegalIDException               ID is not valid
     */
    public void setBeanBagPrice(String id, int priceInPence) 
    throws InvalidPriceException, BeanBagIDNotRecognisedException, IllegalIDException {
        //check that ID is valid, throw exception if not throws IllegalIdException
        BeanBags.checkId(id);
        int beanBagIndex = this.getBeanBagsIndexById(id);
        //check that the bean bag exists in stock if not throws BeanBagIDNotRecognisedException
        if (beanBagIndex == -1) {
            throw new BeanBagIDNotRecognisedException("Bean bag ID " + id + " is not recognised.");
        }
        //get the bean bag stock record from of the list of bean bags
        BeanBagsStock beanBagsStock = (BeanBagsStock) this.beanbags.get(beanBagIndex);

        //throws InvalidPriceException if price is less than 1
        beanBagsStock.setPrice(priceInPence);
        // if bean bag has reservations update them if appropriate.
        if (beanBagsStock.getQuantityReserved() > 0) {
        updateReservationLowestPrice(id, priceInPence);
        }

        try {
            assert beanBagsStock.getPrice() > 0 : "Price must be greater than 0";
        } catch(PriceNotSetException e) {
            assert false : "this should not happen; price has been set so PriceNotSetException " +
                    "should not be thrown";
        }
    }

    /**
     * Updates beanbag reservations lowest price if new price is lower.
     *
     * @param id            ID of bean bag
     * @param priceInPence  Price to set reservation price to 
     */
    private void updateReservationLowestPrice(String id, int priceInPence){
        //gets reservation(s)
        ObjectArrayList matchingReservations = this.getReservationsByBeanBagId(id);
        //iterates through list containing reservation(s)
        for (int i = 0; i < matchingReservations.size(); i++) {
            BeanBagReservation matchingReservation =
                    (BeanBagReservation) matchingReservations.get(i);
            //Call function that will check and update the price if it i lower.
            matchingReservation.setLowestPrice(priceInPence);
        }
    }

    /**
     * Sells bean bags.
     *
     * @param num           number of bean bags to be sold
     * @param id            ID of bean bags to be sold
     * @throws BeanBagNotInStockException           Bean bag is not in stock
     * @throws InsufficientStockException           There is insufficient stock to make the sale
     * @throws IllegalNumberOfBeanBagsSoldException Number of bean bags being sold is less than 1
     * @throws PriceNotSetException                 Price of bean bag has not been set
     * @throws BeanBagIDNotRecognisedException      ID is not recognised
     * @throws IllegalIDException                   ID is not valid
     */
    public void sellBeanBags(int num, String id) throws BeanBagNotInStockException,
    InsufficientStockException, IllegalNumberOfBeanBagsSoldException,
    PriceNotSetException, BeanBagIDNotRecognisedException, IllegalIDException {
        //check id is valid, if not throw IllegalIDException
        BeanBags.checkId(id);
        //gets index of bean bag stock in beanbags list
        int indexOfMatch = this.getBeanBagsIndexById(id);
        //if the ID is not found in the beanbags list of bean bag stocks throws error
        if (indexOfMatch == -1) {
            //if the beanbag has been sold before throws BeanBagNotInStockException
            if (checkBeanBagSold(id)) {
                throw new BeanBagNotInStockException("Bean bag ID "+id+" is no longer in stock.");
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
        //checks there are enough unreserved beanbags in stock if not throws
        //InsufficientStockException
        } else if (numberUnreserved < num) {
            throw new InsufficientStockException("Not enough unreserved stock.");
        }
        //decreases number of bean bags in that bean bag stock record.
        boolean empty = beanbag.decreaseQuantity(num);
        if (empty) {
            //this bean bag is now sold out; remove from list
            beanbags.remove(indexOfMatch);
        }
        //getting the current price of the beanbag
        int price = beanbag.getPrice();
        int priceTotal = price*num;
        //Creating a list and adding to bean bags sold list record

        updateSaleTracking(id, num, priceTotal);

        assert getNumberOfSoldBeanBags() > 0 : "Must sell at least 1 bean bag";
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
        if (matchingReservationIndex == -1) {
            throw new ReservationNumberNotRecognisedException
                    ("Reservation number is not recognised.");
        }
        //Get bean bag reservation
        BeanBagReservation matchingReservation =
                (BeanBagReservation) reservations.get(matchingReservationIndex);
        //getting fields id, num and price form reservation.
        String id = matchingReservation.getId();
        int num = matchingReservation.getQuantity();
        int price = matchingReservation.getLowestPrice();

        //remove quantity from the relevant bean bag stock quantity.
        int beanBagsIndex = this.getBeanBagsIndexById(id);
        BeanBagsStock beanBagStock = (BeanBagsStock)beanbags.get(beanBagsIndex);
        beanBagStock.unreserve(num);
        boolean empty = beanBagStock.decreaseQuantity(num);
        if (empty) {
            //this bean bag is now completely sold out; so remove from list
            beanbags.remove(beanBagsIndex);
        }

        int priceTotal= num*price ;
        //updating store fields: beanBagReservedTotal
        beanBagReservedTotal -= num;

        updateSaleTracking(id, num, priceTotal);

        //remove from reservations
        reservations.remove(matchingReservationIndex);

        assert getNumberOfSoldBeanBags() > 0 : "Must sell at least 1 bean bag";
    }

    /**
     * Update sale tracking with new sale information.
     * 
     * @param id         id of sold bean bags
     * @param num        number of sold bean bags
     * @param totalPrice total price of sale
     */
    private void updateSaleTracking(String id, int num, int totalPrice) {
        //search if in list get number increase number
        int index = getSoldBeanBagIndexByID(id);
        //Creating a list and adding to bean bags sold list record
        //if Id is not in list create new Object array and add
        if(index==-1){
            Object[] beanBagSold = {id, num, totalPrice};
            soldBeanBags.add(beanBagSold);
            //if ID is in list add to values in list.
        }else {
            //Get object array from sold bean bags array with matching ID
            Object[] soldBeanBagRecord = (Object[]) soldBeanBags.get(index);
            //Update Total price of sold beanbags
            int recordPrice = (int) soldBeanBagRecord[2] + totalPrice;
            soldBeanBagRecord[2] = recordPrice;
            //Update total number of sold beanbags
            int recordNum = (int) soldBeanBagRecord[1] + num;
            soldBeanBagRecord[1] = recordNum;
        }
        //updating store fields: numberBeanBagsSold, beanBagTotal, totalPriceSoldBeanBags
        numberBeanBagsSold += num;
        beanBagTotal -= num;
        totalPriceSoldBeanBags += totalPrice;

        assert numberBeanBagsSold > 0 : "cannot sell 0 bean bags";
        assert beanBagTotal >= 0 : "cannot have less than 0 bean bags in stock";

    }

    /**
     * Reserve bean bags and return the reservation number
     * @param num           number of bean bags to be reserved
     * @param id            ID of bean bags to be reserved
     * @return Reservation number
     * @throws BeanBagNotInStockException               None of this type of bean bag is in stock
     * @throws InsufficientStockException               There is insufficient stock to make the 
     * reservation.
     * @throws IllegalNumberOfBeanBagsReservedException Trying to reserve less than 1 bean bag
     * @throws PriceNotSetException                     Trying to reserve a bean bag when it's price
     * has not been set
     * @throws BeanBagIDNotRecognisedException          ID does not match any existing bean bags
     * @throws IllegalIDException                       ID is invalid
     */
    public int reserveBeanBags(int num, String id) throws BeanBagNotInStockException,
    InsufficientStockException, IllegalNumberOfBeanBagsReservedException,
    PriceNotSetException, BeanBagIDNotRecognisedException, IllegalIDException {
       //Checks Bean bag ID is valid
        BeanBags.checkId(id);
        int indexOfMatch = this.getBeanBagsIndexById(id);
        if (indexOfMatch == -1) {
            //bean bag is not in stock
            if (checkBeanBagSold(id)) {
                //bean bag has been in stock before, but is currently not in stock
                throw new BeanBagNotInStockException("Bean bag ID "+id+" is no longer in stock.");
            } else {
                //no record of bean bag ID exists; id is not recognised
                throw new BeanBagIDNotRecognisedException("Bean bag ID "+id+" is not recognised");
            }
        } else {
            //beanbag ID is valid and bean bag is in stock
            //get bean bag from beanbags list
            BeanBagsStock beanbag = (BeanBagsStock) beanbags.get(indexOfMatch);
            int numberUnreserved = beanbag.getQuantityUnreserved();
            if (num < 1) {
                throw new IllegalNumberOfBeanBagsReservedException("Cannot sell less than 1 bean " +
                        "bag");
            } else if (numberUnreserved < num) {
                throw new InsufficientStockException("Not enough unreserved stock.");
            } else {
                //call beanbag.getPrice first as this may throw a PriceNotSetException
                //needs to be thrown before beanbag.reserve so that the state is unmodified
                BeanBagReservation reservation = new BeanBagReservation(id, num,
                        beanbag.getPrice());
                beanbag.reserve(num);
                reservations.add(reservation);
                beanBagReservedTotal += num;

                assert beanBagReservedTotal <= beanBagTotal : "Cannot reserve more bean bags " +
                        "than there are in stock";


                return reservation.getReservationNumber();
            }
        }
    }

    /**
     * Unreserve bean bags.
     * @param reservationNumber           reservation number
     * @throws ReservationNumberNotRecognisedException Reservation number does not match any 
     * existing reservation.
     */
    public void unreserveBeanBags(int reservationNumber)
    throws ReservationNumberNotRecognisedException {
        //checks for the reservation in reservation list.
        int indexOfMatch = getReservationByReservationNumber(reservationNumber);
        //is index returned is -1 reservation does not exist.
        if (indexOfMatch == -1) {
            throw new ReservationNumberNotRecognisedException("Reservation number not recognised");
        } else {
            //gets reservation
            BeanBagReservation reservation = (BeanBagReservation)reservations.get(indexOfMatch);
            //gets reservation details
            String id = reservation.getId();
            int quantity = reservation.getQuantity();
            //removes reservation from reservations list
            reservations.remove(indexOfMatch);
            //gets matching beanbag from beanbags list
            int beanBagIndex = getBeanBagsIndexById(id);
            //updates relevant bean bag and store and fields.
            BeanBagsStock beanbag = (BeanBagsStock)beanbags.get(beanBagIndex);
            beanbag.unreserve(quantity);
            beanBagReservedTotal -= quantity;

            assert beanBagReservedTotal >= 0 : "Cannot have less than 0 bean bags reserved";
            assert beanbag.getQuantityReserved() >= 0 : "Cannot have less than 0 beanbags reserved";
        }
    }


    /**
     * Writes contents of store to file.
     * @param filename      location of the file to be saved
     * @throws IOException Any error that occurs during file handling
     */
    public void saveStoreContents(String filename) throws IOException {
        FileOutputStream rawFileOut = new FileOutputStream(filename);
        BufferedOutputStream fileOut = new BufferedOutputStream(rawFileOut);
        //write entire Store to file
        ObjectArrayList storeContents = new ObjectArrayList();
        //write all attributes to list
        storeContents.add(beanbags);
        storeContents.add(reservations);
        storeContents.add(soldBeanBags);
        storeContents.add(beanBagTotal);
        storeContents.add(beanBagReservedTotal);
        storeContents.add(numberBeanBagsSold);
        storeContents.add(totalPriceSoldBeanBags);
        try (ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            //save list to file
            objectOut.writeObject(storeContents);
        }

    }

    /**
     * Read in contents of store from file and load that data into this store instance
     * @param filename      location of the file to be loaded
     * @throws IOException Any error that occurs during file handling
     * @throws ClassNotFoundException Store class
     */
    public void loadStoreContents(String filename) throws IOException,
    ClassNotFoundException {
        FileInputStream rawFileIn = new FileInputStream(filename);
        BufferedInputStream fileIn = new BufferedInputStream(rawFileIn);
        ObjectArrayList newContents;
        try (ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            //read in list of attributes
            Object obj = objectIn.readObject();
            if (obj instanceof ObjectArrayList) {
                //check if downcast can be done safely
                newContents = (ObjectArrayList) obj;
                //set all attributes to the newly loaded values
                beanbags = (ObjectArrayList) newContents.get(0);
                reservations = (ObjectArrayList) newContents.get(1);
                soldBeanBags = (ObjectArrayList) newContents.get(2);
                beanBagTotal = (int) newContents.get(3);
                beanBagReservedTotal = (int) newContents.get(4);
                numberBeanBagsSold = (int) newContents.get(5);
                totalPriceSoldBeanBags = (int) newContents.get(6);

                assert beanBagTotal >= 0 : "cannot have negative number of bean bags";
                assert beanBagReservedTotal >= 0 : "cannot have negative number of reservations";
                assert numberBeanBagsSold >= 0 : "cannot have negative number of sold bean bags";
                assert totalPriceSoldBeanBags >= 0: "cannot have negative total price";
            }
        }
    }

    /**
     * Access method for total number of bean bags in stock (including reservations).
     * @return Total number of bean bags in stock
     */
    public int beanBagsInStock() { return beanBagTotal; }

    /**
     * Get number of bean bags in stock of a particular ID
     * @param id            ID of bean bags
     * @return number of matching bean bags in stock
     * @throws BeanBagIDNotRecognisedException  No bean bags of this ID exist in stock
     * @throws IllegalIDException               ID is invalid
     */
    public int beanBagsInStock(String id) throws BeanBagIDNotRecognisedException,
            IllegalIDException {
        //check ID is valid; throws IllegalIDException if not
        BeanBags.checkId(id);
        int beanbagIndex = getBeanBagsIndexById(id);
        if (beanbagIndex == -1) {
            //no bean bags match this ID
            throw new BeanBagIDNotRecognisedException("Beanbag ID not recognised");
        }
        //get bean bag stock object from list of beanbags
        BeanBagsStock beanbag = (BeanBagsStock)beanbags.get(beanbagIndex);
        //return number in stock
        return beanbag.getQuantity();
    }

    /**
     * Access method for total number of reserved bean bags.
     * @return Total number of reserved bean bags
     */
    public int reservedBeanBagsInStock() { return beanBagReservedTotal; }

    /**
     * Access method for total number of different types of bean bags in stock.
     * @return number of different bean bags in stock
     */
    public int getNumberOfDifferentBeanBagsInStock() { return beanbags.size(); }

    /**
     * Access method for total number of sold bean bags.
     * @return number of sold bean bags
     */
    public int getNumberOfSoldBeanBags() { return numberBeanBagsSold; }

    /**
     * Get the number of sold bean bags of a particular ID
     * @param id ID of bean bags
     * @return number of sold bean bags
     * @throws BeanBagIDNotRecognisedException No bean bag matches the given ID
     * @throws IllegalIDException              ID is invalid
     */
    public int getNumberOfSoldBeanBags(String id) throws
            BeanBagIDNotRecognisedException, IllegalIDException {
        Object[] record = getSoldBeanBagRecord(id);
        return (int)record[1];
    }

    /**
     * Get a sale by it's ID
     * @param id id of bean bag
     * @return Record of sale: [String id, int totalSold, int totalPrice]
     * @throws IllegalIDException               ID is invalid
     * @throws BeanBagIDNotRecognisedException  No bean bag matching this ID
     */
    private Object[] getSoldBeanBagRecord(String id) throws IllegalIDException,
            BeanBagIDNotRecognisedException {
        // Checks the Id is valid. If the ID isn't it throws an IllegalIDException.
        BeanBags.checkId(id);
        //checks that the ID exists in soldBeanBags list
        boolean sold = this.checkBeanBagSold(id);
        if (!sold) {
            //check that ID exists in bean bag list
            int indexOfMatch = this.getBeanBagsIndexById(id);
            if (indexOfMatch == -1) {
                throw new BeanBagIDNotRecognisedException("Bean bag ID " + id +
                        " is not recognised");
            }
            //no record of sale in soldBeanBags means number sold and total price is zero
            return new Object[]{id, 0, 0};
        }
        for (int i = 0; i < soldBeanBags.size(); i++) {
            //sale = {id, totalNumber, totalPrice}
            Object[] sale = (Object[]) soldBeanBags.get(i);
            String beanBagId = (String) sale[0];
            if (beanBagId.equals(id)) {
                return sale;
            }
        }
        //this section of code should never execute under normal circumstances
        assert false : "Invariant (soldBeanBags) changed during execution!";
        return null;
    }


    /**
     * Access method for the total price of sold bean bagg
     * @return total price of sold bean bags
     */
    public int getTotalPriceOfSoldBeanBags() { return totalPriceSoldBeanBags; }

    /**
     * Get the total price of sold bean bags for a particular ID
     * @param id ID of bean bags
     * @return total price of sold bean bags
     * @throws BeanBagIDNotRecognisedException No bean bag matches the given id
     * @throws IllegalIDException              id is invalid
     */
    public int getTotalPriceOfSoldBeanBags(String id) throws
    BeanBagIDNotRecognisedException, IllegalIDException {
        Object[] record =  getSoldBeanBagRecord(id);
        return (int)record[2];
    }

    /**
     * Access method for the total price of reserved bean bags
     * @return the total price of reserved bean bags
     */
    public int getTotalPriceOfReservedBeanBags() {
        int total = 0;
        //add up total price for every reservation in list
        for (int i = 0; i < reservations.size(); i++) {
            BeanBagReservation reservation = (BeanBagReservation)reservations.get(i);
            int quantity = reservation.getQuantity();
            int price = reservation.getLowestPrice();
            total += (quantity * price);
        }
        return total;
    }

    /**
     * Return details (free text component) of bean bag with matching ID
     * @param id ID of bean bag
     * @return free text component of bean bag
     * @throws BeanBagIDNotRecognisedException ID is valid but not recognised
     * @throws IllegalIDException ID is invalid
     */
    public String getBeanBagDetails(String id) throws
    BeanBagIDNotRecognisedException, IllegalIDException {
        //check ID is valid; throws IllegalIDException if not
        BeanBags.checkId(id);
        //get index of beanbag
        int beanbagIndex = getBeanBagsIndexById(id);
        if (beanbagIndex == -1) {
            //no matching beanbag in list; ID is not recognised
            throw new BeanBagIDNotRecognisedException("Bean bag ID not recognised");
        }
        //get beanbag from list
        BeanBagsStock beanbag = (BeanBagsStock)beanbags.get(beanbagIndex);
        //get details from beanbag
        ObjectArrayList details = beanbag.getDetails();
        //details returns a list of name, manufacturer and additional text
        //so return last (3rd) element of this list
        return (String)details.get(2);
    }

    /**
     * Empty contents of store and reset all sale and cost tracking.
     */
    public void empty() {
        beanbags = new ObjectArrayList();
        reservations = new ObjectArrayList();
        beanBagTotal = 0;
        beanBagReservedTotal = 0;
        soldBeanBags = new ObjectArrayList();
        numberBeanBagsSold = 0;
        totalPriceSoldBeanBags = 0;
    }

    /**
     * Reset tracking of sale and costs.
     */
    public void resetSaleAndCostTracking() {
        numberBeanBagsSold = 0;
        soldBeanBags = new ObjectArrayList();
        totalPriceSoldBeanBags = 0;
    }

    /**
     *
     * @param oldId             old ID of bean bags
     * @param replacementId     replacement ID of bean bags
     * @throws BeanBagIDNotRecognisedException ID does not match any existing IDs
     * @throws IllegalIDException              ID is invalid
     */
    public void replace(String oldId, String replacementId) 
    throws BeanBagIDNotRecognisedException, IllegalIDException {
        //check ids are valid; throws IllegalIDException if it isn't
        BeanBags.checkId(oldId);
        BeanBags.checkId(replacementId);

        int beanbagIndex = getBeanBagsIndexById(oldId);
        boolean sold = checkBeanBagSold(oldId);
        //if beanbag ID does not exist in list of beanbags or list of sold bean bags then the ID
        //is not recognised
        if (beanbagIndex == -1 && !sold) {
            throw new BeanBagIDNotRecognisedException("Bean bag ID not recognised");
        }

        //if bean bag is in stock, replace ID
        if (beanbagIndex != -1) {
            BeanBagsStock beanbag = (BeanBagsStock)beanbags.get(beanbagIndex);
            beanbag.setId(replacementId);
            //if bean bag is reserved, replace ID for all reservations matching that ID
            //if bean bag is not reserved, matchingReservations will be an empty list
            //so for loop will not be executed
            ObjectArrayList matchingReservations = getReservationsByBeanBagId(oldId);
            for (int i = 0; i < matchingReservations.size(); i++) {
                BeanBagReservation reservation = (BeanBagReservation)matchingReservations.get(i);
                reservation.setId(replacementId);
            }
        }
        //if beanbag has been sold, update sale records with new id
        if (sold) {
            for (int i = 0; i < soldBeanBags.size(); i++) {
                //get sale record from sold beanbags list
                Object[] beanBagSaleRecord = (Object[])soldBeanBags.get(i);
                //get id from record (first item in record)
                String id = (String)beanBagSaleRecord[0];
                //if id matches oldId replace with new ID
                if (id.equals(oldId)) {
                    beanBagSaleRecord[0] = replacementId;
                }
            }
        }
        assert !checkBeanBagSold(oldId) : "oldID should not exist on system";
        assert getBeanBagsIndexById(oldId) == -1 : "oldID should not exist on system";
        assert getReservationsByBeanBagId(oldId).size() == 0 : "oldID should not exist on system";
    }

    /**
     * Get the index of the bean bag if it is in stock, otherwise returns -1
     * @param id id of bean bag
     * @return index of bean bag, or -1 if no matching bean bag.
     */
    private int getBeanBagsIndexById (String id) {
        //search list of beanbags
        for (int i = 0; i < beanbags.size(); i++) {
            BeanBagsStock beanbag = (BeanBagsStock)beanbags.get(i);
            if (beanbag.getId().equals(id)) {
                //return index
                return i;
            }
        }
        //bean bag not found; return -1
        return -1;
    }

    /**
     * Get the index of a bean bag sale record. Returns -1 if no matching sale exists.
     * @param id bean bag id
     * @return index of sale record or -1 if no match found
     */
    private int getSoldBeanBagIndexByID(String id){
        //search list of sales
        for (int i = 0; i < soldBeanBags.size(); i++) {
            Object[] soldBeanBagRecord = (Object[]) soldBeanBags.get(i);
            //sale = {id, numberSold, totalPrice}
            if (((String)soldBeanBagRecord[0]).equals(id)){
                return i;
            }
        }
        //reservation not found
        return -1;
    }

    /**
     * Get a the index of a reservation. Returns -1 if no matching reservation is found.
     * @param reservationNumber reservation number
     * @return index of reservation, or -1 if no matching reservation found
     */
    private int getReservationByReservationNumber(int reservationNumber) {
        //iterates through list of reservations
        for (int i = 0; i < reservations.size(); i++) {
            BeanBagReservation reservation = (BeanBagReservation)reservations.get(i);
            //if reservation contains matching reference number returns reservation index.
            if (reservation.getReservationNumber() == reservationNumber) {
                return i;
            }
        }
        //if reservation not found returns -1
        return -1;
    }

    /**
     * Check if a bean bag has been sold.
     * @param id id of bean bag
     * @return true if bean bag id has been sold before, false otherwise.
     */
    private boolean checkBeanBagSold (String id) {
        //search for id in soldBeanBags list
        for (int i = 0; i < soldBeanBags.size(); i++) {
            Object[] beanbag = (Object[])soldBeanBags.get(i);
            if (((String)beanbag[0]).equals(id)) {
                return true;
            }
        }
        //not found; return false
        return false;
    }

    /**
     * Get list of reservations which match the given bean bag ID
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
