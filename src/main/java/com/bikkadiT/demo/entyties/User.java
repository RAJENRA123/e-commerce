package com.bikkadiT.demo.entyties;

import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userid;
    @Column(name = "user_name")
   private String name;
   @Column(name = "user_email",unique = true)
   private String email;
   @Column(name = "user_password",length = 10)
   private  String password;

   private String gender;

   @Column(length =1000)
   private String about;

   @Column(name="user_image_name")
   private String imageName;

}
