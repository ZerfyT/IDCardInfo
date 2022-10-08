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

import com.centrefx.idcardinfo.model.IDInfo;
import com.centrefx.idcardinfo.model.Result;
import com.centrefx.idcardinfo.model.Values;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class IDCardActivity extends AppCompatActivity implements View.OnClickListener {

    private int id_type;
    private int id_length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idcard);

        Bundle b = getIntent().getExtras();
//        assert b != null;
        id_type = b.getInt("ID_TYPE");

        if (id_type == Values.ID_TYPE_OLD) {
            initIDView(R.string.title_old_id, Values.ID_OLD_LENGTH);
        } else if (id_type == Values.ID_TYPE_NEW) {
            initIDView(R.string.title_new_id, Values.ID_NEW_LENGTH);
        }

        monitorInput(R.id.ed_id_num, id_length, "Limit exceeded.");
        monitorInput(R.id.ed_pup_num, 1, "Limit exceeded.");

        Button bt = findViewById(R.id.bt_view_data);
        bt.setOnClickListener(this);
    }

    /**
     * Customize the IDCardActivity view according to ID Type.
     *
     * @param titleResID String Resource ID for Title of the view
     * @param length     Length of IDCard Number TextInputEditText
     */
    private void initIDView(int titleResID, int length) {
        TextView tv = findViewById(R.id.tx_id_type);
        tv.setText(titleResID);
        TextInputLayout edl = findViewById(R.id.edl_id_num);
        edl.setCounterMaxLength(length);
        id_length = length;
    }

    @Override
    public void onClick(View p1) {
        String id_num = getData(R.id.ed_id_num);
        String pp_numS = getData(R.id.ed_pup_num);

        if (isNotValid(id_num, id_length) || isNotValid(pp_numS, 1)) {
            Log.d("ActivityId", Values.INVALID_ID_PP);
            showDialog(Values.INVALID_ID_PP);
            return;
        }

        int pp_num = Integer.parseInt(pp_numS);
        IDInfo idn = new IDInfo(id_num, pp_num, id_type);

        if (isNull(idn.getDateOfBirth()) || isNull(idn.getGender()) || isNull(idn.getProvince())) {
            Log.d("ActivityId", Values.CALC_ERROR);
            showDialog(Values.CALC_ERROR);
            return;
        }

        Result result = new Result(id_num, idn.getDateOfBirth(), idn.getGender(), idn.getProvince());

        if (id_type == Values.ID_TYPE_OLD) {
            if (isNull(idn.getCivilStatus())) {
                Log.d("ActivityId", "Invalid Civil Status");
                return;
            } else {
                result.setCivilStatus(idn.getCivilStatus());
            }
        }

        ResultFragment ar = ResultFragment.newInstance(result);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frag_layout_result, ar, "ActivityResult");
        ft.commit();
    }

    /**
     * A Custom DialogFragment to display errors in DialogBox
     *
     * @param msg Message to display.
     */
    private void showDialog(String msg) {
        FragmentManager fm = getSupportFragmentManager();
        WarningDialog wd = new WarningDialog(msg);
        wd.show(fm, "WarningDialog");
    }

    /**
     * Initialize a {@link TextWatcher} to TextInputEditText to monitor the inputs and length of data.
     *
     * @param id    {@link TextInputEditText} resource id.
     * @param size  Length of TextInputEditText text.
     * @param error An error message that display in {@link TextInputLayout}
     */
    private void monitorInput(int id, final int size, final String error) {
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
                if (id_type == Values.ID_TYPE_OLD) {
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

    private String getData(int id) {
        TextInputEditText ed = findViewById(id);
        return Objects.requireNonNull(ed.getText()).toString();
    }

    /**
     * Validate user inputs (ID Number, Purple Number)
     *
     * @param s    A String value to validate.
     * @param size Exact length needs to be that string value
     * @return Return true if not valid, else return false.
     */
    private boolean isNotValid(String s, int size) {
        if (isNull(s)) {
            Log.d("ActivityId", "String s is Null.");
            return true;
        }

        if (s.length() == size) {
            try {
                long x;
                if (size == 1) {
                    x = Long.parseLong(s);
                } else {
                    if (id_type == Values.ID_TYPE_OLD) {
                        x = Long.parseLong(s.substring(0, 9));
                    } else {
                        x = Long.parseLong(s);
                    }
                }
                return false;
            } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                Log.d("ActivityId", "int x is not valid.", new Throwable(e.getMessage()));
                return true;
            }
        }
        return true;
    }

    private boolean isNull(Object o) {
        return o == null;
    }
}