package com.example.quizapp.model.student;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StudentInformationModel implements Serializable {


    @SerializedName("s_name")
    @Expose
    private String sName;
    @SerializedName("s_batch")
    @Expose
    private String sBatch;
    @SerializedName("s_id")
    @Expose
    private String sId;
    @SerializedName("s_phone")
    @Expose
    private String sPhone;
    @SerializedName("s_password")
    @Expose
    private String sPassword;
    @SerializedName("s_email")
    @Expose
    private String sEmail;
    @SerializedName("s_uniquecode")
    @Expose
    private String sUniquecode;

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsBatch() {
        return sBatch;
    }

    public void setsBatch(String sBatch) {
        this.sBatch = sBatch;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsPhone() {
        return sPhone;
    }

    public void setsPhone(String sPhone) {
        this.sPhone = sPhone;
    }

    public String getsPassword() {
        return sPassword;
    }

    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getsUniquecode() {
        return sUniquecode;
    }

    public void setsUniquecode(String sUniquecode) {
        this.sUniquecode = sUniquecode;
    }

}
