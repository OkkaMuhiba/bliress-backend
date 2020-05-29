package com.blibli.future.phase2.entity.enumerate;

public enum Role {
    ROLE_USER("ROLE_USER"),
    ROLE_TRAINER("ROLE_TRAINER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
