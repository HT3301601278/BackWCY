package org.example.backwcy.controller;

import org.example.backwcy.entity.Device;
import org.example.backwcy.service.DeviceService;
import org.example.backwcy.exception.DeviceNameAlreadyExistsException;
import org.example.backwcy.exception.InvalidInputException;
import org.example.backwcy.dto.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @PostMapping
    public ResponseEntity<?> addDevice(@RequestParam String name) {
        try {
            Device device = deviceService.addDevice(name);
            return ResponseEntity.ok(device);
        } catch (DeviceNameAlreadyExistsException | InvalidInputException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getClass().getSimpleName().toUpperCase(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices() {
        List<Device> devices = deviceService.getAllDevices();
        return ResponseEntity.ok(devices);
    }

    @GetMapping("/{deviceId}")
    public ResponseEntity<?> getDeviceById(@PathVariable Long deviceId) {
        try {
            Device device = deviceService.getDeviceById(deviceId);
            return ResponseEntity.ok(device);
        } catch (InvalidInputException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getClass().getSimpleName().toUpperCase(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}