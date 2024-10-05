package org.example.backwcy.dao;

import org.example.backwcy.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    boolean existsByName(String name);
}