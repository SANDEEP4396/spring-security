package com.spring.security.entity;

import java.util.Set;

public enum Role {
    ADMIN(Set.of(PermissionsAllowed.USER_READ, PermissionsAllowed.USER_WRITE, PermissionsAllowed.USER_DELETE)),
    USER(Set.of(PermissionsAllowed.USER_WRITE, PermissionsAllowed.USER_READ));

    private final Set<PermissionsAllowed> permissions;

    Role(Set<PermissionsAllowed> permissions){
        this.permissions = permissions;
    }

    public Set<PermissionsAllowed> getPermissions() {
        return permissions;
    }
}
