package com.rakeshv.springboottwilio.controllers;

import com.rakeshv.springboottwilio.models.SmsRequest;
import com.rakeshv.springboottwilio.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @PostMapping
    public ResponseEntity<String> sendSms(@Valid @RequestBody SmsRequest smsRequest) {
        this.smsService.sendSms(smsRequest);
        return ResponseEntity.ok("Sms sent");
    }
}
