package com.centrefx.idcardinfo;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class IDCardActivity extends AppCompatActivity implements View.OnClickListener {

    private int id_type = StringValues.ID_TYPE_NEW;
    private int id_length = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idcard);

        Bundle b = getIntent().getExtras();
        assert b != null;
        int idType = b.getInt("ID_TYPE");
//        assert idType != null;

        if (idType == StringValues.ID_TYPE_OLD) {
            TextView tv = findViewById(R.id.tx_id_type);
            tv.setText(R.string.title_old_id);
            TextInputLayout edl = findViewById(R.id.edl_id_num);
            edl.setCounterMaxLength(10);
            id_type = StringValues.ID_TYPE_OLD;
            id_length = 10;
        }

        monitorInput(R.id.ed_id_num, id_length, "Limit exceded.");
        monitorInput(R.id.ed_pup_num, 1, "Limit exceded.");

        Button bt = findViewById(R.id.bt_view_data);
        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View p1) {
        String id_num = getData(R.id.ed_id_num);
        String pp_numS = getData(R.id.ed_pup_num);

        if (!isValid(id_num, id_length) || !isValid(pp_numS, 1)) {
            Log.d("ActivityId", StringValues.INVALID_ID_PP);
            showDialog(StringValues.INVALID_ID_PP);
            return;
        }

        int pp_num = Integer.parseInt(pp_numS);
        String[] result = new String[6];
        IDInfo idn = new IDInfo(id_num, pp_num, id_type);

        if (isNull(idn.getYear()) || isNull(idn.getDateGender()) || isNull(idn.getProvince())) {
            Log.d("ActivityId", StringValues.CALC_ERROR);
            showDialog(StringValues.CALC_ERROR);
            return;
        }

        result[5] = idn.getDateGender();
        String[] r_dateGender = result[5].split(",");
        result[0] = id_num;
        result[1] = idn.getYear() + " - " + r_dateGender[1];
        result[2] = r_dateGender[0];
        result[3] = idn.getProvince();

        if (id_type == StringValues.ID_TYPE_OLD) {
            if (isNull(idn.getCivilStatus())) {
                Log.d("ActivityId", "Invalid Civil Status");
                return;
            } else {
                result[4] = idn.getCivilStatus();
            }
        }

        ResultFragment ar = ResultFragment.newInstance(result);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frag_layout_result, ar, "ActivityResult");
        ft.commit();
    }

    public void showDialog(String msg) {
        FragmentManager fm = getSupportFragmentManager();
        WarningDialog wd = new WarningDialog(msg);
        wd.show(fm, "WarningDialog");
    }

    public void monitorInput(int id, final int size, final String error) {
        final TextInputEditText ed1 = findViewById(id);
        ed1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (id_type == StringValues.ID_TYPE_OLD) {
                    TextInputEditText ed = findViewById(R.id.ed_id_num);
                    if (s.toString().length() == size - 1) {
                        ed.setInputType(EditorInfo.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                    }
                }
                if (s.toString().length() > size) {
                    ed1.setError(error);
                }
            }
        });
    }

    public String getData(int id) {
        TextInputEditText ed = findViewById(id);
        return Objects.requireNonNull(ed.getText()).toString();
    }

    public boolean isValid(String s, int size) {
        if (isNull(s)) {
            Log.d("ActivityId", "String s is Null.");
            return false;
        }

        if (s.length() == size) {
            try {
                long x;
                if(size == 1){
                    x = Long.parseLong(s);
                }
                else{
                    if (id_type == StringValues.ID_TYPE_OLD) {
                        x = Long.parseLong(s.substring(0, 9));
                    } else {
                        x = Long.parseLong(s);
                    }
                }
                return true;
            } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                Log.d("ActivityId", "int x is not valid.", new Throwable(e.getMessage()));
                return false;
            }
        }
        return false;
    }

    public boolean isNull(Object o) {
        return o == null;
    }
}