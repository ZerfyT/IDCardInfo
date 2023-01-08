package com.centrefx.idcardinfo_k.model

class Values {
    companion object {
        val MONTH = intArrayOf(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
        val PROVINCE = arrayOf(
            "Western Province",
            "Central Province",
            "Southern Province",
            "Northern Province",
            "Eastern Province",
            "North Western Province",
            "North Central Province",
            "Province of Uva",
            "Province of Sabaragamuwa"
        )
        val GENDER = arrayOf("Male", "Female")
        val CIVIL = arrayOf("Voting Citizen", "Non-Voting Citizen")
        const val INVALID_ID_PP = "Incorrect ID Number or PP Number."
        const val CALC_ERROR = "Can't Calculate this ID Number."
        const val ID_TYPE_NEW = 1
        const val ID_TYPE_OLD = 0
        const val ID_NEW_LENGTH = 12
        const val ID_OLD_LENGTH = 10
    }
}