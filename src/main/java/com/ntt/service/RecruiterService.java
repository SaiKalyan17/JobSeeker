package com.ntt.service;

import java.util.List;

import com.ntt.entity.Recruiter;

public interface RecruiterService {

	Recruiter register(Recruiter recruiter);

	Recruiter findByEmailAndPassword(String email, String password);

	Recruiter getById(int candidateId);

	List<Recruiter> getAllRecruiters();

}
