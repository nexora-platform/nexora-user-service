package com.nexora.user.service;

import com.nexora.user.dto.CreateUserRequest;
import com.nexora.user.dto.UserResponse;
import com.nexora.user.entity.User;
import com.nexora.user.repository.UserRepository;
import com.nexora.user.tenant.TenantContext;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserResponse createUser(CreateUserRequest request) {

        String tenantId = TenantContext.getTenant();

        log.info("Creating new user for tenant {} with email {}", tenantId, request.getEmail());

        User user = User.builder()
                .tenantId(tenantId)
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(request.getRole())
                .status("ACTIVE")
                .build();

        userRepository.save(user);

        log.info("User successfully created with id {}", user.getId());

        return mapToResponse(user);
    }

    public List<UserResponse> getUsers() {

        String tenantId = TenantContext.getTenant();

        log.info("Fetching users for tenant {}", tenantId);

        List<UserResponse> users = userRepository.findAllByTenantId(tenantId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        log.info("Total users found for tenant {}: {}", tenantId, users.size());

        return users;
    }

    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt())
                .build();
    }
}