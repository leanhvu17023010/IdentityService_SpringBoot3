package com.devteria.identityservice.mapper;

import org.mapstruct.Mapper;

import com.devteria.identityservice.dto.request.PermissionRequest;
import com.devteria.identityservice.dto.response.PermissionResponse;
import com.devteria.identityservice.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    // @Mapping(source = "firstName", target = "lastName")  // Custom mapping attribute
    // @Mapping(target = "lastName", ignore = true)  // Không mapping field lastName
    PermissionResponse toPermissionResponse(Permission permission);
}
