package com.nyanband.university_organizer.controller;

import com.nyanband.university_organizer.controller.util.ControllerUtils;
import com.nyanband.university_organizer.dto.SaveScheduleDto;
import com.nyanband.university_organizer.dto.SaveScheduleItemDto;
import com.nyanband.university_organizer.dto.ScheduleDto;
import com.nyanband.university_organizer.dto.ScheduleItemDto;
import com.nyanband.university_organizer.exception.AccessDeniedException;
import com.nyanband.university_organizer.service.ScheduleItemService;
import com.nyanband.university_organizer.service.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@Api("Schedule API")
public class ScheduleController {
    private ScheduleItemService scheduleItemService;
    private ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleItemService scheduleItemService, ScheduleService scheduleService) {
        this.scheduleItemService = scheduleItemService;
        this.scheduleService = scheduleService;
    }

    @PostMapping("/create")
    @ApiOperation("Create new schedule")
    public ResponseEntity<?> createSchedule(@RequestParam("semesterId") Long id){
        //if(!scheduleService.isScheduleExist(id)){
            SaveScheduleDto saveScheduleDto = new SaveScheduleDto(id);
            scheduleService.save(saveScheduleDto);
            return new ResponseEntity<>(HttpStatus.OK);
        //}
//        else {
//            throw  new AccessDeniedException("schedule with this id  already exist");
//        }


    }

    @PostMapping
    @ApiOperation("Add new scheduleItem")
    public ResponseEntity<?> addScheduleItem(@RequestBody SaveScheduleItemDto saveScheduleItemDto){
        if(scheduleService.isScheduleBelongToUser(ControllerUtils.getUserId(), saveScheduleItemDto.getScheduleId())) {
            scheduleItemService.save(saveScheduleItemDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            throw new AccessDeniedException("Schedule does not exist or user dont have access on it");
        }
    }

    @GetMapping("/{id}")
    @ApiOperation("Get scheduleItems by schedule id")
    public List<ScheduleItemDto> getScheduleItem(@PathVariable Long id){
        long userId = ControllerUtils.getUserId();
        if(scheduleService.isScheduleBelongToUser(userId,id) && scheduleService.isScheduleExist(id)) {
            return scheduleService.getScheduleItemsForSchedule(id, userId);
        } else{
            throw new AccessDeniedException("Schedule does not exist or user dont have access on it");
        }
    }
}
