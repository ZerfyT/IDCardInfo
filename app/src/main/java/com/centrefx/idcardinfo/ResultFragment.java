package com.centrefx.idcardinfo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultFragment extends Fragment {

    private View v;
    private TextView tv1, tv2, tv3, tv4, tv5;
    private String[] result;

    public ResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param r A String Array of Result
     * @return A new instance of fragment ResultFragment.
     */
    public static ResultFragment newInstance(String[] r) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putStringArray("RESULT", r);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            result = getArguments().getStringArray("RESULT");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_result, container, false);
        tv1 = initTextView(R.id.tv_num);
        tv2 = initTextView(R.id.tv_dob);
        tv3 = initTextView(R.id.tv_gender);
        tv4 = initTextView(R.id.tv_province);
        tv5 = initTextView(R.id.tv_civil);

        tv1.setText("");
        tv2.setText("");
        tv3.setText("");
        tv4.setText("");
        tv5.setText("");

        tv1.setText(String.format("ID Number : %s", result[0]));
        tv2.setText(String.format("Date of Birth : %s", result[1]));
        tv3.setText(String.format("Gender : %s", result[2]));
        tv4.setText(String.format("Province : %s", result[3]));
        tv5.setText(String.format("Civil Status : %s", result[4]));

        return v;
    }

    public TextView initTextView(int id) {
        return v.findViewById(id);
    }
}