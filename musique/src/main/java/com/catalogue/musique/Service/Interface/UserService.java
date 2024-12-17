package com.catalogue.musique.Service.Interface;

import com.catalogue.musique.DTO.UserDTO;
import com.catalogue.musique.DTO.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);

    UserDTO getUserById(String id);

    List<UserDTO> getAllUsers();

    Page<UserDTO> getAllUsersPaged(Pageable pageable); // Méthode pour la pagination

    UserDTO updateUser(String id, UserDTO userDTO);

    void deleteUser(String id);
    public UserResponse registerUser(UserDTO request);
}
