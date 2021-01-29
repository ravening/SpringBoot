package com.rakeshv.ifinances.utils;

import com.rakeshv.ifinances.models.TimeTest;
import com.rakeshv.ifinances.models.User;
import com.rakeshv.ifinances.repositories.TimeTestRepository;
import com.rakeshv.ifinances.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class DbLoader implements CommandLineRunner {

    @Autowired
    TimeTestRepository timeTestRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        TimeTest timeTest = TimeTest.builder()
                .dateColumn(new Date())
                .timeColumn(new Date())
                .timestampColumn(new Date())
                .datetimeColumn(new Date())
                .sqlDateColumn(new java.sql.Date(new Date().getTime()))
                .sqlTimeColumn(new Time(new Date().getTime()))
                .sqlDateTimeColumn(new Timestamp(new Date().getTime()))
                .sqlTimestampColumn(new Timestamp(new Date().getTime())).build();
//        timeTestRepository.save(timeTest);

        User user = User.builder()
                .createdBy("Kevin")
                .birthDate(getBirthday())
                .createdDate(new Date())
                .emailAddress("kmb385@yahoo.com")
                .firstName("Kevin")
                .lastName("Bowersox")
                .lastUpdatedBy("kevin")
                .lastUpdatedDate(new Date()).build();
//        userRepository.save(user);

        List<User> users = userRepository.findAll();

        users.forEach(u -> System.out.println(u.toString()));
    }

    private static Date getBirthday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1987);
        calendar.set(Calendar.MONTH, 6);
        calendar.set(Calendar.DATE, 19);

        return calendar.getTime();
    }
}
