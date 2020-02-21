package beanbags;


/**
 * IllegalIDException.
 *  
 * @author Jonathan Fieldsend 
 * @version 1.0
 */
public class IllegalIDException extends Exception
{
    /**
     * Constructs an instance of the exception with no message
     */
    public IllegalIDException(){ }

    /**
     * Constructs an instance of the exception containing the message argument
     * 
     * @param message   message containing details regarding the exception cause
     */
    public IllegalIDException(String message){ 
        super(message);
    }
}
