package beanbags;


/**
 * NegativeNumberOfBeanBagsReservedException.
 * 
 * @author Jonathan Fieldsend 
 * @version 1.0
 */
public class IllegalNumberOfBeanBagsReservedException extends Exception
{
    /**
     * Constructs an instance of the exception with no message
     */
    public IllegalNumberOfBeanBagsReservedException(){ }

    /**
     * Constructs an instance of the exception containing the message argument
     * 
     * @param message   message containing details regarding the exception cause
     */
    public IllegalNumberOfBeanBagsReservedException(String message){ 
        super(message);
    }
}
