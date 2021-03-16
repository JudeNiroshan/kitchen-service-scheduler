package com.verimi.kitchenservice.repository;

import com.verimi.kitchenservice.entity.StaffMember;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StaffMemberRepository extends CrudRepository<StaffMember, Long> {
    List<StaffMember> findAll();
}
