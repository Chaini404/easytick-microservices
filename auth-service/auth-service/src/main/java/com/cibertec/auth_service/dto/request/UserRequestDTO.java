package com.cibertec.auth_service.dto.request;

import com.cibertec.auth_service.model.type.UserRole;

public class UserRequestDTO {

    private String name;
    private String email;
    private String password;
    private UserRole roleType;
}

