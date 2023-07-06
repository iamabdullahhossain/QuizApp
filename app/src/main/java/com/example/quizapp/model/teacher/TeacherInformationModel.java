package com.example.quizapp.model.teacher;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TeacherInformationModel implements Serializable {

    @SerializedName("t_name")
    @Expose
    private String tName;
    @SerializedName("t_designation")
    @Expose
    private String tDesignation;
    @SerializedName("t_uniquecode")
    @Expose
    private String tUniquecode;
    @SerializedName("t_phone")
    @Expose
    private String tPhone;
    @SerializedName("t_password")
    @Expose
    private String tPassword;
    @SerializedName("t_token")
    @Expose
    private String tToken;

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettDesignation() {
        return tDesignation;
    }

    public void settDesignation(String tDesignation) {
        this.tDesignation = tDesignation;
    }

    public String gettUniquecode() {
        return tUniquecode;
    }

    public void settUniquecode(String tUniquecode) {
        this.tUniquecode = tUniquecode;
    }

    public String gettPhone() {
        return tPhone;
    }

    public void settPhone(String tPhone) {
        this.tPhone = tPhone;
    }

    public String gettPassword() {
        return tPassword;
    }

    public void settPassword(String tPassword) {
        this.tPassword = tPassword;
    }

    public String gettToken() {
        return tToken;
    }

    public void settToken(String tToken) {
        this.tToken = tToken;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TeacherInformationModel.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("tName");
        sb.append('=');
        sb.append(((this.tName == null)?"<null>":this.tName));
        sb.append(',');
        sb.append("tDesignation");
        sb.append('=');
        sb.append(((this.tDesignation == null)?"<null>":this.tDesignation));
        sb.append(',');
        sb.append("tUniquecode");
        sb.append('=');
        sb.append(((this.tUniquecode == null)?"<null>":this.tUniquecode));
        sb.append(',');
        sb.append("tPhone");
        sb.append('=');
        sb.append(((this.tPhone == null)?"<null>":this.tPhone));
        sb.append(',');
        sb.append("tPassword");
        sb.append('=');
        sb.append(((this.tPassword == null)?"<null>":this.tPassword));
        sb.append(',');
        sb.append("tToken");
        sb.append('=');
        sb.append(((this.tToken == null)?"<null>":this.tToken));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
