package com.pluralsight.kafka.consumer.service;

import com.pluralsight.kafka.consumer.enums.UserId;
import com.pluralsight.kafka.consumer.model.InternalUser;

import java.util.HashMap;
import java.util.Map;

public class UserDB {

    private static Map<String, InternalUser> users = new HashMap<>();

    static {
        users.put(UserId.ABC123.toString(), new InternalUser(UserId.ABC123));
        users.put(UserId.ABC321.toString(), new InternalUser(UserId.ABC321));
        users.put(UserId.CBA123.toString(), new InternalUser(UserId.CBA123));
        users.put(UserId.CBA321.toString(), new InternalUser(UserId.CBA321));
        users.put(UserId.A1B2C3.toString(), new InternalUser(UserId.A1B2C3));
    }

    public InternalUser findByID(String id) {
        return users.get(id);
    }

    public void save(InternalUser internalUser) {
        users.put(internalUser.getUserId().toString(), internalUser);
    }
}
