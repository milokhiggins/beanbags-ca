package beanbags;

public class BeanBagReservation {

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
    public int getLowestPrice() { return lowestPrice; }

    public void setLowestPrice(int price) {
        this.lowestPrice = price;

    }

    /**
     * Getter method to obtain reservation's bean bag Id.
     * @return bean bag's Id.
     */
    public String getId(){ return beanBagID; }

    public int getNumberOfBeanbags() { return numberOfBeanbags; }
}