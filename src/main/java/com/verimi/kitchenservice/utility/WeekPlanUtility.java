package com.verimi.kitchenservice.utility;

import java.util.Calendar;
import java.util.Date;

import static com.verimi.kitchenservice.constant.WeekPlanConstants.MAXIMUM_PAST_OR_FUTURE_WEEKPLAN_WEEK_COUNT;

public class WeekPlanUtility {

    public static Date getOneWeekPastDate(){
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.WEEK_OF_MONTH, -MAXIMUM_PAST_OR_FUTURE_WEEKPLAN_WEEK_COUNT);
        date = c.getTime();
        return date;
    }

    public static Date getOneWeekFutureDate(){
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.WEEK_OF_MONTH, MAXIMUM_PAST_OR_FUTURE_WEEKPLAN_WEEK_COUNT);
        date = c.getTime();
        return date;
    }

    public static Date[] getDaysOfRequestedWeek(Date refDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(refDate);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.getInstance().getFirstDayOfWeek());
        Date[] daysOfWeek = new Date[5];
        for (int i = 0; i < 5; i++) {
            daysOfWeek[i] = calendar.getTime();
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return daysOfWeek;
    }
}
