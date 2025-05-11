package com.ntt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntt.entity.Candidate;
import com.ntt.repository.CandidateRepository;

@Service
public class CandidateServiceImpl implements CandidateService {

	@Autowired
	private CandidateRepository candidateRepository;

	@Override
	public Candidate register(Candidate candidate) {
		// TODO Auto-generated method stub
		return candidateRepository.save(candidate);
	}

	@Override
	public Candidate findByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return candidateRepository.findByEmailAndPassword(email, password);
	}

	@Override
	public Candidate getById(int candidateId) {

		Optional<Candidate> optional = this.candidateRepository.findById(candidateId);

		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}

	}

	@Override
	public List<Candidate> getBySkillSet(String skill) {
		// TODO Auto-generated method stub
		return candidateRepository.findBySkillSetContainingIgnoreCase(skill);
	}

	@Override
	public List<Candidate> getAllCandidates() {
		// TODO Auto-generated method stub
		return candidateRepository.findAll();
	}

}
