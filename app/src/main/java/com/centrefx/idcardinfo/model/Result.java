package com.centrefx.idcardinfo.model;

import java.io.Serializable;

public class Result implements Serializable {
    String idNum;
    String dateOfBirth;
    String gender;
    String province;
    String civilStatus;

    public Result(String idNum, String dateOfBirth, String gender, String province) {
        this.idNum = idNum;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.province = province;
    }

    public String getIdNum() {
        return idNum;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getProvince() {
        return province;
    }

    public String getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(String civilStatus) {
        this.civilStatus = civilStatus;
    }
}
