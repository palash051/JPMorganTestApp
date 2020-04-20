package com.example._20200418_jahirulbhuiyan_nycschools.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example._20200418_jahirulbhuiyan_nycschools.R;
import com.example._20200418_jahirulbhuiyan_nycschools.data_access.NYSDao;
import com.example._20200418_jahirulbhuiyan_nycschools.pojo.NYSchool;
import com.example._20200418_jahirulbhuiyan_nycschools.services.APIClient;
import com.example._20200418_jahirulbhuiyan_nycschools.services.ISatResultCallback;
import com.example._20200418_jahirulbhuiyan_nycschools.services.NYSchoolService;

public class SchoolSatResultFragment extends Fragment implements ISatResultCallback {

    private static final String ARG_PARAM1 = "NYSchool";
    NYSchool selectedSchool;

    NYSDao nysDao;
    TextView tvSchoolName;
    TextView tvSchoolAddress;
    TextView tvOverviewParagraph;
    TextView tvSatResult;

    public SchoolSatResultFragment() {

    }

    public static SchoolSatResultFragment newInstance(NYSchool nySchool) {
        SchoolSatResultFragment fragment = new SchoolSatResultFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, nySchool);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedSchool = (NYSchool) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_school_sat_result, container, false);
        tvSchoolName = view.findViewById(R.id.tvSchoolName);
        tvSchoolAddress = view.findViewById(R.id.tvSchoolAddress);
        tvOverviewParagraph = view.findViewById(R.id.tvOverviewParagraph);
        tvSatResult = view.findViewById(R.id.tvSatResult);
        nysDao = APIClient.getClient().create(NYSDao.class);
        tvSchoolName.setText(selectedSchool.getSchoolName());
        tvOverviewParagraph.setText(selectedSchool.getOverviewParagraph());
        tvSchoolAddress.setText(new StringBuilder().append(selectedSchool.getPrimaryAddressLine1()).append(",").append(selectedSchool.getCity()).append(",").append(selectedSchool.getZip()).toString());

        setSatResult();
        return view;
    }

    private void setSatResult() {
        NYSchoolService nySchoolService= new NYSchoolService();
        nySchoolService.getSchoolSatResult(this,selectedSchool.getSchoolName());
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void displaySatResult(String satScores) {
        tvSatResult.setText(satScores);
    }
}
