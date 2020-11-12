package com.example.practice.view.officeview;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class OfficeUpdateDto {
    @NonNull
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String address;
    private String phone;
    private Boolean isActive;
}
