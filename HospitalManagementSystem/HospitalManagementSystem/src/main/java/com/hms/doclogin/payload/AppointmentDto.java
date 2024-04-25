package com.hms.doclogin.payload;

import lombok.Data;

@Data
public class AppointmentDto {
    private  long id;
    private String name;
    private String symptoms;
    private int age;
    private String number;
}
