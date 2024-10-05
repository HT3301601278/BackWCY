package org.example.backwcy.dao;

import org.example.backwcy.entity.Device;
import org.example.backwcy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findByUser(User user);
    boolean existsByNameAndUser(String name, User user);
}