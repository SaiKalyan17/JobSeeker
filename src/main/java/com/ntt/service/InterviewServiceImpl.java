package com.ntt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntt.entity.Candidate;
import com.ntt.entity.Interview;
import com.ntt.entity.Recruiter;
import com.ntt.repository.InterviewRepository;

@Service
public class InterviewServiceImpl implements InterviewService {

	@Autowired
	private InterviewRepository interviewRepository;

	@Override
	public Interview addInterview(Interview interview) {
		// TODO Auto-generated method stub
		return interviewRepository.save(interview);
	}

	@Override
	public Interview updateInterview(Interview interview) {
		// TODO Auto-generated method stub
		return interviewRepository.save(interview);
	}

	@Override
	public Interview getById(int interviewId) {

		Optional<Interview> optional = this.interviewRepository.findById(interviewId);

		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}

	}

	@Override
	public List<Interview> getByCandidate(Candidate candidate) {
		// TODO Auto-generated method stub
		return interviewRepository.findByCandidate(candidate);
	}

	@Override
	public List<Interview> getByRecruiter(Recruiter recruiter) {
		// TODO Auto-generated method stub
		return interviewRepository.findByRecruiter(recruiter);
	}

	@Override
	public void delete(Interview interview) {
		interviewRepository.delete(interview);

	}

	@Override
	public List<Interview> getAllInterviews() {
		// TODO Auto-generated method stub
		return interviewRepository.findAll();
	}

}
