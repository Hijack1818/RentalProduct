package com.rentalProduct.Repositories;

import java.sql.Time;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rentalProduct.Entities.Product;

public interface ProductRepository extends JpaRepository<Product,String>{
	
	@Query("SELECT p FROM Product p WHERE p.startTime >= :endTime OR p.endTime <= :startTime")
	List<Product> findAvailableProducts(@Param("startTime") Time startTime,@Param("endTime") Time endTime);

}
