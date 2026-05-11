package com.springapp.F1manager.Dto.Team;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamResponseDto {

    private String name;
    private String country;
    private Integer foundedYear;
}
