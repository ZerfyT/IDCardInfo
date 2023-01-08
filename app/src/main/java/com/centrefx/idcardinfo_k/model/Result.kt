package com.centrefx.idcardinfo_k.model

import java.io.Serializable

data class Result(val idNum: String, val dateOfBirth: String, val gender: String, val province: String) : Serializable {
    var civilState: String? = null
}
