package com.example.quizapp.model.student;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StudentJoinedRoomModel implements Serializable {


    @SerializedName("t_name")
    @Expose
    private String tName;
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
    @SerializedName("s_name")
    @Expose
    private String sName;
    @SerializedName("s_id")
    @Expose
    private String sId;
    @SerializedName("s_uniquecode")
    @Expose
    private String sUniquecode;
    @SerializedName("request_date")
    @Expose
    private String requestDate;
    @SerializedName("s_status")
    @Expose
    private String sStatus;

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
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

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsUniquecode() {
        return sUniquecode;
    }

    public void setsUniquecode(String sUniquecode) {
        this.sUniquecode = sUniquecode;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getsStatus() {
        return sStatus;
    }

    public void setsStatus(String sStatus) {
        this.sStatus = sStatus;
    }

}
