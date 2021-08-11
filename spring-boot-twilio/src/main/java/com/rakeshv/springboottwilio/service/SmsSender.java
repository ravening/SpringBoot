package com.rakeshv.springboottwilio.service;

import com.rakeshv.springboottwilio.models.SmsRequest;

public interface SmsSender {

    void sendSms(SmsRequest smsRequest);
}
