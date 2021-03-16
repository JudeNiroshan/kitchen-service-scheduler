package com.verimi.kitchenservice.repository;

import com.verimi.kitchenservice.entity.DayOfService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface DayOfServiceRepository extends CrudRepository<DayOfService, Long> {
    DayOfService findByCalendarDay(Date calendarDay);

    @Transactional
    void deleteByCalendarDayIn(List<Date> calendarDays);
}
