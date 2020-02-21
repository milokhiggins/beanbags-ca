package beanbags;


/**
 * InvalidPriceException.
 * 
 * @author Jonathan Fieldsend 
 * @version 1.0
 */
public class InvalidPriceException extends Exception
{
    /**
     * Constructs an instance of the exception with no message
     */
    public InvalidPriceException(){ }

    /**
     * Constructs an instance of the exception containing the message argument
     * 
     * @param message   message containing details regarding the exception cause
     */
    public InvalidPriceException(String message){ 
        super(message);
    }
}
