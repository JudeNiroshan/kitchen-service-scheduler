package com.verimi.kitchenservice.validator;

import java.util.Calendar;
import java.util.Date;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.verimi.kitchenservice.annotation.ValidDate;

public class DateValidator implements ConstraintValidator<ValidDate, Date> {

    @Override
    public boolean isValid(Date requestedDate, ConstraintValidatorContext context) {
        return weekplanScheduleAvailability(requestedDate);
    }

    /**
     * Week Plan is only generated for last 6 weeks and future 6 weeks.
     * Other dates we do not generate the week plan
     * @param userInputDate date that needs to be validated
     * @return valid user input date or not
     */
    private boolean weekplanScheduleAvailability(Date userInputDate) {
        Calendar sixWeeksAgo = Calendar.getInstance();
        sixWeeksAgo.add( Calendar.WEEK_OF_MONTH, -6 );

        Calendar sixWeeksInFuture = Calendar.getInstance();
        sixWeeksInFuture.add( Calendar.WEEK_OF_MONTH, 6 );

        return userInputDate.after( sixWeeksAgo.getTime() ) && userInputDate.before( sixWeeksInFuture.getTime() );
    }
}
