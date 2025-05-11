package com.ntt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntt.entity.Recruiter;
import com.ntt.repository.RecruiterRepository;

@Service
public class RecruiterServiceImpl implements RecruiterService {

	@Autowired
	private RecruiterRepository recruiterRepository;

	@Override
	public Recruiter register(Recruiter recruiter) {
		// TODO Auto-generated method stub
		return recruiterRepository.save(recruiter);
	}

	@Override
	public Recruiter findByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return recruiterRepository.findByEmailAndPassword(email, password);
	}

	@Override
	public Recruiter getById(int candidateId) {

		Optional<Recruiter> optional = this.recruiterRepository.findById(candidateId);

		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}

	}

	@Override
	public List<Recruiter> getAllRecruiters() {
		// TODO Auto-generated method stub
		return recruiterRepository.findAll();
	}

}
