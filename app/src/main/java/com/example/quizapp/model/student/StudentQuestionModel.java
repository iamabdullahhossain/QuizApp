package com.example.quizapp.model.student;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StudentQuestionModel implements Serializable {



    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("t_name")
    @Expose
    private String tName;
    @SerializedName("t_uniquecode")
    @Expose
    private String tUniquecode;
    @SerializedName("q_question")
    @Expose
    private String qQuestion;
    @SerializedName("q_type")
    @Expose
    private String qType;
    @SerializedName("q_a")
    @Expose
    private String qA;
    @SerializedName("q_b")
    @Expose
    private String qB;
    @SerializedName("q_c")
    @Expose
    private String qC;
    @SerializedName("q_d")
    @Expose
    private String qD;
    @SerializedName("q_short")
    @Expose
    private Object qShort;
    @SerializedName("q_ans")
    @Expose
    private String qAns;
    @SerializedName("q_marks")
    @Expose
    private String qMarks;
    @SerializedName("r_code")
    @Expose
    private String rCode;
    @SerializedName("active")
    @Expose
    private String active;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getqQuestion() {
        return qQuestion;
    }

    public void setqQuestion(String qQuestion) {
        this.qQuestion = qQuestion;
    }

    public String getqType() {
        return qType;
    }

    public void setqType(String qType) {
        this.qType = qType;
    }

    public String getqA() {
        return qA;
    }

    public void setqA(String qA) {
        this.qA = qA;
    }

    public String getqB() {
        return qB;
    }

    public void setqB(String qB) {
        this.qB = qB;
    }

    public String getqC() {
        return qC;
    }

    public void setqC(String qC) {
        this.qC = qC;
    }

    public String getqD() {
        return qD;
    }

    public void setqD(String qD) {
        this.qD = qD;
    }

    public Object getqShort() {
        return qShort;
    }

    public void setqShort(Object qShort) {
        this.qShort = qShort;
    }

    public String getqAns() {
        return qAns;
    }

    public void setqAns(String qAns) {
        this.qAns = qAns;
    }

    public String getqMarks() {
        return qMarks;
    }

    public void setqMarks(String qMarks) {
        this.qMarks = qMarks;
    }

    public String getrCode() {
        return rCode;
    }

    public void setrCode(String rCode) {
        this.rCode = rCode;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

}
