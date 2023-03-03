package com.nyanband.university_organizer.controller;

import com.nyanband.university_organizer.controller.util.ControllerUtils;
import com.nyanband.university_organizer.dto.SaveScheduleDto;
import com.nyanband.university_organizer.dto.SaveScheduleItemDto;
import com.nyanband.university_organizer.dto.ScheduleDto;
import com.nyanband.university_organizer.dto.ScheduleItemDto;
import com.nyanband.university_organizer.exception.AccessDeniedException;
import com.nyanband.university_organizer.service.DisciplineService;
import com.nyanband.university_organizer.service.ScheduleItemService;
import com.nyanband.university_organizer.service.ScheduleService;
import com.nyanband.university_organizer.service.SemesterService;
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
@Api(description = "Schedule API")

public class ScheduleController {
    private  final ScheduleItemService scheduleItemService;
    private  final ScheduleService scheduleService;
    private  final DisciplineService disciplineService;
    private  final SemesterService semesterService;


    @Autowired
    public ScheduleController(ScheduleItemService scheduleItemService, ScheduleService scheduleService, DisciplineService disciplineService,SemesterService semesterService) {
        this.scheduleItemService = scheduleItemService;
        this.scheduleService = scheduleService;
        this.disciplineService = disciplineService;
        this.semesterService = semesterService;
    }

    @PostMapping
    @ApiOperation("Create new schedule")
    public ResponseEntity<?> createSchedule(@RequestParam("semesterId") Long id) {
        long userId = ControllerUtils.getUserId();
        if (semesterService.isSemesterBelongsToUser(id, userId) && !scheduleService.isSemesterHasSchedule(id)) {
            SaveScheduleDto saveScheduleDto = new SaveScheduleDto(id);
            scheduleService.save(saveScheduleDto);
            return ControllerUtils.okResponse();
        } else throw new AccessDeniedException("Schedule already exist or semester with this id doesn't exists");
    }

    @PostMapping("/addItem")
    @ApiOperation("Add new scheduleItem")
    public ResponseEntity<?> addScheduleItem(@RequestBody SaveScheduleItemDto saveScheduleItemDto) {
        if (scheduleService.isScheduleBelongToUser(ControllerUtils.getUserId(), saveScheduleItemDto.getScheduleId())
                && disciplineService.isDisciplineExistById(saveScheduleItemDto.getDisciplineId())) {
            scheduleItemService.save(saveScheduleItemDto);
            return ControllerUtils.okResponse();
        } else {
            throw new AccessDeniedException("Schedule does not exists or discipline does not exists");
        }
    }

    @GetMapping("/{id}")
    @ApiOperation("Get scheduleItems by schedule id")
    public List<ScheduleItemDto> getScheduleItem(@PathVariable Long id) {
        long userId = ControllerUtils.getUserId();
        if (scheduleService.isScheduleBelongToUser(userId, id) && scheduleService.isScheduleExist(id)) {
            return scheduleItemService.getScheduleItemsForSchedule(id, userId);
        } else {
            throw new AccessDeniedException("Schedule does not exists or user dont have access on it");
        }
    }
    @ApiOperation("Delete schedule")
    @PostMapping("/delete")
    public  ResponseEntity<?> deleteSchedule(@RequestParam("scheduleId") Long scheduleId){
        long userId = ControllerUtils.getUserId();
        if(scheduleService.isScheduleBelongToUser(userId,scheduleId)){
            scheduleService.delete(scheduleId);
            return ControllerUtils.okResponse();
        }
        else throw  new AccessDeniedException("Schedule does not exists or user dont have access on it");
    }

    @ApiOperation("Delete schedule item")
    @PostMapping("/scheduleItem/delete")
    public ResponseEntity<?> deleteScheduleItem(@RequestParam("scheduleItemId") Long scheduleItemId){
        long userId = ControllerUtils.getUserId();
        if(scheduleItemService.isScheduleItemBelongsToUser(userId,scheduleItemId)){
            scheduleItemService.delete(scheduleItemId);
            return ControllerUtils.okResponse();
        }
      else throw  new AccessDeniedException("ScheduleItem does not exists or user dont have access on it");
    }

}
