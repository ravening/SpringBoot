package com.rakeshv.springboottwilio.service;

import com.rakeshv.springboottwilio.configuration.TwilioConfiguration;
import com.rakeshv.springboottwilio.models.SmsRequest;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("twilio")
@Slf4j
public class TwilioSmsSender implements SmsSender {

    @Autowired
    private TwilioConfiguration twilioConfiguration;

    @Override
    public void sendSms(SmsRequest smsRequest) {
        if (isValidPhoneNumber(smsRequest.getPhoneNumber())) {
            PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
            String message = smsRequest.getMessage();

            MessageCreator creator = Message.creator(to, from, message);
            creator.create();
            log.info("Sending sms {}" ,smsRequest);
        } else {
            throw new IllegalArgumentException("Invalid phone number : " + smsRequest.getPhoneNumber());
        }
    }

    private boolean isValidPhoneNumber(String phoneNumber) {

        return true;
    }
}
