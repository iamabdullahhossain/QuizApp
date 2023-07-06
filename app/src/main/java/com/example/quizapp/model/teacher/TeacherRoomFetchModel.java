package com.example.quizapp.model.teacher;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TeacherRoomFetchModel implements Serializable {


    @SerializedName("t_name")
    @Expose
    private String tName;
    @SerializedName("t_designation")
    @Expose
    private String tDesignation;
    @SerializedName("t_phone")
    @Expose
    private String tPhone;
    @SerializedName("t_uniquecode")
    @Expose
    private String tUniquecode;
    @SerializedName("r_code")
    @Expose
    private String rCode;
    @SerializedName("r_name")
    @Expose
    private String rName;
    @SerializedName("r_batch")
    @Expose
    private String rBatch;
    @SerializedName("r_description")
    @Expose
    private String rDescription;
    @SerializedName("r_time")
    @Expose
    private String rTime;

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

    public String gettPhone() {
        return tPhone;
    }

    public void settPhone(String tPhone) {
        this.tPhone = tPhone;
    }

    public String gettUniquecode() {
        return tUniquecode;
    }

    public void settUniquecode(String tUniquecode) {
        this.tUniquecode = tUniquecode;
    }

    public String getrCode() {
        return rCode;
    }

    public void setrCode(String rCode) {
        this.rCode = rCode;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getrBatch() {
        return rBatch;
    }

    public void setrBatch(String rBatch) {
        this.rBatch = rBatch;
    }

    public String getrDescription() {
        return rDescription;
    }

    public void setrDescription(String rDescription) {
        this.rDescription = rDescription;
    }

    public String getrTime() {
        return rTime;
    }

    public void setrTime(String rTime) {

        this.rTime = rTime;
    }

}
