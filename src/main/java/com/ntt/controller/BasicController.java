package com.ntt.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class BasicController {

    @GetMapping("/fa")
    public String faqpath(Model model) {
        // Create a sample list of jobs for demonstration
        return "faq"; // This will resolve to the job-list.html file
    }
    
    @GetMapping("/tc")
    public String tc(Model model) {
        // Create a sample list of jobs for demonstration
        return "termsAndCondition"; // This will resolve to the job-list.html file
    }
    @GetMapping("/contactpage")
    public String contactPath(Model model) {
        // Create a sample list of jobs for demonstration
        return "contact"; // This will resolve to the job-list.html file
    }
   
}
