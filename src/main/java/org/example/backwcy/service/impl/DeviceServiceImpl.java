package org.example.backwcy.service.impl;

import org.example.backwcy.dao.DeviceRepository;
import org.example.backwcy.entity.Device;
import org.example.backwcy.exception.DeviceNotFoundException;
import org.example.backwcy.exception.InvalidInputException;
import org.example.backwcy.exception.DeviceNameAlreadyExistsException;
import org.example.backwcy.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public Device addDevice(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidInputException("设备名称不能为空");
        }
        if (deviceRepository.existsByName(name)) {
            throw new DeviceNameAlreadyExistsException("设备名称 '" + name + "' 已存在");
        }
        Device device = new Device(name);
        return deviceRepository.save(device);
    }

    @Override
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    @Override
    public Device getDeviceById(Long deviceId) {
        if (deviceId == null) {
            throw new InvalidInputException("设备ID不能为空");
        }
        return deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException("设备不存在，ID: " + deviceId));
    }
}