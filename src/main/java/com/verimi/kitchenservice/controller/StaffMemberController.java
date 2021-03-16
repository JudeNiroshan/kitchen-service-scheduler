package com.verimi.kitchenservice.controller;

import java.util.List;

import com.verimi.kitchenservice.entity.StaffMember;
import com.verimi.kitchenservice.service.StaffMemberService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaffMemberController {

    private final StaffMemberService staffMemberService;

    public StaffMemberController(StaffMemberService staffMemberService) {
        this.staffMemberService = staffMemberService;
    }

    @GetMapping("/staff_members")
    List<StaffMember> findAll() {
        return staffMemberService.findAllStaffMembers();
    }

    @PostMapping("/staff_members")
    StaffMember newStaffMember(@RequestBody StaffMember newStaffMember) {
        return staffMemberService.createStaffMember( newStaffMember );
    }

    @GetMapping("/staff_members/{id}")
    StaffMember findById(@PathVariable Long id) {
        return staffMemberService.findById( id );
    }

    @PutMapping("/staff_members/{id}")
    StaffMember updateStaffMember(@PathVariable Long id, @RequestBody StaffMember newStaffMember) {
        return staffMemberService.updateStaffMember( id, newStaffMember );
    }

    @DeleteMapping("/staff_members/{id}")
    void deleteStaffMember(@PathVariable Long id) {
        staffMemberService.deleteStaffMember( id );
    }

}
