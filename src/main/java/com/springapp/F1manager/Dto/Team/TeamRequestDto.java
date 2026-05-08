package com.springapp.F1manager.Dto.Team;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamRequestDto {

    @NotBlank(message = "Team name is required!")
    private String name;
    private String country;
    private Integer foundedYear;

}
