package com.bikkadiT.demo.controllers;


import com.bikkadiT.demo.appConstants.AppConstant;
import com.bikkadiT.demo.dtos.*;
import com.bikkadiT.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {



    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;

    /**
     * @author Rajendra
     * @apiNote This Api is used for create User
     * @param userDto
     * @return
     */

    //create
    @PostMapping("/user")
public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
    log.info("Initiated request for create User Details");
    UserDto saveUser = userService.createUser(userDto);
    log.info("Completed request for create User details");
    return new ResponseEntity<>(saveUser, HttpStatus.CREATED);

}

    /**
     * @author Rajendra
     * @apiNote  This Api is used for update user
     * @param userId
     * @return
     */
    //update
    @PutMapping("/user/{id}")
public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("id")Integer userId){
        log.info("Initiate request for update user details with userid:{}*",userId);
        UserDto updatedUser = userService.updateUser(userDto,userId);
        log.info("Completed request for update user details with userid:{}*",userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
}
    //delete
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String>deleteUser(@PathVariable("id") Integer userId){
        log.info("Initiate request for delete user details with userid:{}*",userId);

        userService.deleteUser(userId);
        log.info("Completed request for delete user details with userid:{}*",userId);

        return new ResponseEntity<>(AppConstant.USER_DELETED,HttpStatus.OK);
    }

    /**
     * @aurhor Rajendra
     * @apiNote This Api is used for get single user
     * @param userId
     * @return
     */
    //get single
    @GetMapping("user/{userId}")
public ResponseEntity<UserDto>getSingleUser(@PathVariable() Integer userId){
        log.info("Initiate request for get user details with userid:{}*",userId);
    UserDto userById = userService.getUserById(userId);
        log.info("Completed request for get user details with userid:{}*",userId);

    return new ResponseEntity<UserDto>(userById,HttpStatus.OK);
}
    //getAll
    /**
     * @aurhor Rajendra
     * @apiNote This Api is used for get all users

     * @return
     */
    @GetMapping("/user")
public ResponseEntity <PageableResponse<UserDto>> getAllUser(

        @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
        @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
        @RequestParam(value = "sortby",defaultValue = "name",required = false) String sortBy,
        @RequestParam(value = "sortdir",defaultValue = "asc",required = false) String sortDir){
    log.info("Initiate request to get all user details ");

        log.info("Completed request to get all user details");
    return new ResponseEntity <PageableResponse<UserDto>>(userService.getAllUser(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
}
    @GetMapping("user/email/{email}")
    public ResponseEntity<UserDto>getUserByEmail(@PathVariable("email") String email) {
        log.info("Initiate request for get user details with email id:{}*", email);
        UserDto saveUserByEmail=userService.getUserByEmail(email);
        log.info("Completed request for get user details with userid:{}*", email);


        return new ResponseEntity<UserDto>(saveUserByEmail, HttpStatus.OK);
    }
    @GetMapping("user/search/{keyword}")
    public ResponseEntity <List<UserDto>>searchUserNameContaining(@PathVariable("keyword") String Keyword) {
        log.info("Initiate request for search user details with userid:{}*", Keyword);
        List<UserDto> dtos = userService.searchUser(Keyword);
        log.info("Completed request for get user details with userid:{}*", Keyword);


        return new ResponseEntity<>(dtos, HttpStatus.OK);

    }
    @PostMapping("/image/{productId}")
    public ResponseEntity<ImageResponse> uplodProductImage(
            @PathVariable String productId,
            @RequestParam("productImage") MultipartFile image,
            String imagePath, Object userId, UserDto userDto) throws IOException {
        String fileName = fileService.uploadFile(image, imagePath);

         UserService.get(userId);
        userDto.setUserImageName(fileName);
        UserDto updatedUser = userService.updateUser(userDto, (Integer) userId);
        ImageResponse response = ImageResponse.builder().imageName(updatedUser.getImageName()).message("product image successfully uploaded").status(String.valueOf(HttpStatus.CREATED)).success(String.valueOf(true)).build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
    @GetMapping("/image/{productId}")
    public void serveProductImage(@PathVariable String productId, HttpServletResponse response, String imagePath) throws IOException, IOException {
         UserDto userDto=UserService.get(productId);
        InputStream resource = fileService.getResource(imagePath);

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());

    }
}

