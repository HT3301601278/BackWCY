package org.example.backwcy.service;

import org.example.backwcy.entity.Device;
import org.example.backwcy.entity.DeviceData;

import java.util.List;

public interface DeviceDataService {
    DeviceData addDeviceData(Device device, Double value);
    List<DeviceData> getDeviceData(Device device);
}