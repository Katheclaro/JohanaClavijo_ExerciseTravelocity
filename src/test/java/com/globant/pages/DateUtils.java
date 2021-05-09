package com.globant.pages;

import java.util.Calendar;
import java.util.TimeZone;

public class DateUtils {

    /**
     * Method to calculate the trip days
     * @return
     */

    public static String getCurrentDay (){

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
        String todayStr = Integer.toString(todayInt);

        return todayStr;
    }
}
