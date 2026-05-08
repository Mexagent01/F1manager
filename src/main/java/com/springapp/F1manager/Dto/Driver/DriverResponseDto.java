package com.springapp.F1manager.Dto.Driver;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverResponseDto {

    private String name;
    private int carNumber;
    private String nationality;
    private LocalDate birthDate;
}
