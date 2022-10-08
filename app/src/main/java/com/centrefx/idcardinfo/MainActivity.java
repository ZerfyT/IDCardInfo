package com.centrefx.idcardinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

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
        switch(v.getId())
        {
            case R.id.bt_old_id:
                i.putExtra("ID_TYPE", StringValues.ID_TYPE_OLD);
                break;
            case R.id.bt_new_id:
                i.putExtra("ID_TYPE", StringValues.ID_TYPE_NEW);
                break;
        }
        startActivity(i);
    }
}