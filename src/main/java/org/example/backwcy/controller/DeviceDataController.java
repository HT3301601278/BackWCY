package org.example.backwcy.controller;

import org.example.backwcy.entity.Device;
import org.example.backwcy.entity.DeviceData;
import org.example.backwcy.service.DeviceDataService;
import org.example.backwcy.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/device-data")
public class DeviceDataController {

    @Autowired
    private DeviceDataService deviceDataService;

    @Autowired
    private DeviceService deviceService;

    @PostMapping
    public ResponseEntity<DeviceData> addDeviceData(@RequestParam Long deviceId, @RequestParam Double value) {
        Device device = deviceService.getDeviceById(deviceId);
        DeviceData deviceData = deviceDataService.addDeviceData(device, value);
        return ResponseEntity.ok(deviceData);
    }

    @GetMapping("/device/{deviceId}")
    public ResponseEntity<List<DeviceData>> getDeviceData(@PathVariable Long deviceId) {
        Device device = deviceService.getDeviceById(deviceId);
        List<DeviceData> deviceDataList = deviceDataService.getDeviceData(device);
        return ResponseEntity.ok(deviceDataList);
    }
}