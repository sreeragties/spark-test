package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT SUM(p.price * s.quantity) AS total_revenue " +
            "FROM Product p JOIN p.sales s")
    Double getTotalRevenue();

    @Query("SELECT SUM(p.price * s.quantity) " +
            "FROM Product p JOIN p.sales s " +
            "WHERE p.id = :productId")
    Double getRevenueForEachProduct(@Param("productId") int productId);
}
