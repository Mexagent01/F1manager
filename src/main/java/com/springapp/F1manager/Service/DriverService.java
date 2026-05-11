package com.springapp.F1manager.Service;

import com.springapp.F1manager.Dto.Driver.DriverRequestDto;
import com.springapp.F1manager.Dto.Driver.DriverResponseDto;
import com.springapp.F1manager.Model.Driver;
import com.springapp.F1manager.Repo.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverResponseDto createDriver(DriverRequestDto requestDto) {

        Driver driver = Driver.builder()
                .name(requestDto.getName())
                .carNumber(requestDto.getCarNumber())
                .nationality(requestDto.getNationality())
                .birthDate(requestDto.getBirthDate())
                .build();

        Driver savedDriver = driverRepository.save(driver);

        return mapToResponse(savedDriver);
    }

    public List<DriverResponseDto> getAllDrivers() {

        return driverRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public DriverResponseDto getAllDriversById(Long id) {

        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        return mapToResponse(driver);
    }

    public DriverResponseDto updateDriver(Long id,
                                          DriverRequestDto requestDto) {

        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        driver.setName(requestDto.getName());
        driver.setCarNumber(requestDto.getCarNumber());
        driver.setNationality(requestDto.getNationality());
        driver.setBirthDate(requestDto.getBirthDate());

        Driver updatedDriver = driverRepository.save(driver);

        return mapToResponse(updatedDriver);
    }

    public void deleteDriver(Long id) {

        driverRepository.deleteById(id);
    }

    private DriverResponseDto mapToResponse(Driver driver) {

        return DriverResponseDto.builder()
                .id(driver.getId())
                .name(driver.getName())
                .carNumber(driver.getCarNumber())
                .nationality(driver.getNationality())
                .birthDate(driver.getBirthDate())
                .build();
    }
}