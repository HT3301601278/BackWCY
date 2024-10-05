package org.example.backwcy.service;

import org.example.backwcy.entity.User;

public interface UserService {
    User registerUser(String username, String password);
    User loginUser(String username, String password);
    User getUserById(Long userId);  // 添加这行
}