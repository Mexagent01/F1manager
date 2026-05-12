package com.springapp.F1manager.ServiceTest;

import com.springapp.F1manager.Dto.Team.TeamRequestDto;
import com.springapp.F1manager.Dto.Team.TeamResponseDto;
import com.springapp.F1manager.Model.Team;
import com.springapp.F1manager.Repo.TeamRepository;
import com.springapp.F1manager.Service.TeamService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService;

    @Test
    @DisplayName("Csapat sikeres létrehozása")
    void shouldCreateTeam() {
        // Given
        TeamRequestDto requestDto = TeamRequestDto.builder()
                .name("Ferrari")
                .country("Italy")
                .foundedYear(1950)
                .build();

        Team savedTeam = Team.builder()
                .id(1L)
                .name("Ferrari")
                .country("Italy")
                .foundedYear(1950)
                .build();

        when(teamRepository.save(any(Team.class))).thenReturn(savedTeam);

        // When
        TeamResponseDto response = teamService.createTeam(requestDto);

        // Then
        assertNotNull(response);
        assertEquals("Ferrari", response.getName());
        verify(teamRepository, times(1)).save(any(Team.class));
    }

    @Test
    @DisplayName("Összes csapat lekérése")
    void shouldGetAllTeams() {
        // Given
        Team team1 = Team.builder().id(1L).name("Mercedes").build();
        Team team2 = Team.builder().id(2L).name("Red Bull").build();
        when(teamRepository.findAll()).thenReturn(List.of(team1, team2));

        // When
        List<TeamResponseDto> teams = teamService.getAllTeams();

        // Then
        assertEquals(2, teams.size());
        assertEquals("Mercedes", teams.get(0).getName());
        assertEquals("Red Bull", teams.get(1).getName());
    }

    @Test
    @DisplayName("Csapat lekérése ID alapján - Sikeres")
    void shouldGetTeamById() {
        // Given
        Long teamId = 1L;
        Team team = Team.builder()
                .id(teamId)
                .name("McLaren")
                .build();
        when(teamRepository.findById(teamId)).thenReturn(Optional.of(team));

        // When
        TeamResponseDto response = teamService.getTeamById(teamId);

        // Then
        assertNotNull(response);
        assertEquals("McLaren", response.getName());
    }

    @Test
    @DisplayName("Csapat lekérése ID alapján - Hiba (nem található)")
    void shouldThrowExceptionWhenTeamNotFound() {
        // Given
        Long teamId = 99L;
        when(teamRepository.findById(teamId)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            teamService.getTeamById(teamId);
        });

        assertEquals("Team not found", exception.getMessage());
    }

    @Test
    @DisplayName("Csapat adatainak frissítése")
    void shouldUpdateTeam() {
        // Given
        Long teamId = 1L;
        Team existingTeam = Team.builder()
                .id(teamId)
                .name("Old Name")
                .country("Old Country")
                .build();

        TeamRequestDto updateDto = TeamRequestDto.builder()
                .name("New Name")
                .country("New Country")
                .foundedYear(2024)
                .build();

        when(teamRepository.findById(teamId)).thenReturn(Optional.of(existingTeam));
        when(teamRepository.save(any(Team.class))).thenAnswer(i -> i.getArguments()[0]);

        // When
        TeamResponseDto response = teamService.updateTeam(teamId, updateDto);

        // Then
        assertEquals("New Name", response.getName());
        assertEquals("New Country", response.getCountry());
        verify(teamRepository).save(existingTeam);
    }

    @Test
    @DisplayName("Csapat törlése")
    void shouldDeleteTeam() {
        // Given
        Long teamId = 1L;
        doNothing().when(teamRepository).deleteById(teamId);

        // When
        teamService.deleteTeam(teamId);

        // Then
        verify(teamRepository, times(1)).deleteById(teamId);
    }
}