package com.bikkadiT.demo.services;

import com.bikkadiT.demo.dtos.PageableResponse;
import com.bikkadiT.demo.dtos.UserDto;
import com.bikkadiT.demo.entyties.User;

import java.util.List;

public interface UserService {

    //create

    UserDto  createUser(UserDto userDto);
    //update
    UserDto updateUser(UserDto userDto,Integer userid);

    //delete
    void deleteUser(Integer userid);

    //get all users
    PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir);

    //get single user by id
    UserDto getUserById(Integer userid);

    //get single user by email
    UserDto getUserByEmail(String email);


    //search user
List<UserDto>searchUser(String keyword);

    //other user specific features

}
