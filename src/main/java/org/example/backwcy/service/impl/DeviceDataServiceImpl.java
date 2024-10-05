package org.example.backwcy.service.impl;

import org.example.backwcy.dao.DeviceDataRepository;
import org.example.backwcy.entity.Device;
import org.example.backwcy.entity.DeviceData;
import org.example.backwcy.exception.InvalidInputException;
import org.example.backwcy.service.DeviceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceDataServiceImpl implements DeviceDataService {

    @Autowired
    private DeviceDataRepository deviceDataRepository;

    @Override
    public DeviceData addDeviceData(Device device, Double value) {
        if (device == null || value == null) {
            throw new InvalidInputException("设备和数值不能为空");
        }
        DeviceData deviceData = new DeviceData(device, value);
        return deviceDataRepository.save(deviceData);
    }

    @Override
    public List<DeviceData> getDeviceData(Device device) {
        if (device == null) {
            throw new InvalidInputException("设备不能为空");
        }
        return deviceDataRepository.findByDeviceOrderByTimestampDesc(device);
    }
}