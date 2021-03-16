package com.verimi.kitchenservice.controller;

import java.util.Date;
import java.util.List;

import com.verimi.kitchenservice.annotation.ValidDate;
import com.verimi.kitchenservice.entity.DayOfService;
import com.verimi.kitchenservice.service.WeekPlanService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class WeekPlanController {

    private final WeekPlanService weekPlanService;

    public WeekPlanController(WeekPlanService weekPlanService) {
        this.weekPlanService = weekPlanService;
    }

    @GetMapping("/week_plan")
    public List<DayOfService> generateWeekPlan(
        @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") @ValidDate Date requestedDate) {
        return weekPlanService.generateWeekPlan( requestedDate );
    }

}
