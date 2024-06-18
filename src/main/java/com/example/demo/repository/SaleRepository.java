package com.example.demo.repository;

import com.example.demo.model.Sale;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Sale s SET s.productId = :productId WHERE s = :sale")
    void saveByProductId(int productId, Sale sale);
}
