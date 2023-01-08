package com.centrefx.idcardinfo_k

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.centrefx.idcardinfo_k.databinding.FragmentResultBinding
import com.centrefx.idcardinfo_k.model.Result

/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultFragment : Fragment() {
    private var v: View? = null
    private var result: Result? = null
    private lateinit var binding: FragmentResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        result = arguments?.getSerializable("RESULT") as Result?
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(inflater, container, false)
        v = binding.root
        val tv1 = binding.tvNum2
        val tv2 = binding.tvDob2
        val tv3 = binding.tvGender2
        val tv4 = binding.tvProvince2
        val tv5 = binding.tvCivil2

        tv1.text = ""
        tv2.text = ""
        tv3.text = ""
        tv4.text = ""
        tv5.text = ""

        tv1.text = result?.idNum
        tv2.text = result?.dateOfBirth
        tv3.text = result?.gender
        tv4.text = result?.province
        if (result?.civilState != null) {
            tv5.text = result?.civilState
        }
        return v
    }

    companion object {
        @JvmStatic
        fun newInstance(r: Result): ResultFragment {
            val fragment = ResultFragment()
            val b = Bundle()
            b.putSerializable("RESULT", r)
            fragment.arguments = b
            return fragment
        }
    }
}