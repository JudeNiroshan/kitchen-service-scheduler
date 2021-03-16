package com.verimi.kitchenservice.service;

import java.util.Date;
import java.util.List;

import com.verimi.kitchenservice.entity.StaffMember;
import com.verimi.kitchenservice.exception.StaffMemberNotFoundException;
import com.verimi.kitchenservice.repository.DayOfServiceRepository;
import com.verimi.kitchenservice.repository.StaffMemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StaffMemberService {

    private static final Logger logger = LoggerFactory.getLogger( StaffMemberService.class);

    private final StaffMemberRepository staffMemberRepository;

    private final DayOfServiceRepository dayOfServiceRepository;

    public StaffMemberService(StaffMemberRepository staffMemberRepository,
                              DayOfServiceRepository dayOfServiceRepository) {
        this.staffMemberRepository = staffMemberRepository;
        this.dayOfServiceRepository = dayOfServiceRepository;
    }

    public StaffMember createStaffMember(StaffMember newStaffMember) {
        return staffMemberRepository.save(newStaffMember);
    }

    public List<StaffMember> findAllStaffMembers() {
        List<StaffMember> all = staffMemberRepository.findAll();
        for(StaffMember m : all) {
            logger.debug("{} {}, last time {}", m.getFirstName(), m.getLastName(), m.getLastService());
        }
        return all;
    }

    public StaffMember findById(Long id) {
        return staffMemberRepository.findById(id)
            .orElseThrow(() -> new StaffMemberNotFoundException(id));
    }
    public StaffMember updateStaffMember(Long staffMemberId, StaffMember newStaffMember) {

        return staffMemberRepository.findById(staffMemberId)
            .map(staffMember -> this.replaceStaffMemberAppropiately(newStaffMember, staffMember) )
            .orElseThrow(() -> new StaffMemberNotFoundException(staffMemberId));
    }

    private StaffMember replaceStaffMemberAppropiately(StaffMember newStaffMember, StaffMember oldStaffMember){
        if ( newStaffMember.getDayOffs().size() != 0 ) {
            dayOfServiceRepository.deleteByCalendarDayIn( newStaffMember.getDayOffs() );

            final List<Date> updatedDayOffs = newStaffMember.getDayOffs();
            // to make sure no duplicates are available
            updatedDayOffs.removeAll( oldStaffMember.getDayOffs() );
            updatedDayOffs.addAll( oldStaffMember.getDayOffs() );
            oldStaffMember.setDayOffs( updatedDayOffs );
        }

        oldStaffMember.setLastService(newStaffMember.getLastService());
        return staffMemberRepository.save(oldStaffMember);
    }

    public void deleteStaffMember( Long id) {
        staffMemberRepository.deleteById(id);
    }
}
