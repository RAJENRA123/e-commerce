package com.bikkadiT.demo.serviceImpl;

import com.bikkadiT.demo.dtos.PageableResponse;
import com.bikkadiT.demo.dtos.UserDto;
import com.bikkadiT.demo.entyties.User;
import com.bikkadiT.demo.exceptions.ResourceNotFoundException;
import com.bikkadiT.demo.helper.Helper;
import com.bikkadiT.demo.repositories.UserRepository;
import com.bikkadiT.demo.services.UserService;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Slf4j
@Service
public  class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public UserDto createUser(UserDto userDto) {
log.info("Initiating dao call for create user");
        String userId = UUID.randomUUID().toString();
        userDto.getUserid();

        //dto->entity
          User user=dtoToEntity(userDto);
          User saveUser=userRepository.save(user);
        //entity->dto
        UserDto newdto=entityToDto(saveUser);
        log.info("Completed dao call for create user");
        return newdto;
    }

    private UserDto entityToDto(User saveUser) {
        return mapper.map(saveUser,UserDto.class);
    }

    private User dtoToEntity(UserDto userDto) {
        return mapper.map(userDto,User.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userid) {
log.info("Initiating dao call for Update the user details with userId:{}", userid);

        User user=userRepository.findById(userid).orElseThrow(()->new ResourceNotFoundException("User not found with given id"));
        user.setName(userDto.getName());
        //email update
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setImageName(userDto.getImageName());
        //save data
        User updatedUser=userRepository.save(user);
        UserDto updatedDto=entityToDto(updatedUser);
    log.info("Completed dao call for Update the user details with userId:{}", userid);
       return updatedDto;
    }

    @Override
    public void deleteUser(Integer userid) {
User user=userRepository.findById(userid).orElseThrow(()->new ResourceNotFoundException("User not found exception"));
   //delete user
        userRepository.delete(user);
    }

    @Override
    public PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir) {
       log.info("Initiate dao call for get all user details");
        Sort sort = Sort.by(sortBy);
//        Sort sort=(sortdir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable=PageRequest.of(pageNumber,pageSize,sort);
        Page<User> page = userRepository.findAll(pageable);
        PageableResponse<UserDto> pageableReaponse=Helper.getPageableReaponse(page, UserDto.class);
        log.info("Completed dao call for get all user details");
        return pageableReaponse;
    }

    @Override
    public UserDto getUserById(Integer userid) {
        log.info("Initiate dao call for get  user details");

        User user = userRepository.findById(userid).orElseThrow(() -> new ResourceNotFoundException("User not found by given Id"));
        log.info("Completed dao call for get user details");

        return entityToDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
       log.info("Initiating dao call for get the user details with emailId:{}", email);

        User user=userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User not found by given emailId"));
       log.info("Completed dao call for get the user details with emailId:{}", email);
        return entityToDto(user);
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        log.info("Initiating dao call for search the user details with user:{}", keyword);

        List<User>users=userRepository.findByNameContaining(keyword);
        List<UserDto>dtoList=users.stream().map(user ->entityToDto(user)).collect(Collectors.toList());
        log.info("Completed dao call for search all user details");
        return dtoList;
    }
}
