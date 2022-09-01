package edu.escuelaing.IETI.controller;

import edu.escuelaing.IETI.dto.UserDto;
import edu.escuelaing.IETI.entities.User;
import edu.escuelaing.IETI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.String.valueOf;

@RestController
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        //TODO implement this method using UserService
        List<User> users =userService.getAll();
        ArrayList<UserDto> usersDto = new ArrayList<UserDto>();
        for(int i = 0; i<users.size(); i++){
            usersDto.add(convertToUserDto(users.get(i)));
            //System.out.println(users.get(i).getId()); Los id se generan aleatoreamente, con esto los puede ver
        }
        return new ResponseEntity<List<UserDto>>(usersDto, HttpStatus.ACCEPTED);
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<UserDto> findById( @PathVariable String id ) {

        //TODO implement this method using UserService
        return new ResponseEntity<>(convertToUserDto(userService.findById(id)),HttpStatus.ACCEPTED);
    }


    @PostMapping
    public ResponseEntity<UserDto> create( @RequestBody UserDto userDto ) {
        //TODO implement this method using UserService
        User user =convertToUser(userDto);
        userService.create(user);
        return new ResponseEntity<>(userDto,HttpStatus.ACCEPTED);
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<UserDto> update( @RequestBody UserDto user, @PathVariable String id ) {
        //TODO implement this method using UserService
        User userN = convertToUser(user);
        userN.setId(id);
        userService.update(userN, userN.getId());
        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Boolean> delete( @PathVariable String id ) {
        //TODO implement this method using UserService
        boolean flag = false;
        try{
            userService.deleteById(id);
            flag = true;
        }
        catch (Exception e){
            flag = false;
        }
        return new ResponseEntity<>(flag, HttpStatus.ACCEPTED);
    }

    public UserDto convertToUserDto(User userC){

        UserDto userDto = new UserDto();
        userDto.setName(userC.getName());
        userDto.setEmail(userC.getEmail());
        userDto.setLastName(userC.getLastName());
        return userDto;
    }
    public User convertToUser(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setId(valueOf(Math.random()));
        user.setCreatedAt(new Date());
        user.setLastName(userDto.getLastName());
        return user;
    }
}

