package beanbags;


/**
 * IllegalNumberOfBeanBagsSoldException.
 * 
 * @author Jonathan Fieldsend 
 * @version 1.0
 */
public class IllegalNumberOfBeanBagsSoldException extends Exception
{
    /**
     * Constructs an instance of the exception with no message
     */
    public IllegalNumberOfBeanBagsSoldException(){ }

    /**
     * Constructs an instance of the exception containing the message argument
     * 
     * @param message   message containing details regarding the exception cause
     */
    public IllegalNumberOfBeanBagsSoldException(String message){ 
        super(message);
    }
}
