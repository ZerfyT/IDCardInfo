package com.centrefx.idcardinfo_k.model

data class IDInfo(val idNum: String, val pupNum: Int, val idType: Int) {
    var gender: String = ""

    private fun getYear(): String {
        return when (idType) {
            Values.ID_TYPE_NEW -> idNum.substring(0, 4)
            Values.ID_TYPE_OLD -> "19${idNum.substring(0, 2)}"
            else -> ""
        }
    }

    private fun getDate(): String {
        var date = ""
        var tmp = 0

        when (idType) {
            Values.ID_TYPE_NEW -> tmp = idNum.substring(4, 7).toInt()
            Values.ID_TYPE_OLD -> tmp = idNum.substring(2, 5).toInt()
        }

        if (tmp in 1..366) {
            gender = Values.GENDER[0]
            var i = 0
            while (tmp > Values.MONTH[i]) {
                tmp -= Values.MONTH[i]
                i++
            }
            date = "${i + 1} - $tmp"
        } else if (tmp in 501..866) {
            gender = Values.GENDER[1]
            tmp -= 500
            var i = 0
            while (tmp > Values.MONTH[i]) {
                tmp -= Values.MONTH[i]
                i++
            }
            date = "${i + 1} - $tmp"
        }
        return date
    }

    fun getProvince(): String {
        var p = ""
        val index = pupNum - 1
        if (index in 0 until Values.PROVINCE.size) {
            p = Values.PROVINCE[index]
        }
        return p
    }

    fun getCivilStatus(): String? {
        val tmp = idNum.elementAt(9).toString()
        if (tmp.equals("V", true)) {
            return Values.CIVIL[0]
        } else if (tmp.equals("X", true)) {
            return Values.CIVIL[1]
        }
        return null
    }

    fun getDateOfBirth(): String {
        if (getYear().isEmpty() || getDate().isEmpty()) {
            return ""
        }
        return getYear() + " - " + getDate()
    }
}