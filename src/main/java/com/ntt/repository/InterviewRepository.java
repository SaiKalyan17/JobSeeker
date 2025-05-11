package com.ntt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ntt.entity.Candidate;
import com.ntt.entity.Interview;
import com.ntt.entity.Recruiter;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Integer> {

	List<Interview> findByCandidate(Candidate candidate);

	List<Interview> findByRecruiter(Recruiter recruiter);

}
