package com.nexora.user.repository;

import com.nexora.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findAllByTenantId(String tenantId);

}