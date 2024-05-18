package com.javaguides.lifestyledelicioswebApp.controller;

import com.javaguides.lifestyledelicioswebApp.service.DeactivateService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeactivateController {

    @Autowired
    private DeactivateService userService;

    @PostMapping("/deactivate_account")
    public String deactivateAccount(HttpServletRequest request) {
        boolean isDeactivated = userService.deactivateAuthenticatedUser();
        if (isDeactivated) {
            SecurityContextHolder.clearContext(); // Clear the security context
            HttpSession session = request.getSession(false); // Get the session if it exists
            if (session != null) {
                session.invalidate(); // Invalidate the session
            }
            // Ensure the redirect URL is correctly pointing to your login page URL with the deactivated query parameter
            return "redirect:/login?deactivated=true";
        } else {
            // Ensure this URL correctly points to an error page designed to handle deactivation failures
            return "redirect:/error_page?error=deactivation_failed";
        }
    }
}