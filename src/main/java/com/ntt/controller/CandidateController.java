package com.ntt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ntt.entity.Candidate;
import com.ntt.service.CandidateService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CandidateController {

	@Autowired
	private CandidateService candidateService;

	@GetMapping("/candidatelogin")
	public String goToAdminLoginPage() {

		return "candidatelogin";
	}

	@GetMapping("/candidateregister")
	public String goToCandidateRegisterPage() {

		return "candidateregister";
	}

	@PostMapping("/candidateregister")
	public ModelAndView registerCandidate(@ModelAttribute Candidate candidate, HttpServletRequest request) {

		ModelAndView mv = new ModelAndView();
		if (this.candidateService.register(candidate) != null) {
			mv.addObject("status", " Successfully Registered");
			mv.setViewName("index");
		}

		else {
			mv.addObject("status", "Candidate Failed to Registered");
			mv.setViewName("index");
		}

		return mv;
	}

	@PostMapping("/candidatelogin")
	public ModelAndView loginCandiate(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();

		Candidate loggedInCandidate = candidateService.findByEmailAndPassword(email, password);

		if (loggedInCandidate != null) {
			HttpSession session = request.getSession();
			session.setAttribute("activeuser", loggedInCandidate);
			session.setAttribute("userlogin", "candidate");
			mv.addObject("status", " Successfully Logged in as Candidate!");
			mv.setViewName("index");
		}

		else {
			mv.addObject("status", "Failed to login as Candidate!");
			mv.setViewName("index");
		}

		return mv;
	}

	@GetMapping("/addqualification")
	public String goToAddQualifiction() {

		return "addqualification";
	}

	@GetMapping("/addQualification")
	public ModelAndView addQualification(@RequestParam("qualification") String qualification, HttpSession session) {
		ModelAndView mv = new ModelAndView();

		Candidate candidate = (Candidate) session.getAttribute("activeuser");

		candidate.setQualification(qualification);

		this.candidateService.register(candidate);
		mv.addObject("status", "Candidate Qualification Added Successful!!");
		mv.setViewName("index");
		return mv;
	}

	@GetMapping("/updateskill")
	public String goToUpdateSkill() {

		return "updateskillset";
	}

	@GetMapping("/updateSkill")
	public ModelAndView updateskill(@RequestParam("skill") String skill, HttpSession session) {
		ModelAndView mv = new ModelAndView();

		Candidate candidate = (Candidate) session.getAttribute("activeuser");

		candidate.setSkillSet(skill);

		this.candidateService.register(candidate);
		mv.addObject("status", "Candidate Skill Updated Successful!!");
		mv.setViewName("index");
		return mv;
	}

	@GetMapping("/candidateprofile")
	public ModelAndView candidateProfile(HttpSession session) {
		ModelAndView mv = new ModelAndView();

		Candidate candidate = (Candidate) session.getAttribute("activeuser");

		mv.addObject("candidate", candidate);
		mv.setViewName("candidateprofile");
		return mv;
	}
	
	@GetMapping("/candidates")
	public ModelAndView viewCandidates() {
		ModelAndView mv = new ModelAndView();

		List<Candidate> candidates = this.candidateService.getAllCandidates();

		mv.addObject("candidates", candidates);
		mv.setViewName("candidates");
		return mv;
	}

}
