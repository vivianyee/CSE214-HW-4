package test;
/**
 * 
 * The <code></code> class 
 * 
 * @author Vivian Yee
 * 		e-mail: vivian.yee@stonybrook.edu
 * 		Stonybrook ID: 112145534
 */
public class IllegalElementException extends Exception {
	public IllegalElementException()
    {  //Default message
        super("Invalid element used as method parameter.");
    }

    public IllegalElementException(String message)
    {   //Passed message
        super(message);
    }	
}
