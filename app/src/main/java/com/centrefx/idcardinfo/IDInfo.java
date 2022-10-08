package com.centrefx.idcardinfo;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class IDInfo {
//    private final int[] MONTH = new int[12];
    private final String id_num;
    private final int id_pup_num;
    private final int id_type;

    // id_type -- false = old, true = new
    public IDInfo(String idnNum, int pupNum, int idType) {
        this.id_num = idnNum;
        this.id_pup_num = pupNum;
        this.id_type = idType;
    }

    /**
     * Get year from ID number
     *
     * @return year
     */
    public String getYear() {
        switch (id_type) {
            case 1: {
                return id_num.substring(0, 4);
            }
            case 0: {
                return "19" + id_num.substring(0, 2);
            }
        }
        return null;
    }

    public String getDateGender() {

        // 0 = date, 1 = gender
        String date_gender = null;
        int tmp = 0;
//        setMonthDays(Integer.parseInt(getYear()));

        switch (id_type) {
            case 1: {
                tmp = Integer.parseInt(id_num.substring(4, 7));
                break;
            }
            case 0: {
                tmp = Integer.parseInt(id_num.substring(2, 5));
            }
        }

        if ((tmp <= 366) && (tmp >= 1)) {
            date_gender = StringValues.GENDER[0]; // Male
            int i = 0;
            while (tmp > StringValues.MONTH[i]) {
                tmp = tmp - StringValues.MONTH[i];
                i++;
            }
            date_gender += "," + (i + 1) + " - " + tmp;
        } else if ((tmp <= 866) && (tmp >= 501)) {
            date_gender = StringValues.GENDER[1]; // Female
            tmp = tmp - 500;
            int i = 0;
            while (tmp > StringValues.MONTH[i]) {
                tmp = tmp - StringValues.MONTH[i];
                i++;
            }
            date_gender += "," + (i + 1) + " - " + tmp;
        }
        return date_gender;
    }

    public String getProvince() // Province
    {
        String p = null;
        int index = id_pup_num - 1;

        if (index < StringValues.PROVINCE.length && index >= 0) {
            p = StringValues.PROVINCE[index];
        }
        return p;
    }

    public String getCivilStatus() {
        String end = String.valueOf(id_num.charAt(9));

        if (end.equalsIgnoreCase("V")) {
            return StringValues.CIVIL[0];
        } else if (end.equalsIgnoreCase("X")) {
            return StringValues.CIVIL[1];
        }
        return null;
    }

    /*private void setMonthDays(int year) {
//        LocalDate d = LocalDate.of(2000, 8, 1);
//        YearMonth d2 = YearMonth.of(2000,8);
        for (int i = 0; i < 12; i++) {
            Calendar calendar = new GregorianCalendar(year, i, 1);
            MONTH[i] = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            System.out.println((i) + MONTH[i]);
        }
    }*/
}