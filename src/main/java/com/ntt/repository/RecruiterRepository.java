package com.ntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ntt.entity.Recruiter;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter, Integer> {

	Recruiter findByEmailAndPassword(String email, String password);

}
