package com.supricode.security.timer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeService {
    private final TimerRepository timerRepository;

    public List<Timer> findAll(){
        System.out.printf("Print =  ",timerRepository.toString());
        return timerRepository.findAll();
    }
}
