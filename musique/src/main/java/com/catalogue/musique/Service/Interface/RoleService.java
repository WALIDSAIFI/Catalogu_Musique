package com.catalogue.musique.Service.Interface;

import com.catalogue.musique.DTO.RoleDTO;
import com.catalogue.musique.Entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    RoleDTO createRole(RoleDTO roleDTO);

    RoleDTO getRoleById(String id);

    List<RoleDTO> getAllRoles();

    Page<RoleDTO> getAllRolesPaged(Pageable pageable);

    RoleDTO updateRole(String id, RoleDTO roleDTO);

    void deleteRole(String id);
    public Role findByName(String name);
}
