package com.ntt.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ntt.entity.Candidate;
import com.ntt.entity.Interview;
import com.ntt.entity.Recruiter;
import com.ntt.service.CandidateService;
import com.ntt.service.InterviewService;
import com.ntt.service.RecruiterService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class RecruiterController {

	@Autowired
	private RecruiterService recruiterService;

	@Autowired
	private InterviewService interviewService;

	@Autowired
	private CandidateService candidateService;

	@GetMapping("/recruiterlogin")
	public String goToAdminLoginPage() {

		return "recruiterlogin";
	}

	@GetMapping("/recruiterregister")
	public String goToRecruiterRegisterPage() {

		return "recruiterregister";
	}

	@PostMapping("/recruiterregister")
	public ModelAndView registerRecruiter(@ModelAttribute Recruiter recruiter, HttpServletRequest request) {

		ModelAndView mv = new ModelAndView();
		if (this.recruiterService.register(recruiter) != null) {
			mv.addObject("status", " Successfully Registered");
			mv.setViewName("index");
		}

		else {
			mv.addObject("status", "Recruiter Failed to Registered");
			mv.setViewName("index");
		}

		return mv;
	}

	@PostMapping("/recruiterlogin")
	public ModelAndView loginCandiate(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();

		Recruiter loggedInRecruiter = recruiterService.findByEmailAndPassword(email, password);

		if (loggedInRecruiter != null) {
			HttpSession session = request.getSession();
			session.setAttribute("activeuser", loggedInRecruiter);
			session.setAttribute("userlogin", "recruiter");
			mv.addObject("status", " Successfully Logged in as Recruiter!");
			mv.setViewName("index");
		}

		else {
			mv.addObject("status", "Failed to login as Recruiter!");
			mv.setViewName("index");
		}

		return mv;
	}

	@GetMapping("/scheduleInterview")
	public ModelAndView scheduleInterview(@RequestParam("candidateId") int candidateId,
			@RequestParam("dateTime") String dateTime, HttpSession session) {

		ModelAndView mv = new ModelAndView();

		Recruiter recruiter = (Recruiter) session.getAttribute("activeuser");

		if (recruiter == null) {
			mv.addObject("status", "Please Log In as Recruiter to Schedule the Interview for the Candidate!");
			mv.setViewName("index");
			return mv;
		}

		Candidate candidate = candidateService.getById(candidateId);

		// Ensure the recruiter entity is managed
		recruiter = recruiterService.getById(recruiter.getId());

		Interview interview = new Interview();
		interview.setCandidate(candidate);
		interview.setAddedTime(LocalDateTime.now().toString());
		interview.setRecruiter(recruiter);
		interview.setIsOfferLetterSent("No");
		interview.setJoiningDate("Pending");
		interview.setDateTime(dateTime);
		interview.setStatus("Pending");

		interviewService.addInterview(interview);

		mv.addObject("status", "Interview Successfully Scheduled!!!");
		mv.setViewName("index");
		return mv;

	}

	@GetMapping("/schedule-interview/{candidateId}")
	public ModelAndView scheduleInterviewPage(@PathVariable("candidateId") int candidateId, HttpSession session) {

		ModelAndView mv = new ModelAndView();

		Candidate candidate = candidateService.getById(candidateId);

		mv.addObject("candidate", candidate);
		mv.setViewName("scheduleinterview");
		return mv;

	}

	@GetMapping("/schedule-interviews")
	public ModelAndView viewScheduledInterviews(HttpSession session) {

		ModelAndView mv = new ModelAndView();

		Recruiter recruiter = (Recruiter) session.getAttribute("activeuser");

		// Ensure the recruiter entity is managed
		recruiter = recruiterService.getById(recruiter.getId());

		List<Interview> interviews = interviewService.getByRecruiter(recruiter);

		mv.addObject("interviews", interviews);
		mv.setViewName("scheduledinterviews");
		return mv;

	}

	@GetMapping("/update-interview-status")
	public ModelAndView updateInterviewStatus(@RequestParam("interviewId") int interviewId,
			@RequestParam("status") String status) {

		ModelAndView mv = new ModelAndView();

		Interview interview = interviewService.getById(interviewId);

		interview.setStatus(status);

		this.interviewService.addInterview(interview);

		mv.addObject("status", "Candidate Interview Selection Status Updated Successful!!");
		mv.setViewName("index");
		return mv;

	}

	@GetMapping("/send-offer-letter")
	public ModelAndView sendOfferLetter(@RequestParam("interviewId") int interviewId) {

		ModelAndView mv = new ModelAndView();

		Interview interview = interviewService.getById(interviewId);

		interview.setIsOfferLetterSent("Yes");

		this.interviewService.addInterview(interview);

		mv.addObject("status", "Offer Letter Sent to Candidate!!!");
		mv.setViewName("index");
		return mv;

	}

}
