package beanbags;


/**
 * IllegalNumberOfBeanBagsAddedException.
 * 
 * @author Jonathan Fieldsend 
 * @version 1.0
 */
public class IllegalNumberOfBeanBagsAddedException extends Exception
{    
    /**
     * Constructs an instance of the exception with no message
     */
    public IllegalNumberOfBeanBagsAddedException(){ }

    /**
     * Constructs an instance of the exception containing the message argument
     * 
     * @param message   message containing details regarding the exception cause
     */
    public IllegalNumberOfBeanBagsAddedException(String message){ 
        super(message);
    }
}
