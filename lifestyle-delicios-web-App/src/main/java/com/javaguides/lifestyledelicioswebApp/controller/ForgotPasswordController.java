package com.javaguides.lifestyledelicioswebApp.controller;
import com.javaguides.lifestyledelicioswebApp.service.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ForgotPasswordController {

    private final ForgotPasswordService forgotPasswordService;

    @Autowired
    public ForgotPasswordController(ForgotPasswordService forgotPasswordService) {
        this.forgotPasswordService = forgotPasswordService;
    }

    @PostMapping("/reset")
    public String forgotPassword(@RequestParam String email, @RequestParam String newPassword) {
        // Call the service method to reset the password
        forgotPasswordService.resetPassword(email, newPassword);
        return "redirect:/login"; // Redirect to login page after password reset
    }
}

