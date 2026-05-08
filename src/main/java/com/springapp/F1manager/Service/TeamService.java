package com.springapp.F1manager.Service;


import com.springapp.F1manager.Dto.Team.TeamRequestDto;
import com.springapp.F1manager.Dto.Team.TeamResponseDto;
import com.springapp.F1manager.Model.Driver;
import com.springapp.F1manager.Model.Team;
import com.springapp.F1manager.Repo.DriverRepository;
import com.springapp.F1manager.Repo.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {


    private final TeamRepository teamRepository;
    private final DriverRepository driverRepository;


    public TeamResponseDto createTeam(TeamRequestDto requestDto) {
        Team team = findTeamById(requestDto.getTeamId());

        Driver driver = Driver.builder()
                .name(requestDto.getName())
                .carNumber.getcarNumber())
                .nationality(nationality)
                .localDate (birthdate)

    }

    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }

    public TeamRepository getTeamRepository() {
        return teamRepository;
    }

    public void setTeamRepository(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
}
