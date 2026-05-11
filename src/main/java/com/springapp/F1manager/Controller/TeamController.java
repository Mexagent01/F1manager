package com.springapp.F1manager.Controller;

import com.springapp.F1manager.Dto.Team.TeamRequestDto;
import com.springapp.F1manager.Dto.Team.TeamResponseDto;
import com.springapp.F1manager.Service.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamResponseDto createTeam(@Valid @RequestBody TeamRequestDto requestDto){
        return teamService.createTeam(requestDto);
    }

    @GetMapping
    public List<TeamResponseDto> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    public TeamResponseDto getAllTeamsById(@PathVariable Long id) {
        return teamService.getAllTeamsById(id);
    }

    @PutMapping("/{id}")
    public TeamResponseDto updateTeam(@PathVariable Long id,
                                          @Valid @RequestBody TeamRequestDto requestDto) {
        return teamService.updateTeam(id,requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
    }
}
