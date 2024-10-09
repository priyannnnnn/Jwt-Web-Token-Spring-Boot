package com.supricode.security.timer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/timer")
@RequiredArgsConstructor
public class TimerController {

//    @Autowired
//    private TimerRepository timerRepository;
    private final TimeService timeService;

    //Get all Timer
    @GetMapping
    public ResponseEntity<List <Timer>> findAllTimer(){
        return ResponseEntity.ok(timeService.findAll());
    }

}
