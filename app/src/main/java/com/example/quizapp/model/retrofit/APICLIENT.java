package com.example.quizapp.model.retrofit;


import com.example.quizapp.model.student.StudentInformationModel;
import com.example.quizapp.model.student.StudentJoinedRoomModel;
import com.example.quizapp.model.student.StudentQuestionModel;
import com.example.quizapp.model.teacher.StudentAcceptedListModel;
import com.example.quizapp.model.teacher.StudentRequestCountModel;
import com.example.quizapp.model.teacher.StudentRequestModel;
import com.example.quizapp.model.teacher.TeacherFetchQuestionModel;
import com.example.quizapp.model.teacher.TeacherInformationModel;
import com.example.quizapp.model.teacher.TeacherRoomFetchModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APICLIENT {


    @FormUrlEncoded
    @POST("teacher/t_registration.php")
    Call<PostRetrofitModel> t_register(
            @Field("t_name") String t_name,
            @Field("t_designation") String t_designation,
            @Field("t_phone") String t_phone,
            @Field("t_password") String t_password
    );

    @GET("teacher/t_login.php")
    Call<PostRetrofitModel> t_login(
            @Query("t_phone") String phone,
            @Query("t_password") String password
    );

    @GET("teacher/t_information.php")
    Call<List<TeacherInformationModel>> t_information(
            @Query("t_phone") String phone
    );

    @FormUrlEncoded
    @POST("teacher/t_createroom.php")
    Call<PostRetrofitModel> t_createroom(
            @Field("t_name") String t_name,
            @Field("t_designation") String designation,
            @Field("t_phone") String t_phone,
            @Field("t_uniquecode") String t_uniquecode,
            @Field("r_code") String roomCode,
            @Field("r_name") String roomName,
            @Field("r_batch") String roomBatch,
            @Field("r_description") String roomDescription
    );

    @GET("teacher/t_fetchroom.php")
    Call<List<TeacherRoomFetchModel>> t_fetchroom(
            @Query("t_uniquecode") String t_uniquecode,
            @Query("t_phone") String t_phone,
            @Query("r_code") String r_code
    );

    @FormUrlEncoded
    @POST("teacher/t_mcqquestion.php")
    Call<PostRetrofitModel> t_mcq(

            @Field("t_name") String t_name,
            @Field("t_uniquecode") String t_uniquecode,
            @Field("q_question") String q_question,
            @Field("q_type") String q_type,
            @Field("q_a") String q_a,
            @Field("q_b") String q_b,
            @Field("q_c") String q_c,
            @Field("q_d") String q_d,
            @Field("q_ans") String q_ans,
            @Field("q_marks") String q_marks,
            @Field("r_code") String r_code

    );

    @FormUrlEncoded
    @POST("teacher/t_truefalse.php")
    Call<PostRetrofitModel> t_tf(

            @Field("t_name") String t_name,
            @Field("t_uniquecode") String t_uniquecode,
            @Field("q_question") String q_question,
            @Field("q_type") String q_type,
            @Field("q_a") String q_a,
            @Field("q_b") String q_b,
            @Field("q_ans") String q_ans,
            @Field("r_code") String r_code,
            @Field("q_marks") String q_marks

    );

    @FormUrlEncoded
    @POST("teacher/t_shortquestion.php")
    Call<PostRetrofitModel> t_shortanswer(

            @Field("t_name") String t_name,
            @Field("t_uniquecode") String t_uniquecode,
            @Field("q_question") String q_question,
            @Field("q_type") String q_type,
            @Field("r_code") String r_code,
            @Field("q_marks") String q_marks

    );


    @GET("teacher/t_fetchquestion.php")
    Call<List<TeacherFetchQuestionModel>> t_fetchquestion(

            @Query("t_uniquecode") String t_uniquecode,
            @Query("r_code") String r_code


    );

    @FormUrlEncoded
    @POST("teacher/t_updatetime.php")
    Call<PostRetrofitModel> updateTime(
            @Query("r_code") String r_code,
            @Field("r_time") String r_time
    );


    @FormUrlEncoded
    @POST("student/s_registration.php")
    Call<PostRetrofitModel> s_reg(
            @Field("s_name") String s_name,
            @Field("s_batch") String s_batch,
            @Field("s_id") String batchID,
            @Field("s_phone") String s_phone,
            @Field("s_password") String password,
            @Field("s_email") String email
    );


    @GET("student/s_login.php")
    Call<PostRetrofitModel> s_login(
            @Query("s_id") String s_id,
            @Query("s_password") String s_password
    );


    @GET("student/s_fetchinformation.php")
    Call<List<StudentInformationModel>> s_information(
            @Query("s_id") String id
    );

    @GET("student/s_fetchroominformation.php")
    Call<List<TeacherRoomFetchModel>> s_roomfetch(
            @Query("r_code") String r_code
    );

    @FormUrlEncoded
    @POST("student/s_joinroomrequest.php")
    Call<PostRetrofitModel> s_joinroom(

            @Field("t_name") String t_name,
            @Field("t_uniquecode") String t_uniquecode,
            @Field("r_code") String r_code,
            @Field("r_name") String r_name,
            @Field("r_batch") String r_batch,
            @Field("r_description") String r_description,
            @Field("r_time") String r_time,
            @Field("s_name") String s_name,
            @Field("s_id") String s_id,
            @Field("s_uniquecode") String s_uniquecode,
            @Field("request_date") String requestDate,
            @Field("s_batch") String s_batch


    );

    @GET("student/s_fetchjoinedroom.php")
    Call<List<StudentJoinedRoomModel>> s_joinedRoom(
            @Query("s_id") String s_id
    );

    @GET("teacher/t_waitingroomcount.php")
    Call<StudentRequestCountModel> req_count(
            @Query("r_code") String r_code
    );

    @GET("teacher/t_fetchstudentrequest.php")
    Call<List<StudentRequestModel>> s_request(
            @Query("r_code") String r_code
    );

    @GET("teacher/t_reqaccept.php")
    Call<PostRetrofitModel> accept_request(
            @Query("s_id") String s_id,
            @Query("r_code") String r_code
    );

    @FormUrlEncoded
    @POST("teacher/t_activeexam.php")
    Call<PostRetrofitModel> exam(
            @Query("r_code") String r_code,
            @Field("active") String active
    );

    @GET("student/s_fetchquestions.php")
    Call<List<StudentQuestionModel>> question(
            @Query("r_code") String r_code
    );

    @FormUrlEncoded
    @POST("student/s_answer.php")
    Call<PostRetrofitModel> answer(
            @Field("s_name") String s_name,
            @Field("s_id") String s_id,
            @Field("s_uniquecode") String s_uniquecode,
            @Field("s_batch") String s_batch,
            @Field("t_name") String t_name,
            @Field("t_uniquecode") String t_uniquecode,
            @Field("r_code") String r_code,
            @Field("r_name") String r_name,
            @Field("q_question") String q_question,
            @Field("q_id") String q_id,
            @Field("q_answer") String q_answer,
            @Field("s_answer") String s_answer,
            @Field("q_marks") String q_marks

    );

    @GET("teacher/t_fetchjoinedstudents.php")
    Call<List<StudentAcceptedListModel>> accepted(
            @Query("r_code") String r_code
    );


}
