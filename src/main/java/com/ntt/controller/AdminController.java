package com.ntt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ntt.entity.Admin;
import com.ntt.entity.Candidate;
import com.ntt.entity.Interview;
import com.ntt.entity.Recruiter;
import com.ntt.service.AdminService;
import com.ntt.service.CandidateService;
import com.ntt.service.InterviewService;
import com.ntt.service.RecruiterService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private CandidateService candidateService;

	@Autowired
	private RecruiterService recruiterService;

	@Autowired
	private InterviewService interviewService;

	
	@GetMapping("/")
	public String goToIndexPage() {
		return "index";
	}
	

	@GetMapping("/home")
	public String goToHomePage() {
		return "index";
	}

	@GetMapping("/aboutus")
	public String goToAboutUs() {
		return "aboutus";
	}

	@GetMapping("/adminlogin")
	public String goToAdminLoginPage() {

		return "adminlogin";
	}

	@GetMapping("/adminregister")
	public String goToAdminRegisterPage() {

		return "adminregister";
	}

	@PostMapping("/adminregister")
	public ModelAndView registerAdmin(@ModelAttribute Admin admin, HttpServletRequest request) {

		ModelAndView mv = new ModelAndView();
		if (this.adminService.register(admin) != null) {
			mv.addObject("status", "Admin Successfully Registered");
			mv.setViewName("index");
		}

		else {
			mv.addObject("status", "Admin Failed to Registered");
			mv.setViewName("index");
		}

		return mv;
	}

	@PostMapping("/adminlogin")
	public ModelAndView loginAdmin(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();

		Admin loggedInAdmin = adminService.findByEmailAndPassword(email, password);

		if (loggedInAdmin != null) {
			HttpSession session = request.getSession();
			session.setAttribute("activeuser", loggedInAdmin);
			session.setAttribute("userlogin", "admin");
			mv.addObject("status", " Successfully Logged in as ADMIN!");
			mv.setViewName("index");
		}

		else {
			mv.addObject("status", "Failed to login as Admin!");
			mv.setViewName("index");
		}

		return mv;
	}

	@GetMapping("/logout")
	public ModelAndView loginAdmin(HttpSession session) {
		ModelAndView mv = new ModelAndView();

		session.removeAttribute("activeuser");
		session.removeAttribute("userlogin");

		mv.addObject("status", "Log out successful!!!");
		mv.setViewName("index");

		return mv;
	}

	@GetMapping("/viewcandidates")
	public ModelAndView viewCandidates() {
		ModelAndView mv = new ModelAndView();

		List<Candidate> candidates = this.candidateService.getAllCandidates();

		mv.addObject("candidates", candidates);
		mv.setViewName("viewcandidates");
		return mv;
	}

	@GetMapping("/viewrecruiters")
	public ModelAndView viewRecruiters() {
		ModelAndView mv = new ModelAndView();

		List<Recruiter> recruiters = this.recruiterService.getAllRecruiters();

		mv.addObject("recruiters", recruiters);
		mv.setViewName("viewrecruiters");
		return mv;
	}

	@GetMapping("/viewallinterviews")
	public ModelAndView viewallinterviews() {
		ModelAndView mv = new ModelAndView();

		List<Interview> interviews = this.interviewService.getAllInterviews();

		mv.addObject("interviews", interviews);
		mv.setViewName("viewallinterviews");
		return mv;
	}

	@GetMapping("/delete-interview/{interviewId}")
	public ModelAndView scheduleInterviewPage(@PathVariable("interviewId") int interviewId) {

		ModelAndView mv = new ModelAndView();

		Interview interview = interviewService.getById(interviewId);

		interviewService.delete(interview);

		mv.addObject("status", "Interview Enrtry Deleted Successful!!");
		mv.setViewName("index");
		return mv;

	}

}
