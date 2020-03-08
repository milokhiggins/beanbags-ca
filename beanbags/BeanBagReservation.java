package beanbags;

import java.io.Serializable;

/**
 * BeanBagReservation class contains information about reservations.
 * @author SN690024245 & SN680046138
 */
public class BeanBagReservation implements Serializable {

    private String beanBagID;
    private int numberOfBeanbags;
    private int lowestPrice;
    private int reservationNumber;
    private static int idCounter = 1;

    /**
     * Creates a bean bag reservation.
     * @param beanBagID         ID of reserved bean bags
     * @param numberOfBeanbags  Number of bean bags in reservation
     * @param currentPrice      Price of bean bags when reservation was made
     */
    public BeanBagReservation(String beanBagID, int numberOfBeanbags, int currentPrice) {
        this.beanBagID = beanBagID;
        this.numberOfBeanbags = numberOfBeanbags;
        lowestPrice = currentPrice;
        reservationNumber = idCounter++;
    }

    /**
     * Access method for lowest price
     * @return lowest price
     */
    public int getLowestPrice() { return lowestPrice; }

    /**
     * Setter method for lowest price
     * @param price new lowest price
     */
    public void setLowestPrice(int price) {
        this.lowestPrice = price;

    }

    /**
     * Access method for number of bean bags reserved
     * @return number of bean bags reserved
     */
    public int getQuantity() {return numberOfBeanbags; }

    /**
     * Access method for reservation number
     * @return reservation number
     */
    public int getReservationNumber() { return reservationNumber; }

    /**
     * Getter method to obtain reservation's bean bag Id.
     * @return bean bag's Id.
     */
    public String getId(){ return beanBagID; }

    /**
     * Setter method for bean bag ID
     * @param id bean bag ID
     */
    public void setId(String id){
        beanBagID = id;
    }
}