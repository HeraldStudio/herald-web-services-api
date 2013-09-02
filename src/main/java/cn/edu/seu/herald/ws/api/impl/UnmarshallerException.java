package cn.edu.seu.herald.ws.api.impl;

public class UnmarshallerException extends Exception {

    public UnmarshallerException() {
        super("UnmarshallerException occurred.");
    }

    public UnmarshallerException(String msg) {
        super(msg);
    }

    public UnmarshallerException(Exception cause) {
        super("UnmarshallerException occurred.", cause);
    }

    public UnmarshallerException(String msg, Exception cause) {
        super(msg, cause);
    }
}
