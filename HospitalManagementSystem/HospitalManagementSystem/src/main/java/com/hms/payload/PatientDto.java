package com.hms.payload;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PatientDto {
    private long id;
    private String name;
    private int age;
    private String email;
    private String bloodGroup;
    private double fees;
    private String urgency;
}
