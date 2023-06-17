package com.bikkadiT.demo.repositories;

import com.bikkadiT.demo.entyties.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
