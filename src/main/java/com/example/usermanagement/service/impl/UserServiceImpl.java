package com.example.usermanagement.service.impl;

import com.example.usermanagement.dto.CreateUserRequest;
import com.example.usermanagement.dto.UserDto;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.exception.ResourceNotFoundException;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    private UserDto toDto(User u) {
        return new UserDto(u.getId(), u.getName(), u.getEmail());
    }

    @Override
    public UserDto createUser(CreateUserRequest request) {
        repo.findByEmail(request.getEmail()).ifPresent(u -> {
            throw new IllegalArgumentException("Email already exists");
        });

        User u = new User(request.getName(), request.getEmail(), request.getPassword());
        User saved = repo.save(u);
        return toDto(saved);
    }

    @Override
    public UserDto getUserById(Long id) {
        User u = repo.findById(id)
                     .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        return toDto(u);
    }

    @Override
    public Page<UserDto> getAllUsers(Pageable pageable) {
        Page<User> page = repo.findAll(pageable);
        return page.map(this::toDto);
    }

    @Override
    public UserDto updateUser(Long id, CreateUserRequest request) {
        User u = repo.findById(id)
                     .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        u.setName(request.getName());
        u.setEmail(request.getEmail());
        u.setPassword(request.getPassword());
        User updated = repo.save(u);
        return toDto(updated);
    }

    @Override
    public void deleteUser(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
        repo.deleteById(id);
    }
}
