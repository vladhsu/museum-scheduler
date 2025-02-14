package com.vladhsu.app;

public enum PathTypes {
    GROUPS("groups"),
    LISTENER("listener"),
    MUSEUMS("museums");

    private final String value;

    PathTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
