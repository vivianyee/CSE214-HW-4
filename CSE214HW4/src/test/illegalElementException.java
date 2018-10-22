package test;

public class illegalElementException extends Exception {
	public illegalElementException()
    {  //Default message
        super("Invalid element used as method parameter.");
    }

    public illegalElementException(String message)
    {   //Passed message
        super(message);
    }	
}
