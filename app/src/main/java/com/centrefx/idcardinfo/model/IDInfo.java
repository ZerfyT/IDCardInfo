package com.centrefx.idcardinfo.model;

public class IDInfo {
    private final String id_num;
    private final int id_pup_num;
    private final int id_type;
    private String gender;

    public IDInfo(String idnNum, int pupNum, int idType) {
        this.id_num = idnNum;
        this.id_pup_num = pupNum;
        this.id_type = idType;
    }

    /**
     * Calculate year of date of birth.
     *
     * @return year or null if error occurred.
     */
    private String getYear() {
        switch (id_type) {
            case Values.ID_TYPE_NEW: {
                return id_num.substring(0, 4);
            }
            case Values.ID_TYPE_OLD: {
                return "19" + id_num.substring(0, 2);
            }
        }
        return null;
    }

    /**
     * Calculate month, date and gender.
     *
     * @return Month, Date as like "MM - DD"
     */
    private String getDate() {
        String date = null;
        int tmp = 0;

        switch (id_type) {
            case Values.ID_TYPE_NEW: {
                tmp = Integer.parseInt(id_num.substring(4, 7));
                break;
            }
            case Values.ID_TYPE_OLD: {
                tmp = Integer.parseInt(id_num.substring(2, 5));
            }
        }

        if ((tmp <= 366) && (tmp >= 1)) {
            gender = Values.GENDER[0]; // Male
            int i = 0;
            while (tmp > Values.MONTH[i]) {
                tmp = tmp - Values.MONTH[i];
                i++;
            }
            date = (i + 1) + " - " + tmp;
        } else if ((tmp <= 866) && (tmp >= 501)) {
            gender = Values.GENDER[1]; // Female
            tmp = tmp - 500;
            int i = 0;
            while (tmp > Values.MONTH[i]) {
                tmp = tmp - Values.MONTH[i];
                i++;
            }
            date = (i + 1) + " - " + tmp;
        }
        return date;
    }

    /**
     * Calculate province by purple number.
     * List of provinces are available in {@link Values}
     *
     * @return province
     */
    public String getProvince() {
        String p = null;
        int index = id_pup_num - 1;

        if (index < Values.PROVINCE.length && index >= 0) {
            p = Values.PROVINCE[index];
        }
        return p;
    }

    /**
     * Civil Statues of given ID Number.
     *
     * @return civilStatus can be Married or Unmarried
     */
    public String getCivilStatus() {
        String tmp = String.valueOf(id_num.charAt(9));

        if (tmp.equalsIgnoreCase("V")) {
            return Values.CIVIL[0];
        } else if (tmp.equalsIgnoreCase("X")) {
            return Values.CIVIL[1];
        }
        return null;
    }

    /**
     * Get gender from ID Number.
     *
     * @return gender can be Male or Female
     */
    public String getGender() {
        return gender;
    }

    /**
     * Date of Birth from given ID Number.
     *
     * @return "YYYY - MM - DD" formatted date
     */
    public String getDateOfBirth() {
        if (getYear() == null || getDate() == null) {
            return null;
        }
        return getYear() + " - " + getDate();
    }
}