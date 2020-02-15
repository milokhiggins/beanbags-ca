package beanbags;


/**
 * InvalidMonthException.
 * 
 * @author Jonathan Fieldsend 
 * @version 1.0
 */
public class InvalidMonthException extends Exception
{
    /**
     * Constructs an instance of the exception with no message
     */
    public InvalidMonthException(){ }

    /**
     * Constructs an instance of the exception containing the message argument
     * 
     * @param message   message containing details regarding the exception cause
     */
    public InvalidMonthException(String message){ 
        super(message);
    }
}
