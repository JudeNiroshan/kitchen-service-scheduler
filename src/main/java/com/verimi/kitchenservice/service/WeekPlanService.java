package com.verimi.kitchenservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.verimi.kitchenservice.entity.DayOfService;
import com.verimi.kitchenservice.entity.StaffMember;
import com.verimi.kitchenservice.repository.DayOfServiceRepository;
import com.verimi.kitchenservice.repository.StaffMemberRepository;
import org.springframework.stereotype.Service;

import static com.verimi.kitchenservice.utility.WeekPlanUtility.getDaysOfRequestedWeek;

@Service
public class WeekPlanService {

    private final StaffMemberRepository staffMemberRepository;

    private final DayOfServiceRepository dayOfServiceRepository;

    public WeekPlanService(StaffMemberRepository staffMemberRepository,
                           DayOfServiceRepository dayOfServiceRepository) {
        this.staffMemberRepository = staffMemberRepository;
        this.dayOfServiceRepository = dayOfServiceRepository;
    }

    public List<DayOfService> generateWeekPlan(Date requestedDate) {
        List<DayOfService> dayOfServiceList = new ArrayList<>(5);
        Date[] daysOfRequestedWeek = getDaysOfRequestedWeek( requestedDate );

        for ( Date day : daysOfRequestedWeek ) {
            DayOfService dayOfService = dayOfServiceRepository.findByCalendarDay( day );
            if ( dayOfService == null ) {
                dayOfService = dayOfServiceRepository.save( new DayOfService( day ) );
            }
            if ( dayOfService.getStaffMember() == null ) {
                StaffMember staffMember = findNextStaffMemberForService( day );
                dayOfService.setStaffMember( staffMember );
                staffMember.setLastService( dayOfService.getCalendarDay() );
                staffMemberRepository.save( staffMember );
            }
            dayOfServiceRepository.save( dayOfService );
            dayOfServiceList.add( dayOfService );
        }

        return dayOfServiceList;
    }

    private StaffMember findNextStaffMemberForService(Date day) {
        List<StaffMember> allStaffMembers = staffMemberRepository.findAll();

        allStaffMembers.sort( (staffMember1, staffMember2) -> {
            if ( staffMember1.getLastService() == null ) {
                return -1;
            }
            if ( staffMember2.getLastService() == null ) {
                return 1;
            }
            return staffMember1.getLastService().compareTo( staffMember2.getLastService() );
        } );

        return allStaffMembers.stream()
            .filter( staffMember -> staffMember.getDayOffs()
                .stream()
                .noneMatch( dayOff -> dayOff.equals( day ) ) )
            .findFirst()
            .orElse( null );
    }
}
