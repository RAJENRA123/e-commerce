package com.bikkadiT.demo.dtos;

import com.bikkadiT.demo.validate.ImageNameValid;
import lombok.*;


import javax.validation.constraints.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class  UserDto {
    private Integer userid;


    @Size(min=3, max = 15,message = "Invalid Name!!")
    private String name;

    @Email(message = "Invalid User Email")
    @NotBlank(message = "Email is required !!")
    private String email;

    @NotBlank (message = "password is required!!")
    private  String password;

    @Size(min = 4,max = 6 ,message = "Invalid gender!!")
    private String gender;

    @NotBlank(message = "Write something about yourself")
    private String about;

   @ImageNameValid
    private String imageName;
}
