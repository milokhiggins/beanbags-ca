package beanbags;

/**
 * BeanBags ...
 *
 * @author SN690024245, SN680046138
 * @version 1.0
 */
public class BeanBags {
    private String id;
    private String name;
    private String manufacturer;
    private short yearManufactured;
    private byte monthManufactured;
    private String additionalText;
    private int quantity;
    private int quantityUnreserved;
    private int price;

    /**
     *
     * @param id                8 digit hexadecimal ID of bean bag
     * @param name              bean bag name
     * @param manufacturer      bean bag manufacturer
     * @param yearManufactured  year of manufacture
     * @param monthManufactured month of manufacture
     * @param quantity          number of bean bags
     */
    public BeanBags (String id, String name, String manufacturer, short yearManufactured,
                     byte monthManufactured, int quantity) throws IllegalIDException,
                     InvalidMonthException, IllegalNumberOfBeanBagsAddedException {
        this(id, name, manufacturer, yearManufactured, monthManufactured, quantity, "");
    }

    /**
     *
     * @param id                8 digit hexadecimal ID of bean bag
     * @param name              bean bag name
     * @param manufacturer      bean bag manufacturer
     * @param yearManufactured  year of manufacture
     * @param monthManufactured month of manufacture
     * @param quantity          number of bean bags
     */
    public BeanBags(String id, String name, String manufacturer, short yearManufactured,
                    byte monthManufactured, int quantity, String additionalText) throws
                    IllegalIDException, InvalidMonthException, IllegalNumberOfBeanBagsAddedException
                    {

        BeanBags.checkId(id);
        if (quantity<=0){
            throw new IllegalNumberOfBeanBagsAddedException("Must add at least one beanbag");
        }
        if (monthManufactured < 1 || monthManufactured > 13){
            throw new InvalidMonthException("Month has to be a number between 1 and 12");
        }

        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.yearManufactured = yearManufactured;
        this.monthManufactured = monthManufactured;
        this.quantity = quantity;
        this.quantityUnreserved = quantity;
        this.additionalText = additionalText;
    }

    /**
     *  Getter method for ID.
     * @return ID of bean bag
     */
    public String getId() {
        return id;
    }

    /**
     *
     */
    public void setId(String id) throws IllegalIDException {
        checkId(id);
        this.id = id;
    }

    /**
     *  Getter method for quantity.
     * @return number of bean bags
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Increase quantity of bean bag in stock.
     * @param num Number to increase quantity by
     */
    public void increaseQuantity(int num) {
        quantity += num;
        quantityUnreserved += num;
    }

    /**
     * Decrease quantity of bean bag in stock.
     * @param num Number to decrease quantity by
     * @return Returns true if there are none left, otherwise returns false
     * @throws InsufficientStockException not enough stock
     */
    public boolean decreaseQuantity(int num) throws InsufficientStockException {
        if (num > quantity) {
            throw new InsufficientStockException("Not enough bean bags of ID " + id + " in stock.");
        }
        quantity -= num;
        if (quantity == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void setPrice(int price) throws InvalidPriceException {

    }

    /**
     * Reserve bean bags.
     * @param num Number to reserve.
     * @throws IllegalNumberOfBeanBagsReservedException not enough bean bags unreserved
     */
    public void reserve(int num) throws IllegalNumberOfBeanBagsReservedException {
        if (num > quantityUnreserved) {
            throw new IllegalNumberOfBeanBagsReservedException("Not enough bean bags of ID " + id + " in stock.");
        }
        quantityUnreserved -= num;
    }

    /**
     * Get number of reserved bean bags.
     * @return number of reserved bean bags
     */
    public int getQuantityReserved() {
        return quantity - quantityUnreserved;
    }
    /**
     * Gets bean bag's details.
     * @return List of name, manufacturer and free text component
     */
    public ObjectArrayList getDetails() {
        ObjectArrayList details = new ObjectArrayList();
        details.add((Object) name);
        details.add((Object) manufacturer);
        details.add((Object) additionalText);

        return details;
    }

    /**
     * Setter method for free text component
     * @param text text to set free text component equal too
     */
    public void setAdditionalText(String text) {
        additionalText = text;
    }

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



}
