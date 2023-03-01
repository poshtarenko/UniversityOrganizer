package com.nyanband.university_organizer.controller;

import com.nyanband.university_organizer.controller.util.ControllerUtils;
import com.nyanband.university_organizer.dto.SaveUserSettingDto;
import com.nyanband.university_organizer.dto.UpdateUserSettingDto;
import com.nyanband.university_organizer.service.UserSettingService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user_settings")
public class UserSettingController {

    private final UserSettingService userSettingService;

    @Autowired
    public UserSettingController(UserSettingService userSettingService) {
        this.userSettingService = userSettingService;

    }

    @PostMapping()
    @ApiOperation("Updated user settings")
    public ResponseEntity<?> updatedUserSettings(@RequestBody SaveUserSettingDto userSettingDto) {
        UpdateUserSettingDto updateUserSettingDto = new UpdateUserSettingDto(
                ControllerUtils.getUserId(),
                userSettingDto.getBreakTime(),
                userSettingDto.getLessonDuration()
        );
        userSettingService.update(updateUserSettingDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
