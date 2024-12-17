package com.catalogue.musique.Service.Implimentation;

import com.catalogue.musique.DTO.UserDTO;
import com.catalogue.musique.DTO.UserResponse;
import com.catalogue.musique.Entity.Role;
import com.catalogue.musique.Entity.Users;
import com.catalogue.musique.Mapper.UserMapper;
import com.catalogue.musique.Repository.RoleRepository;
import com.catalogue.musique.Repository.UserRepository;

import com.catalogue.musique.Service.Interface.RoleService;
import com.catalogue.musique.Service.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private  RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public UserDTO createUser(UserDTO userDTO) {
        Users user = userMapper.toEntity(userDTO);
        Users savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    @Override
    public UserDTO getUserById(String id) {
        Optional<Users> user = userRepository.findById(id);
        return user.map(userMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'id : " + id));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<UserDTO> getAllUsersPaged(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toDTO);
    }

    @Override
    public UserDTO updateUser(String id, UserDTO userDTO) {
        Users existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'id : " + id));
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setPassword(userDTO.getPassword());
        existingUser.setRoles(userMapper.toEntity(userDTO).getRoles());
        Users updatedUser = userRepository.save(existingUser);
        return userMapper.toDTO(updatedUser);
    }

    @Override
    public void deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Utilisateur non trouvé avec l'id : " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse registerUser(UserDTO request) {
        Users user = userMapper.toEntity(request);
       //Role role = roleService.findByName("USER");
        //user.setRoles(Collections.singletonList(role));

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        UserResponse response = new UserResponse();
        response.setUsername(user.getUsername());
        response.setRoles(user.getRoles().stream()
                .map(Role::getName)
                .toList());

        return response;
    }

}
