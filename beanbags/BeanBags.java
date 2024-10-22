package beanbags;

import java.io.Serializable;

/**
 * BeanBag class stores basic information about a bean bag.
 *
 * @author SN690024245 & SN680046138
 */
public class BeanBags implements Serializable {
    protected String id;
    protected String name;
    protected String manufacturer;
    protected int quantity;

    /**
     * Constructor method
     * @param id           id of bean bag
     * @param name         name of bean bag
     * @param manufacturer manufacturer of bean bag
     * @param quantity     number of bean bags
     */
    public BeanBags(String id, String name, String manufacturer, int quantity) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
    }

    /**
     *  Getter method for ID.
     * @return ID of bean bag
     */
    public String getId() {
        return id;
    }

    /**
     * Set ID to new ID.
     * @param id id to set
     * @throws IllegalIDException id is not valid
     */
    public void setId(String id) throws IllegalIDException {
        BeanBags.checkId(id);
        this.id = id;
    }

    /**
     * Check ID is valid
     * @param id id
     * @throws IllegalIDException ID is not valid; not a positive 8 digit hexadecimal number
     */
    static void checkId(String id) throws IllegalIDException{
        long hexadecimalNumber;
        //  checks if the string is a hexadecimal number.
        try{
            hexadecimalNumber = Long.parseLong(id,16);
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
