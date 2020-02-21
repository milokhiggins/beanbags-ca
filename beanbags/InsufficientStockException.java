package beanbags;


/**
 * InsufficientStockException.
 * 
 * @author Jonathan Fieldsend 
 * @version 1.0
 */
public class InsufficientStockException extends Exception
{
    /**
     * Constructs an instance of the exception with no message
     */
    public InsufficientStockException(){ }

    /**
     * Constructs an instance of the exception containing the message argument
     * 
     * @param message   message containing details regarding the exception cause
     */
    public InsufficientStockException(String message){ 
        super(message);
    }
}
