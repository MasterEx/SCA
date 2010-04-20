// MIT License - Copyright (c) 2009 Periklis Ntanasis

package pntanasis.exceptions;

/**
 *
 * @author periklis
 *
 * \class WrongSyntaxException
 *
 * A Wrong Syntax Exception. Unexpected syntax may cause this type of exception.
 */
public class WrongSyntaxException extends Exception{

    /*
     * Empty constructor.
     */
    public WrongSyntaxException()
    {
        super();
    }

    /*
     * A constructor that receives a mesage. The message is displayed when
     * the exception is throwed.
     */
    public WrongSyntaxException(String msg)
    {
        super(msg);
    }
}
