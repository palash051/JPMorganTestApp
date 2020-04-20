package com.example._20200418_jahirulbhuiyan_nycschools.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example._20200418_jahirulbhuiyan_nycschools.R;

public class NYSchoolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportFragmentManager().beginTransaction().
                replace(R.id.fragmentContainer, new NYSchoolListFragment(), "NYSCHOOL").addToBackStack(null).
                commit();

    }
}
