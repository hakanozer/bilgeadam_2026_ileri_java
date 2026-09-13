package com.works.repository;

import com.works.entitiy.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Page<Product> Product içerisindeki title veya description q değişkenini arayan sorgu
    // select * from product where title ilike %q% or description ilike %q%
    Page<Product> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description, Pageable pageable);

    @Query("select p from Product p where p.title like %:q% or p.description like %:q%")
    Page<Product> search(String q, Pageable pageable);

    Page<Product> findByTitleContainsOrDescriptionContainsAllIgnoreCase(String title, String description, Pageable pageable);


}