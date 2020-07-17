package com.blibli.future.phase2.entity.enumerate;

public enum Division {
    TECH("TECH"),
    MARKETING("MARKETING");

    private String division;

    Division(String division) {
        this.division = division;
    }

    public String getDivision() {
        return division;
    }
}
