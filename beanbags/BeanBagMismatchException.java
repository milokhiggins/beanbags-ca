package beanbags;


/**
 * BeanBagMismatchException.
 * 
 * @author Jonathan Fieldsend 
 * @version 1.0
 */
public class BeanBagMismatchException extends Exception
{   
    /**
     * Constructs an instance of the exception with no message
     */
    public BeanBagMismatchException(){ }

    /**
     * Constructs an instance of the exception containing the message argument
     * 
     * @param message   message containing details regarding the exception cause
     */
    public BeanBagMismatchException(String message){ 
        super(message);
    }
}
