package com.bikkadiT.demo.repositories;

import com.bikkadiT.demo.entyties.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);


   List<User> findByNameContaining(String keywords);

}
