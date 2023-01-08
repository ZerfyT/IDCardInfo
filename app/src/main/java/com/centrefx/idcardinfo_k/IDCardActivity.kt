package com.centrefx.idcardinfo_k

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import com.centrefx.idcardinfo_k.databinding.ActivityIdcardBinding
import com.centrefx.idcardinfo_k.model.IDInfo
import com.centrefx.idcardinfo_k.model.Result
import com.centrefx.idcardinfo_k.model.Values
import com.google.android.material.textfield.TextInputEditText

class IDCardActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityIdcardBinding
    private var idType: Int = 0
    private var idLength: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIdcardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val b: Bundle? = intent.extras
        idType = b?.getInt("ID_TYPE")!!

        when (idType) {
            Values.ID_TYPE_OLD -> initView(R.string.title_old_id, Values.ID_OLD_LENGTH)
            Values.ID_TYPE_NEW -> initView(R.string.title_new_id, Values.ID_NEW_LENGTH)
        }
        monitorInput(binding.edIdNum, idLength, "Limit exceeded.")
        monitorInput(binding.edPupNum, 1, "Limit exceeded.")

        binding.btViewData.setOnClickListener(this)

    }

    private fun initView(titleResID: Int, length: Int) {
        val tv = binding.txIdType
        tv.text = getString(titleResID)
        val ed = binding.edlIdNum
        ed.counterMaxLength = length
        idLength = length
    }

    override fun onClick(v: View?) {
        val idNum = binding.edIdNum.text.toString()
        val ppNum = binding.edPupNum.text.toString()

        if (isNotValid(idNum, idLength) || isNotValid(ppNum, 1)) {
            showDialog(Values.INVALID_ID_PP)
            return
        }
        val idn = IDInfo(idNum, ppNum.toInt(), idType)

        if (idn.getDateOfBirth().isEmpty() || idn.gender.isEmpty() || idn.getProvince().isEmpty()) {
            showDialog(Values.CALC_ERROR)
            return
        }

        val result = Result(idNum, idn.getDateOfBirth(), idn.gender, idn.getProvince())
        if (idType == Values.ID_TYPE_OLD) {
            result.civilState = idn.getCivilStatus()!!
        }

        val rf = ResultFragment.newInstance(result)
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(binding.fragLayoutResult.id, rf, "ActivityResult")
        ft.commit()
    }

    private fun showDialog(msg: String) {
        val fm = supportFragmentManager
        val wd = WarningDialog(msg)
        wd.show(fm, "WarningDialog")
    }

    private fun monitorInput(edt: TextInputEditText, size: Int, error: String) {
        edt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (idType == Values.ID_TYPE_OLD && edt == binding.edIdNum) {
                    if (s.toString().length == size - 1) {
                        edt.inputType = EditorInfo.TYPE_TEXT_FLAG_CAP_CHARACTERS
                    }
                }
                if (s.toString().length > size) {
                    edt.error = error
                }
            }
        })
    }

    private fun isNotValid(s: String, size: Int): Boolean {
        if (s.isNullOrBlank()) {
            return true
        }
        if (s.length == size) {
            try {
                val x: Long = if (size == 1) {
                    s.toLong()
                } else {
                    if (idType == Values.ID_TYPE_OLD) {
                        s.substring(0, 9).toLong()
                    } else {
                        s.toLong()
                    }
                }
                return false
            } catch (e: NumberFormatException) {
                return true
            } catch (e: StringIndexOutOfBoundsException) {
                return true
            }
        }
        return true
    }
}