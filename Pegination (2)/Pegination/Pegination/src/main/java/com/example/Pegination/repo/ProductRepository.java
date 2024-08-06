package com.example.Pegination.repo;

import com.example.Pegination.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Page<Product> findByCompanyId(Integer companyId , Pageable pageable);

}
