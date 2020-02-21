package beanbags;


/**
 * PriceNotSetException.
 * 
 * @author Jonathan Fieldsend 
 * @version 1.0
 */
public class PriceNotSetException extends Exception
{
    /**
     * Constructs an instance of the exception with no message
     */
    public PriceNotSetException(){ }

    /**
     * Constructs an instance of the exception containing the message argument
     * 
     * @param message   message containing details regarding the exception cause
     */
    public PriceNotSetException(String message){ 
        super(message);
    }
}
