package edu.escuelaing.IETI.impl;

import edu.escuelaing.IETI.dto.UserDto;
import edu.escuelaing.IETI.entities.User;
import edu.escuelaing.IETI.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceIMPL implements UserService {

    private HashMap<String, User> users = new HashMap<String, User>();

    @Override
    public User create(User user) {
        users.put(user.getId(),user);
        return user;
    }

    @Override
    public User findById(String id) {
        return users.get(id);
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<User>(users.values());
    }

    @Override
    public void deleteById(String id) {
        users.remove(id);

    }

    @Override
    public User update(User user, String userId) {
        users.replace(userId,user);
        return user;
    }




}
