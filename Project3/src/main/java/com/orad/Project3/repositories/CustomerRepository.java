package com.orad.Project3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orad.Project3.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	boolean existsByEmail(String customerEmail);

	Customer findByEmail(String email);

	boolean existsByEmailAndPassword(String email, String password);

}
