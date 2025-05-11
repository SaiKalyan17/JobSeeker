package com.ntt.service;

import java.util.List;

import com.ntt.entity.Candidate;
import com.ntt.entity.Interview;
import com.ntt.entity.Recruiter;

public interface InterviewService {

	Interview addInterview(Interview interview);

	Interview updateInterview(Interview interview);

	Interview getById(int interviewId);

	List<Interview> getByCandidate(Candidate candidate);

	List<Interview> getByRecruiter(Recruiter recruiter);

	void delete(Interview interview);

	List<Interview> getAllInterviews();

}
