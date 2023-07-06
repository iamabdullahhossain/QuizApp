package com.example.quizapp.model.retrofit;


public class PostRetrofitModel {
    String Reply;

    public PostRetrofitModel() {
    }

    public PostRetrofitModel(String reply) {
        Reply = reply;
    }

    public String getReply() {
        return Reply;
    }

    public void setReply(String reply) {
        Reply = reply;
    }
}
