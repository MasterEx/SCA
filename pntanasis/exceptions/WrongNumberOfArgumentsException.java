// MIT License - Copyright (c) 2009 Periklis Ntanasis

/**
 * \package pntanasis.exceptions
 * This package contains all the exceptions (written by Periklis Ntanasis)
 */
package pntanasis.exceptions;

/**
 *
 * @author periklis
 *
 * \class WrongNumberOfArgumentsException
 *
 * A Wrong Number of Arguments Exception class. An exception caused when the number
 * of arguments given to a program is unexpected.
 * (i.e. We may want even number of arguments and the input is odd.)
 */
public class WrongNumberOfArgumentsException extends Exception
{
    /*
     * Empty constructor.
     */
    public WrongNumberOfArgumentsException() { }

    /*
     * A constructor that receives a mesage. The message is displayed when
     * the exception is throwed.
     */
    public WrongNumberOfArgumentsException(String msg)
    {
        super(msg);
    }
}
