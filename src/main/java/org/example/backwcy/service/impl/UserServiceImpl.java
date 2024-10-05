package org.example.backwcy.service.impl;

import org.example.backwcy.dao.UserRepository;
import org.example.backwcy.entity.User;
import org.example.backwcy.exception.InvalidInputException;
import org.example.backwcy.exception.InvalidPasswordException;
import org.example.backwcy.exception.UserNotFoundException;
import org.example.backwcy.exception.UsernameAlreadyExistsException;
import org.example.backwcy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(String username, String password) {
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            throw new InvalidInputException("用户名和密码不能为空");
        }
        if (userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException("用户名已存在：" + username);
        }
        User user = new User(username, password);
        return userRepository.save(user);
    }

    @Override
    public User loginUser(String username, String password) {
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            throw new InvalidInputException("用户名和密码不能为空");
        }
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("用户不存在：" + username));
        if (!password.equals(user.getPassword())) {
            throw new InvalidPasswordException("密码错误");
        }
        return user;
    }

    @Override
    public User getUserById(Long userId) {
        if (userId == null) {
            throw new InvalidInputException("用户ID不能为空");
        }
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("用户不存在，ID: " + userId));
    }
}