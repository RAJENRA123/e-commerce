package com.bikkadiT.demo.repositories;

import com.bikkadiT.demo.entyties.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductRepository extends JpaRepository<Product,String> {
    //search
    Page<Product> findByTitleContaining(String subtitle,Pageable pageable);
    Page<Product> findByLiveTrue(Pageable pageable);


}
