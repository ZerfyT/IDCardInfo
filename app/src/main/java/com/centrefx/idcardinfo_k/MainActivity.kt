package com.centrefx.idcardinfo_k

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.centrefx.idcardinfo_k.databinding.ActivityMainBinding
import com.centrefx.idcardinfo_k.model.Values

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btNewId.setOnClickListener(this)
        binding.btOldId.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val i = Intent(this, IDCardActivity::class.java)
        val idType: Int = if (v?.id == binding.btOldId.id) {
            Values.ID_TYPE_OLD
        } else {
            Values.ID_TYPE_NEW
        }
        i.putExtra("ID_TYPE", idType)
        startActivity(i)
    }


}