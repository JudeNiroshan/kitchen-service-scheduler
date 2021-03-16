package com.verimi.kitchenservice.exception;

public class StaffMemberNotFoundException extends RuntimeException {

    public StaffMemberNotFoundException(Long id) {
        super("Could not find staff member with ID " + id);
    }
}
