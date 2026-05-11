package com.springapp.F1manager.Controller;


import com.springapp.F1manager.Dto.Driver.DriverRequestDto;
import com.springapp.F1manager.Dto.Driver.DriverResponseDto;
import com.springapp.F1manager.Service.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final  DriverService driverService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverResponseDto createDriver(@Valid @RequestBody DriverRequestDto requestDto){
        return driverService.createDriver(requestDto);
    }

    @GetMapping
    public List<DriverResponseDto> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    @GetMapping("/{id}")
    public DriverResponseDto getAllDriversById(@PathVariable Long id) {
        return driverService.getAllDriversById(id);
    }

    @PutMapping("/{id}")
    public DriverResponseDto updateDriver(@PathVariable Long id,
                                          @Valid @RequestBody DriverRequestDto requestDto) {
        return driverService.updateDriver(id,requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
    }
}
