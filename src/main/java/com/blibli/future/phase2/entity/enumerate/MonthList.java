package com.blibli.future.phase2.entity.enumerate;

public enum MonthList {
    JAN(1),
    FEB(2),
    MAR(3),
    APR(4),
    MAY(5),
    JUN(6),
    JUL(7),
    AUG(8),
    SEP(9),
    OCT(10),
    NOV(11),
    DES(12);

    private Integer monthNumber;

    private String monthName;

    MonthList(int monthNumber) {
        this.monthNumber = monthNumber;

        String[] monthName = new String[]{
                "January", "February", "March", "April",
                "May", "June", "July", "August",
                "September", "October", "November", "December"
        };

        this.monthName = monthName[monthNumber - 1];
    }
}
