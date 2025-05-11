package com.ntt.service;

import java.util.List;

import com.ntt.entity.Candidate;

public interface CandidateService {

	Candidate register(Candidate candidate);

	Candidate findByEmailAndPassword(String email, String password);

	Candidate getById(int candidateId);
	
	List<Candidate> getBySkillSet(String skill);

	List<Candidate> getAllCandidates();


}
