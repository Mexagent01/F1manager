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


        return mapToDto(savedTeam);
    }

    public List<TeamResponseDto> getAllTeams() {
        return teamRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    /**
     * Ez a metódus végzi az "átcsomagolást" az adatbázis modellből
     * a kifelé küldött DTO objektumba.
     */
    private TeamResponseDto mapToDto(Team team) {
        return TeamResponseDto.builder()
                .name(team.getName())
                .country(team.getCountry())
                .foundedYear(team.getFoundedYear())
                .build();
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }
}