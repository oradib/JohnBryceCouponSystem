package com.orad.Project3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orad.Project3.models.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

	boolean existsByEmail(String email);

	boolean existsByName(String name);

	Company findByEmail(String email);

	int findIdByEmailAndPassword(String email, String password);

	boolean existsByEmailAndPassword(String email, String password);

}
