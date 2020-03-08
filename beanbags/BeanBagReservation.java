package beanbags;

import java.io.Serializable;

public class BeanBagReservation implements Serializable {

    private String beanBagID;
    private int numberOfBeanbags;
    private int lowestPrice;
    private int reservationNumber;
    private static int idCounter = 1;

    /**
     * Creates a bean bag reservation.
     * @param beanBagID
     * @param numberOfBeanbags
     * @param currentPrice
     */
    public BeanBagReservation(String beanBagID, int numberOfBeanbags, int currentPrice) {
        this.beanBagID = beanBagID;
        this.numberOfBeanbags = numberOfBeanbags;
        lowestPrice = currentPrice;
        reservationNumber = idCounter++;
    }

    /**
     *
     * @return
     */
    public int getLowestPrice() { return lowestPrice; }

    /**
     *
     * @param price
     */
    public void setLowestPrice(int price) {
        this.lowestPrice = price;

    }

    /**
     *
     * @return
     */
    public int getQuantity() {return numberOfBeanbags; }

    /**
     *
     * @return
     */
    public int getReservationNumber() { return reservationNumber; }

    /**
     * Getter method to obtain reservation's bean bag Id.
     * @return bean bag's Id.
     */
    public String getId(){ return beanBagID; }

    /**
     *
     * @param id
     */
    public void setId(String id){
        beanBagID = id;
    }

    /**
     *
     * @return
     */
    public int getNumberOfBeanbags() { return numberOfBeanbags; }
}