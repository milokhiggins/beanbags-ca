package beanbags;


/**
 * BeanBagNotInStockException.
 * 
 * @author Jonathan Fieldsend 
 * @version 1.0
 */
public class BeanBagNotInStockException extends Exception
{
    /**
     * Constructs an instance of the exception with no message
     */
    public BeanBagNotInStockException(){ }

    /**
     * Constructs an instance of the exception containing the message argument
     * 
     * @param message   message containing details regarding the exception cause
     */
    public BeanBagNotInStockException(String message){ 
        super(message);
    }
}
