package com.example._20200418_jahirulbhuiyan_nycschools.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example._20200418_jahirulbhuiyan_nycschools.R;
import com.example._20200418_jahirulbhuiyan_nycschools.pojo.NYSchool;
import com.example._20200418_jahirulbhuiyan_nycschools.services.INYSchoolServiceCallBack;
import com.example._20200418_jahirulbhuiyan_nycschools.services.NYSchoolService;

import java.util.List;


public class NYSchoolListFragment extends Fragment implements SchoolListAdapter.ItemClickListener, INYSchoolServiceCallBack {


    SchoolListAdapter adapter;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_nyschool_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getProgressDialog();
        recyclerView = view.findViewById(R.id.rvSchoolList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        displaySchoolList();
    }

    private void getProgressDialog() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMax(100);
        progressDialog.setMessage("Its loading....");
        progressDialog.setTitle("ProgressDialog bar example");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    private void displaySchoolList() {
        progressDialog.show();
        NYSchoolService  nySchoolService= new NYSchoolService();
        nySchoolService.getSchoolList(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        getFragmentManager().beginTransaction().
                replace(R.id.fragmentContainer, SchoolSatResultFragment.newInstance((NYSchool)view.getTag()), "NYSCHOOLSAT").addToBackStack(null).
                commit();
    }

    @Override
    public void displayList(List<NYSchool> schoolList) {
        adapter = new SchoolListAdapter(getContext(), schoolList);
        adapter.setClickListener(NYSchoolListFragment.this);
        recyclerView.setAdapter(adapter);
        progressDialog.dismiss();
    }
}
