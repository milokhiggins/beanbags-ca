package beanbags;

/**
 * BeanBags ...
 *
 * @author SN690024245, SN680046138
 * @version 1.0
 */
public class BeanBagsStock extends BeanBags {
    private ObjectArrayList manufactureDates = new ObjectArrayList();
    private String additionalText;
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
     * @param additionalText    description or additional details about the bean bag
     */
    public BeanBagsStock(String id, String name, String manufacturer, short yearManufactured,
                     byte monthManufactured, int quantity, String additionalText) throws IllegalIDException,
                     InvalidMonthException, IllegalNumberOfBeanBagsAddedException {
        this(id, name, manufacturer, yearManufactured, monthManufactured, quantity);
        this.additionalText = additionalText;
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
    public BeanBagsStock(String id, String name, String manufacturer, short yearManufactured,
                    byte monthManufactured, int quantity) throws
                    IllegalIDException, InvalidMonthException, IllegalNumberOfBeanBagsAddedException
    {
        super(id, name, manufacturer, quantity);
        //check id is valid, if not throw IllegalIDException
        BeanBagsStock.checkId(id);
        if (quantity<=0){
            throw new IllegalNumberOfBeanBagsAddedException("Must add at least one beanbag");
        }
        if (monthManufactured < 1 || monthManufactured > 12){
            throw new InvalidMonthException("Month has to be a number between 1 and 12");
        }
        short[] newDate = {monthManufactured, yearManufactured};
        manufactureDates.add(newDate);
        this.quantityUnreserved = quantity;
    }

    /**
     *  Getter method for quantity.
     * @return number of bean bags
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Getter method for quantity unreserved.
     * @return number of unreserved bean bags
     */
    public int getQuantityUnreserved() {
        return quantityUnreserved;
    }

    /**
     * Increase quantity of bean bag in stock.
     * @param num Number to increase quantity by
     */
    public void increaseQuantity(int num) throws IllegalNumberOfBeanBagsAddedException {
        if (num < 1) {
            throw new IllegalNumberOfBeanBagsAddedException("Cannot less than 1  bean bag");
        }
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

    public int getPrice() throws PriceNotSetException {
        if (price == 0) {
            throw new PriceNotSetException("Price has not been set");
        }
        return price;
    }

    public void setPrice(int price) throws InvalidPriceException {
        if (price < 1) {
            throw new InvalidPriceException("Price must be greater than 0 pence");
        }
        this.price = price;
    }

    /**
     * Reserve bean bags.
     * @param num Number to reserve.
     * @throws IllegalNumberOfBeanBagsReservedException not enough bean bags unreserved
     */
    public void reserve(int num) throws IllegalNumberOfBeanBagsReservedException {
        quantityUnreserved -= num;
    }

    public void unreserve(int num) {
        quantityUnreserved += num;
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


    /**
     * Add a date to list of dates, only if it is not already in the list.
     * @param month month of manufacture
     * @param year  year of manufacture
     */
    public void addDate(byte month, short year) throws InvalidMonthException {
        if (month < 1 || month > 12) {
            throw new InvalidMonthException("Month has to be a number between 1 and 12");
        }
        boolean duplicate = false;
        for(int i = 0; i < manufactureDates.size(); i++) {
            short[] date = (short[]) manufactureDates.get(i);
            if (date[0] == month && date[1] == year) {
                duplicate = true;
                //do not need to check any more of the dates; it is a duplicate
                break;

            }
        }
        if (!duplicate) {
            short[] newDate = {month, year};
            manufactureDates.add(newDate);
        }
    }

    public boolean checkDetailsMatch(String name, String manufacturer, String information) {
        if (this.name.equals(name) && this.manufacturer.equals(manufacturer)
                && this.additionalText.equals(information)) {
            return true;
        } else {
            return false;
        }
    }



}
