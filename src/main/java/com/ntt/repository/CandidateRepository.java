package com.ntt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ntt.entity.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

	Candidate findByEmailAndPassword(String email, String password);

	List<Candidate> findBySkillSetContainingIgnoreCase(String skill);

}
