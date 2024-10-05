package org.example.backwcy.dao;

import org.example.backwcy.entity.Device;
import org.example.backwcy.entity.DeviceData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceDataRepository extends JpaRepository<DeviceData, Long> {
    List<DeviceData> findByDeviceOrderByTimestampDesc(Device device);
}