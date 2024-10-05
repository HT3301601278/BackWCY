package org.example.backwcy.service;

import org.example.backwcy.entity.Device;

import java.util.List;

public interface DeviceService {
    Device addDevice(String name);
    List<Device> getAllDevices();
    Device getDeviceById(Long deviceId);
}