package beanbags;


/**
 * BeanBagIDNotRecognisedException.
 * 
 * @author Jonathan Fieldsend 
 * @version 1.0
 */
public class BeanBagIDNotRecognisedException extends Exception
{
    /**
     * Constructs an instance of the exception with no message
     */
    public BeanBagIDNotRecognisedException(){ }

    /**
     * Constructs an instance of the exception containing the message argument
     * 
     * @param message   message containing details regarding the exception cause
     */
    public BeanBagIDNotRecognisedException(String message){ 
        super(message);
    }
}
