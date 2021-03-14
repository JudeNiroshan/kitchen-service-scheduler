package com.verimi.kitchenservice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static com.verimi.kitchenservice.WeekPlanConstants.MAXIMUM_PAST_OR_FUTURE_WEEKPLAN_WEEK_COUNT;

@RestController
public class WeekPlanController {

    private final StaffMemberRepository staffMemberRepository;
    private final DayOfServiceRepository dayOfServiceRepository;

    public WeekPlanController(StaffMemberRepository staffMemberRepository, DayOfServiceRepository dayOfServiceRepository) {
        this.staffMemberRepository = staffMemberRepository;
        this.dayOfServiceRepository = dayOfServiceRepository;
    }

    @GetMapping("/week_plan")
    public List<DayOfService> all(@RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date requestedDate) {
        List<DayOfService> dayOfServiceList = new ArrayList<>();

        if ( requestedDate.before( getOneWeekPastDate() ) || requestedDate.after( getOneWeekFutureDate() )) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "requested Date is too past or future. Please consider recent date");
        }
        Date[] daysOfRequestedWeek = getDaysOfRequestedWeek(requestedDate);
        for (Date day: daysOfRequestedWeek) {
            DayOfService dayOfService = dayOfServiceRepository.findByCalendarDay(day);
            if (dayOfService == null) {
                dayOfService = dayOfServiceRepository.save(new DayOfService(day));
            }
            if(dayOfService.getStaffMember() == null) {
                StaffMember staffMember = findNextStaffMemberForService();
                dayOfService.setStaffMember(staffMember);
                staffMember.setLastService(dayOfService.getCalendarDay());
                staffMemberRepository.save(staffMember);
            }
            dayOfServiceRepository.save(dayOfService);
            dayOfServiceList.add(dayOfService);
        }

        return dayOfServiceList;
    }

    private StaffMember findNextStaffMemberForService() {
        List<StaffMember> allStaffMembers = staffMemberRepository.findAll();

        allStaffMembers.sort( (staffMember1, staffMember2) -> {
            if(staffMember1.getLastService() == null) {
                return -1;
            }
            if(staffMember2.getLastService() == null) {
                return 1;
            }
            return staffMember1.getLastService().compareTo(staffMember2.getLastService());
        } );

        return allStaffMembers.get(0);
    }

    // TODO: 14.03.21 Can be moved to Utility class
    private Date getOneWeekPastDate(){
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.WEEK_OF_MONTH, -MAXIMUM_PAST_OR_FUTURE_WEEKPLAN_WEEK_COUNT);
        date = c.getTime();
        return date;
    }

    // TODO: 14.03.21 Can be moved to Utility class
    private Date getOneWeekFutureDate(){
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.WEEK_OF_MONTH, MAXIMUM_PAST_OR_FUTURE_WEEKPLAN_WEEK_COUNT);
        date = c.getTime();
        return date;
    }

    // TODO: 14.03.21 Can be moved to Utility class
    Date[] getDaysOfRequestedWeek(Date refDate) {
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
