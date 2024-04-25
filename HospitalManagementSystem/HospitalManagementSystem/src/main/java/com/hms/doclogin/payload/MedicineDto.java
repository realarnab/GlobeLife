package com.hms.doclogin.payload;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class MedicineDto {
    private long id;
    private String drugName;
    private String stock;
}
