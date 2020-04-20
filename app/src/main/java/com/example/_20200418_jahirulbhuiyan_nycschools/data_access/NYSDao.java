package com.example._20200418_jahirulbhuiyan_nycschools.data_access;

import com.example._20200418_jahirulbhuiyan_nycschools.pojo.NYSchool;
import com.example._20200418_jahirulbhuiyan_nycschools.pojo.NYSchoolSatScore;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NYSDao {
    //@FormUrlEncoded
    @GET("s3k6-pzi2.json")
    Call<List<NYSchool>> getSchoolList();

    //@FormUrlEncoded
    @GET("f9bf-2cp4.json")
    Call<List<NYSchoolSatScore>> getSetResult(@Query("school_name") String schoolName);
}
