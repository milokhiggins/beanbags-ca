package beanbags;


/**
 * ReservationNumberNotRecognisedException.
 * 
 * @author Jonathan Fieldsend 
 * @version 1.0
 */
public class ReservationNumberNotRecognisedException extends Exception
{
    /**
     * Constructs an instance of the exception with no message
     */
    public ReservationNumberNotRecognisedException(){ }

    /**
     * Constructs an instance of the exception containing the message argument
     * 
     * @param message   message containing details regarding the exception cause
     */
    public ReservationNumberNotRecognisedException(String message){ 
        super(message);
    }
}
