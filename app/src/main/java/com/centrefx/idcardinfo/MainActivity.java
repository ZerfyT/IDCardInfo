package com.centrefx.idcardinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.centrefx.idcardinfo.model.Values;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = findViewById(R.id.bt_new_id);
        b1.setOnClickListener(this);
        Button b2 = findViewById(R.id.bt_old_id);
        b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(MainActivity.this, IDCardActivity.class);
        if (v.getId() == R.id.bt_old_id) {
            i.putExtra("ID_TYPE", Values.ID_TYPE_OLD);
        } else if (v.getId() == R.id.bt_new_id) {
            i.putExtra("ID_TYPE", Values.ID_TYPE_NEW);
        }
        startActivity(i);
    }
}