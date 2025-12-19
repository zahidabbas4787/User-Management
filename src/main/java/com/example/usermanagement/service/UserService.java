package com.example.usermanagement.service;

import com.example.usermanagement.dto.CreateUserRequest;
import com.example.usermanagement.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserDto createUser(CreateUserRequest request);
    UserDto getUserById(Long id);
    Page<UserDto> getAllUsers(Pageable pageable);
    UserDto updateUser(Long id, CreateUserRequest request);
    void deleteUser(Long id);
}
