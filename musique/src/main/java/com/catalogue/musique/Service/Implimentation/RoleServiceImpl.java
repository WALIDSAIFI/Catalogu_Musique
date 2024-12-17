package com.catalogue.musique.Service.Implimentation;

import com.catalogue.musique.DTO.RoleDTO;
import com.catalogue.musique.Entity.Role;
import com.catalogue.musique.Exception.RoleNotFound;
import com.catalogue.musique.Mapper.RoleMapper;
import com.catalogue.musique.Repository.RoleRepository;
import com.catalogue.musique.Service.Interface.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        this.roleMapper = RoleMapper.INSTANCE;
    }

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
        if(roleRepository.findByName( roleDTO.getName()).isPresent()){
            throw new RoleNotFound("Role already exists");
        }
        Role role = roleMapper.toEntity(roleDTO);
        role = roleRepository.save(role);
        return roleMapper.toDTO(role);
    }



    @Override
    public RoleDTO getRoleById(String id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.map(roleMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Rôle non trouvé avec l'id : " + id));
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<RoleDTO> getAllRolesPaged(Pageable pageable) {
        return roleRepository.findAll(pageable).map(roleMapper::toDTO);
    }

    @Override
    public RoleDTO updateRole(String id, RoleDTO roleDTO) {
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rôle non trouvé avec l'id : " + id));
        existingRole.setName(roleDTO.getName());
        Role updatedRole = roleRepository.save(existingRole);
        return roleMapper.toDTO(updatedRole);
    }

    @Override
    public void deleteRole(String id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Rôle non trouvé avec l'id : " + id);
        }
        roleRepository.deleteById(id);
    }

    @Override
    public Role findByName(String name) {
        Optional<Role> role = roleRepository.findByName(name);
        if (role.isPresent()) {
            return role.get();
        } else {

            throw new RoleNotFound("Role not found with name: " + name);
        }
    }

}
