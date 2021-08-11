package com.rakeshv.springboottwilio.service;

import com.rakeshv.springboottwilio.models.SmsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    private SmsSender smsSender;

    @Autowired
    public SmsService(@Qualifier("twilio") TwilioSmsSender smsSender) {
        this.smsSender = smsSender;
    }

    public void sendSms(SmsRequest request) {
        this.smsSender.sendSms(request);
    }
}
