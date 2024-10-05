package org.example.backwcy.controller;

import org.example.backwcy.entity.Device;
import org.example.backwcy.entity.User;
import org.example.backwcy.service.DeviceService;
import org.example.backwcy.service.UserService;
import org.example.backwcy.exception.UserNotFoundException;
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

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> addDevice(@RequestParam String name, @RequestParam Long userId) {
        try {
            User user = userService.getUserById(userId);
            Device device = deviceService.addDevice(name, user);
            return ResponseEntity.ok(device);
        } catch (UserNotFoundException | DeviceNameAlreadyExistsException | InvalidInputException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getClass().getSimpleName().toUpperCase(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Device>> getUserDevices(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        List<Device> devices = deviceService.getUserDevices(user);
        return ResponseEntity.ok(devices);
    }
}