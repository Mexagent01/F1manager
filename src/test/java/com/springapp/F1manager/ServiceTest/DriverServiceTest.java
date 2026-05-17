package com.springapp.F1manager.ServiceTest;

import com.springapp.F1manager.Dto.Driver.DriverRequestDto;
import com.springapp.F1manager.Dto.Driver.DriverResponseDto;
import com.springapp.F1manager.Model.Driver;
import com.springapp.F1manager.Repo.DriverRepository;
import com.springapp.F1manager.Service.DriverService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DriverServiceTest {

    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private DriverService driverService;

    @Test
    @DisplayName("Versenyző sikeres létrehozása")
    void shouldCreateDriver() {

        // Given
        DriverRequestDto requestDto = DriverRequestDto.builder()
                .name("Max Verstappen")
                .carNumber(1)
                .nationality("Dutch")
                .birthDate(LocalDate.of(1997, 9, 30))
                .build();

        Driver savedDriver = Driver.builder()
                .id(1L)
                .name("Max Verstappen")
                .carNumber(1)
                .nationality("Dutch")
                .birthDate(LocalDate.of(1997, 9, 30))
                .build();

        when(driverRepository.save(any(Driver.class)))
                .thenReturn(savedDriver);

        // When
        DriverResponseDto response = driverService.createDriver(requestDto);

        // Then
        assertNotNull(response);
        assertEquals("Max Verstappen", response.getName());

        verify(driverRepository, times(1))
                .save(any(Driver.class));
    }

    @Test
    @DisplayName("Összes versenyző lekérése")
    void shouldGetAllDrivers() {

        // Given
        Driver driver1 = Driver.builder()
                .id(1L)
                .name("Lewis Hamilton")
                .build();

        Driver driver2 = Driver.builder()
                .id(2L)
                .name("Charles Leclerc")
                .build();

        when(driverRepository.findAll())
                .thenReturn(List.of(driver1, driver2));

        // When
        List<DriverResponseDto> drivers =
                driverService.getAllDrivers();

        // Then
        assertEquals(2, drivers.size());
        assertEquals("Lewis Hamilton", drivers.get(0).getName());
        assertEquals("Charles Leclerc", drivers.get(1).getName());
    }

    @Test
    @DisplayName("Versenyző lekérése ID alapján - Sikeres")
    void shouldGetDriverById() {

        // Given
        Long driverId = 1L;

        Driver driver = Driver.builder()
                .id(driverId)
                .name("Lando Norris")
                .build();

        when(driverRepository.findById(driverId))
                .thenReturn(Optional.of(driver));

        // When
        DriverResponseDto response =
                driverService.getAllDriversById(driverId);

        // Then
        assertNotNull(response);
        assertEquals("Lando Norris", response.getName());
    }

    @Test
    @DisplayName("Versenyző lekérése ID alapján - Hiba")
    void shouldThrowExceptionWhenDriverNotFound() {

        // Given
        Long driverId = 99L;

        when(driverRepository.findById(driverId))
                .thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception =
                assertThrows(RuntimeException.class, () -> {
                    driverService.getAllDriversById(driverId);
                });

        assertEquals("Driver not found",
                exception.getMessage());
    }

    @Test
    @DisplayName("Versenyző adatainak frissítése")
    void shouldUpdateDriver() {

        // Given
        Long driverId = 1L;

        Driver existingDriver = Driver.builder()
                .id(driverId)
                .name("Old Driver")
                .carNumber(99)
                .nationality("Old")
                .build();

        DriverRequestDto updateDto = DriverRequestDto.builder()
                .name("Oscar Piastri")
                .carNumber(81)
                .nationality("Australian")
                .birthDate(LocalDate.of(2001, 4, 6))
                .build();

        when(driverRepository.findById(driverId))
                .thenReturn(Optional.of(existingDriver));

        when(driverRepository.save(any(Driver.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        // When
        DriverResponseDto response =
                driverService.updateDriver(driverId, updateDto);

        // Then
        assertEquals("Oscar Piastri", response.getName());
        assertEquals(81, response.getCarNumber());

        verify(driverRepository)
                .save(existingDriver);
    }

    @Test
    @DisplayName("Versenyző törlése")
    void shouldDeleteDriver() {

        // Given
        Long driverId = 1L;

        doNothing().when(driverRepository)
                .deleteById(driverId);

        // When
        driverService.deleteDriver(driverId);

        // Then
        verify(driverRepository, times(1))
                .deleteById(driverId);
    }
}