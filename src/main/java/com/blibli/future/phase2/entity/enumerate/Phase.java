package com.blibli.future.phase2.entity.enumerate;

public enum Phase {
    PHASE_1("PHASE 1"),
    PHASE_2("PHASE 2"),
    PHASE_3("PHASE 3"),
    PHASE_4("PHASE 4"),
    PHASE_5("PHASE 5");

    private String phase;

    Phase(String phase) {
        this.phase = phase;
    }

    public String getPhase() {
        return phase;
    }
}
