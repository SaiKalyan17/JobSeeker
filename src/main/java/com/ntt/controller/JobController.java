package com.ntt.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class JobController {

    @GetMapping("/job-list")
    public String jobList(Model model) {
        // Create a sample list of jobs for demonstration
        return "job-list"; // This will resolve to the job-list.html file
    }
    @GetMapping("/job-details")
    public String jobdetail(Model model) {
        // Create a sample list of jobs for demonstration
        return "job-details"; // This will resolve to the job-list.html file
    }
}
