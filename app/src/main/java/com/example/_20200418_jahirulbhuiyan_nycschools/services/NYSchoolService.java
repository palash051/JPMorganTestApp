package com.example._20200418_jahirulbhuiyan_nycschools.services;

import android.support.v4.app.Fragment;

import com.example._20200418_jahirulbhuiyan_nycschools.data_access.NYSDao;
import com.example._20200418_jahirulbhuiyan_nycschools.pojo.NYSchool;
import com.example._20200418_jahirulbhuiyan_nycschools.pojo.NYSchoolSatScore;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NYSchoolService {
    NYSDao nysDao;
    INYSchoolServiceCallBack inySchoolServiceCallBack;
    ISatResultCallback iSatResultCallback;

    public void getSchoolList(Fragment context){
        nysDao = APIClient.getClient().create(NYSDao.class);
        inySchoolServiceCallBack= (INYSchoolServiceCallBack) context;
        Call<List<NYSchool>> call = nysDao.getSchoolList();
        call.enqueue(new Callback<List<NYSchool>>() {
            @Override
            public void onResponse(Call<List<NYSchool>> call, Response<List<NYSchool>> response) {
                inySchoolServiceCallBack.displayList(response.body());
            }

            @Override
            public void onFailure(Call<List<NYSchool>> call, Throwable t) {
                call.cancel();

            }
        });
    }

    public void getSchoolSatResult(Fragment context, String schoolName){
        nysDao = APIClient.getClient().create(NYSDao.class);
        iSatResultCallback= (ISatResultCallback) context;
        Call<List<NYSchoolSatScore>> call = nysDao.getSetResult(schoolName.toUpperCase());

        call.enqueue(new Callback<List<NYSchoolSatScore>>() {
            @Override
            public void onResponse(Call<List<NYSchoolSatScore>> call, Response<List<NYSchoolSatScore>> response) {
                List<NYSchoolSatScore> satScores = response.body();
                StringBuilder satResult=new StringBuilder();
                for (NYSchoolSatScore nySchoolSatScore:satScores ) {
                    satResult.append("DBN: "+nySchoolSatScore.getDbn()+"\n");
                    satResult.append("Num Of Test Takers: "+nySchoolSatScore.getNumOfSatTestTakers()+"\n");
                    satResult.append("Critical Reading Avg Score: "+nySchoolSatScore.getSatCriticalReadingAvgScore()+"\n");
                    satResult.append("Math Avg Score: "+nySchoolSatScore.getSatMathAvgScore()+"\n");
                    satResult.append("Writing Avg Score: "+nySchoolSatScore.getSatWritingAvgScore());
                }
                iSatResultCallback.displaySatResult(satResult.toString());
            }

            @Override
            public void onFailure(Call<List<NYSchoolSatScore>> call, Throwable t) {
                call.cancel();

            }
        });
    }


}
