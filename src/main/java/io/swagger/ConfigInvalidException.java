package io.swagger;

/**
 * Created by swsong on 17. 8. 30..
 */
public class ConfigInvalidException extends RuntimeException {
    public ConfigInvalidException(Exception e) {
        super(e);
    }
}
