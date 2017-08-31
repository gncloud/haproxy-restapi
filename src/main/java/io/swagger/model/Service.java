package io.swagger.model;

import java.util.Map;

/**
 * Created by swsong on 17. 8. 31..
 */
public class Service {
    private Frontend frontend;
    private Backend backend;

    public Service() {
    }

    public Service(Frontend frontend, Backend backend) {
        this.frontend = frontend;
        this.backend = backend;
    }
    public Frontend getFrontend() {
        return frontend;
    }

    public Backend getBackend() {
        return backend;
    }

    public void setFrontend(Frontend frontend) {
        this.frontend = frontend;
    }

    public void setBackend(Backend backend) {
        this.backend = backend;
    }
}
