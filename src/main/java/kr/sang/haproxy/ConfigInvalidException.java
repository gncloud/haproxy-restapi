package kr.sang.haproxy;

/**
 * Created by swsong on 17. 8. 30..
 */
public class ConfigInvalidException extends RuntimeException {
    public ConfigInvalidException(Exception e) {
        super(e);
    }

    public ConfigInvalidException(String message) {
        super(message);
    }
}
