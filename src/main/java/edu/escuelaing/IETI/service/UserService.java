package edu.escuelaing.IETI.service;

import edu.escuelaing.IETI.dto.UserDto;
import edu.escuelaing.IETI.entities.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
    User create(User user);

    User findById(String id);

    List<User> getAll();

    void deleteById(String id);

    User update(User user, String userId);
}
