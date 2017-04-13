package com.regressionelineare.riccardoamadio.regressionelineare;

/**
 * Created by riccardoamadio on 04/04/17.
 */

public   class MyException extends  Exception{
    public MyException() {}

    public MyException(String message){
        super(message);
    }

    public MyException (Throwable cause) {
        super (cause);
    }

    public MyException (String message, Throwable cause) {
        super (message, cause);
    }
}
