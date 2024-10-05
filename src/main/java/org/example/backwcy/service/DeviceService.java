package org.example.backwcy.service;

import org.example.backwcy.entity.Device;
import org.example.backwcy.entity.User;

import java.util.List;

public interface DeviceService {
    Device addDevice(String name, User user);
    List<Device> getUserDevices(User user);
    Device getDeviceById(Long deviceId);  // 添加这行
}