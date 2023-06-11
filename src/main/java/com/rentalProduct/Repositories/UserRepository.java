package com.rentalProduct.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalProduct.Entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User getByName(String name);

}
