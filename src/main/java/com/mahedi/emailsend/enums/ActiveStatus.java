package com.mahedi.emailsend.enums;

public enum ActiveStatus {
    INACTIVE(0),
    ACTIVE(1);

    private final int value;

    ActiveStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
