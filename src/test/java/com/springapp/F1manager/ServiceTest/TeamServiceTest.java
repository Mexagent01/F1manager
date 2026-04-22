package com.springapp.F1manager.ServiceTest;

import com.springapp.F1manager.Model.Team;
import com.springapp.F1manager.Repo.TeamRepository;
import com.springapp.F1manager.Service.TeamService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService;

    @Test
    void testGetAllTeams() {
        // Mock adatok előkészítése
        Team team = new Team();
        team.setName("Red Bull");
        when(teamRepository.findAll()).thenReturn(List.of(team));

        // Metódus hívása
        List<Team> result = teamService.getAllTeams();

        // Ellenőrzés
        assertEquals(1, result.size());
        assertEquals("Red Bull", result.get(0).getName());
        verify(teamRepository, times(1)).findAll();
    }

    @Test
    void testSaveTeam() {
        Team team = new Team();
        team.setName("Mercedes");
        when(teamRepository.save(any(Team.class))).thenReturn(team);

        Team saved = teamService.saveTeam(team);

        assertNotNull(saved);
        assertEquals("Mercedes", saved.getName());
    }
}