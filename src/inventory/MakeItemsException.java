package inventory;

@SuppressWarnings("serial")
public class MakeItemsException extends RuntimeException
{
    public static enum Type { BAD_SYNTAX, EXTRA_CHARACTERS, UNKNOWN_COMMAND };
    private Type myType;

    /**
     * Create exception with given message
     *  
     * @param message explanation of problem
     */
    public MakeItemsException (String message)
    {
        this(message, Type.BAD_SYNTAX);
    }
    
    public MakeItemsException (String message, Type type)
    {
        super(message);
        myType = type;
    }
    
    public Type getType()
    {
        return myType;
    }
}