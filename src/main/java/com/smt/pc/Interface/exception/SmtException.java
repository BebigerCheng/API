
package com.smt.pc.Interface.exception;

/**
 * 项目共通异常
 * Created by LIJIKAI on 2015/5/11.
 */
public class SmtException extends Exception {

    private static final long serialVersionUID = 1L;

    public SmtException(String message) {
        super(message);
    }

    public SmtException(String message, Throwable cause) {
        super(message, cause);
    }

}
