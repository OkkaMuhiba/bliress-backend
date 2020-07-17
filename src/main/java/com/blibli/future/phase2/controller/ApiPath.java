package com.blibli.future.phase2.controller;

public class ApiPath {
    public static final String BASE_PATH = "/api";

    /**
     * AUTH PATH
     */
    public static final String AUTH = BASE_PATH + "/auth";
    public static final String AUTH_LOGIN = AUTH + "/login";
    public static final String AUTH_REGISTER = AUTH + "/register";

    /**
     * ADMIN PATH
     */
    public static final String BASE_ADMIN = BASE_PATH + "/admin";

    // Batch
    public static final String ADMIN_BATCH = BASE_ADMIN + "/batch";
    public static final String ADMIN_BATCH_CREATE = ADMIN_BATCH + "";
    public static final String ADMIN_BATCH_GET_ALL = ADMIN_BATCH + "";
    public static final String ADMIN_BATCH_GET_BY_ID = ADMIN_BATCH + "/detail";
    public static final String ADMIN_BATCH_DELETE = ADMIN_BATCH + "";

    // Employee
    public static final String ADMIN_EMPLOYEE = BASE_ADMIN + "/employee";
    public static final String ADMIN_EMPLOYEE_CREATE = ADMIN_EMPLOYEE + "";
    public static final String ADMIN_EMPLOYEE_GET_ALL = ADMIN_EMPLOYEE + "";
    public static final String ADMIN_EMPLOYEE_GET_ALL_BY_BATCH = ADMIN_EMPLOYEE + "";
    public static final String ADMIN_EMPLOYEE_GET_BY_ID = ADMIN_EMPLOYEE + "/detail";
    public static final String ADMIN_EMPLOYEE_UPDATE = ADMIN_EMPLOYEE + "";
    public static final String ADMIN_EMPLOYEE_DELETE = ADMIN_EMPLOYEE + "";

    // Training
    public static final String ADMIN_TRAINING = ADMIN_BATCH + "/training";
    public static final String ADMIN_TRAINING_CREATE = ADMIN_TRAINING + "/";
    public static final String ADMIN_TRAINING_GET_ALL = ADMIN_TRAINING + "/";
    public static final String ADMIN_TRAINING_GET_ATTENDANCE = ADMIN_TRAINING + "/attendance";
    public static final String ADMIN_TRAINING_GET_BY_ID = ADMIN_TRAINING + "/detail";
    public static final String ADMIN_TRAINING_UPDATE = ADMIN_TRAINING + "/";
    public static final String ADMIN_TRAINING_DELETE = ADMIN_TRAINING + "/";

    // Trainer
    public static final String ADMIN_TRAINER = BASE_ADMIN + "/trainer";
    public static final String ADMIN_TRAINER_CREATE = ADMIN_TRAINER + "";
    public static final String ADMIN_TRAINER_GET_ALL = ADMIN_TRAINER + "";
    public static final String ADMIN_TRAINER_GET_BY_ID = ADMIN_TRAINER + "/detail";
    public static final String ADMIN_TRAINER_UPDATE = ADMIN_TRAINER + "";
    public static final String ADMIN_TRAINER_DELETE = ADMIN_TRAINER + "";

    // Employee
    public static final String ADMIN_MATERIAL = BASE_ADMIN + "/material";
    public static final String ADMIN_MATERIAL_CREATE = ADMIN_MATERIAL + "/";
    public static final String ADMIN_MATERIAL_GET_ALL = ADMIN_MATERIAL + "/";
    public static final String ADMIN_MATERIAL_DELETE = ADMIN_MATERIAL + "/";
}
