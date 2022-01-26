package com.vn.chotot.exception;

public enum FailureHandling {
    STOP_ON_FAILURE,
    CONTINUE_ON_FAILURE,
    WARNING;

    FailureHandling() {
    }
}
