package com.centrefx.idcardinfo.model;

public class Values {
    public static final int[] MONTH = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public static final String[] PROVINCE = {"Western Province", "Central Province", "Southern Province", "Northern Province", "Eastern Province", "North Western Province", "North Central Province", "Province of Uva", "Province of Sabaragamuwa"};
    public static final String[] GENDER = {"Male", "Female"};
    public static final String[] CIVIL = {"Voting Citizen", "Non-Voting Citizen"};
    public static final String INVALID_ID_PP = "Incorrect ID Number or PP Number.";
    public static final String CALC_ERROR = "Can't Calculate this ID Number.";
    public static final int ID_TYPE_NEW = 1;
    public static final int ID_TYPE_OLD = 0;
    public static final int ID_NEW_LENGTH = 12;
    public static final int ID_OLD_LENGTH = 10;
}

