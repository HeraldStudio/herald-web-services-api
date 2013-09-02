package cn.edu.seu.herald.ws.api.util;

public class Assert {

    public static void notNull(Object object) throws IllegalArgumentException {
        if (object == null) {
            throw new IllegalArgumentException();
        }
    }

    public static void notNull(Object object, String message)
            throws IllegalArgumentException {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
