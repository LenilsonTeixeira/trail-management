package br.com.boraviajar.trailmanagement.exception;

public class TrailException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TrailException(String message){
        super(message);
    }

    public TrailException(String message , Throwable cause){
        super(message,cause);
    }
}
