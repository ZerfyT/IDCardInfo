package com.centrefx.idcardinfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.centrefx.idcardinfo.model.Result;

import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultFragment extends Fragment {

    private View v;
    private Result result;

    public ResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param r A Result object
     * @return A new instance of fragment ResultFragment.
     */
    public static ResultFragment newInstance(Result r) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putSerializable("RESULT", r);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            result = (Result) getArguments().getSerializable("RESULT");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_result, container, false);
        TextView tv1 = initTextView(R.id.tv_num2);
        TextView tv2 = initTextView(R.id.tv_dob2);
        TextView tv3 = initTextView(R.id.tv_gender2);
        TextView tv4 = initTextView(R.id.tv_province2);
        TextView tv5 = initTextView(R.id.tv_civil2);

        tv1.setText("");
        tv2.setText("");
        tv3.setText("");
        tv4.setText("");
        tv5.setText("");

        tv1.setText(result.getIdNum());
        tv2.setText(result.getDateOfBirth());
        tv3.setText(result.getGender());
        tv4.setText(result.getProvince());
        tv5.setText(result.getCivilStatus());
        return v;
    }

    private TextView initTextView(int id) {
        return v.findViewById(id);
    }
}