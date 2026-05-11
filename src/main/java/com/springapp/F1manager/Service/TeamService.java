package com.springapp.F1manager.Service;

import com.springapp.F1manager.Dto.Team.TeamRequestDto;
import com.springapp.F1manager.Dto.Team.TeamResponseDto;
import com.springapp.F1manager.Model.Team;
import com.springapp.F1manager.Repo.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamResponseDto createTeam(TeamRequestDto requestDto) {

        Team team = Team.builder()
                .name(requestDto.getName())
                .country(requestDto.getCountry())
                .foundedYear(requestDto.getFoundedYear())
                .build();


        Team savedTeam = teamRepository.save(team);

        return mapToResponse(savedTeam);
    }

    public List<TeamResponseDto> getAllTeams() {
        return teamRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public TeamResponseDto getAllTeamsById(Long id) {

        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        return mapToResponse(team);
    }

    public TeamResponseDto updateTeam(Long id,
                                          TeamRequestDto requestDto) {

        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        team.setName(requestDto.getName());
        team.setCountry(requestDto.getCountry());
        team.setFoundedYear(requestDto.getFoundedYear());

        Team updatedTeam = teamRepository.save(team);

        return mapToResponse(updatedTeam);
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }

    private TeamResponseDto mapToResponse(Team team) {

        return TeamResponseDto.builder()
                .id(team.getId())
                .name(team.getName())
                .country(team.getCountry())
                .foundedYear(team.getFoundedYear())
                .build();
    }
}