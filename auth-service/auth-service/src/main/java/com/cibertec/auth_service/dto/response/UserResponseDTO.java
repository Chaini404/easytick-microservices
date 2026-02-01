package com.cibertec.auth_service.dto.response;

import java.time.LocalDateTime;

import com.cibertec.auth_service.model.type.UserRole;

public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
    private UserRole roleType;
    private Boolean enabled;
    private LocalDateTime createdAt;
}

