package org.example.backwcy.exception;

public class DeviceNameAlreadyExistsException extends RuntimeException {
    public DeviceNameAlreadyExistsException(String message) {
        super(message);
    }
}