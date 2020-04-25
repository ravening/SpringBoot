package com.rakeshv.springwebfluxmongodb.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeEvent {
    private Employee employee;
    private Date date;
}
