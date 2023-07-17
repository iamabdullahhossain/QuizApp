package com.example.quizapp.model.teacher;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TeacherResultBoradModel implements Serializable {


    @SerializedName("s_name")
    @Expose
    private String sName;
    @SerializedName("s_id")
    @Expose
    private String sId;
    @SerializedName("s_uniquecode")
    @Expose
    private String sUniquecode;
    @SerializedName("s_batch")
    @Expose
    private String sBatch;
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
    @SerializedName("q_question")
    @Expose
    private String qQuestion;
    @SerializedName("q_id")
    @Expose
    private String qId;
    @SerializedName("q_answer")
    @Expose
    private String qAnswer;
    @SerializedName("s_answer")
    @Expose
    private String sAnswer;
    @SerializedName("q_marks")
    @Expose
    private String qMarks;
    @SerializedName("t_marking")
    @Expose
    private String tMarking;
    @SerializedName("t_checked")
    @Expose
    private String tChecked;
    @SerializedName("id")
    @Expose
    private String id;

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

    public String getsBatch() {
        return sBatch;
    }

    public void setsBatch(String sBatch) {
        this.sBatch = sBatch;
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

    public String getqQuestion() {
        return qQuestion;
    }

    public void setqQuestion(String qQuestion) {
        this.qQuestion = qQuestion;
    }

    public String getqId() {
        return qId;
    }

    public void setqId(String qId) {
        this.qId = qId;
    }

    public String getqAnswer() {
        return qAnswer;
    }

    public void setqAnswer(String qAnswer) {
        this.qAnswer = qAnswer;
    }

    public String getsAnswer() {
        return sAnswer;
    }

    public void setsAnswer(String sAnswer) {
        this.sAnswer = sAnswer;
    }

    public String getqMarks() {
        return qMarks;
    }

    public void setqMarks(String qMarks) {
        this.qMarks = qMarks;
    }

    public String gettMarking() {
        return tMarking;
    }

    public void settMarking(String tMarking) {
        this.tMarking = tMarking;
    }

    public String gettChecked() {
        return tChecked;
    }

    public void settChecked(String tChecked) {
        this.tChecked = tChecked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
