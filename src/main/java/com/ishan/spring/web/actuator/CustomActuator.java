package com.ishan.spring.web.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "custom-info") // {{baseURL}}/actuator/custom-info to be used => not no / in the id 
public class CustomActuator {

    private long startTime = System.currentTimeMillis();

    @ReadOperation
    public String getUpTime(){
        long currentTime = System.currentTimeMillis();
        long difference = currentTime - startTime;
        long minutes = (difference/1000)/60;
        long seconds = (difference/1000)%60;
        return String.format("It has been %d minutes and %d seconds since the application started", minutes, seconds);
    }
}
