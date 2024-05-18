package com.javaguides.lifestyledelicioswebApp.controller;


import com.javaguides.lifestyledelicioswebApp.model.UsersModel;
import com.javaguides.lifestyledelicioswebApp.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class UsersController {

    private final UsersService usersService;

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/sign_in")
    public String signIn(Model model) {
        model.addAttribute("registerRequest",new UsersModel());
        return "sign_in";
    }

    @PostMapping("/sign_in")
    public String signInPost(@ModelAttribute UsersModel usersModel, Model model) {
        System.out.println("register request " + usersModel);

        UsersModel registeredUser = usersService.registerUser(usersModel.getLogin(), usersModel.getPassword(), usersModel.getEmail(), usersModel.getName(), usersModel.getSurname(), usersModel.getPhoneNumber());

        if (registeredUser == null) {
            model.addAttribute("registrationError", "Registration failed. Please try again.");
            return "sign_in"; // Assuming "sign_in" is your registration view name
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("loginErrorMessage", "Your username or password is incorrect.");
        }
        model.addAttribute("loginRequest", new UsersModel()); // Assuming you have a UsersModel object for the login form
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute UsersModel usersModel, Model model) {
        System.out.println("login request " + usersModel);

        UsersModel authenticate = usersService.aunthenticate(usersModel.getLogin(), usersModel.getPassword());

        if (authenticate != null) {
            model.addAttribute("userLogin", authenticate.getLogin());
            return "menu";
        } else {
            model.addAttribute("loginError", "Invalid username or password.");
            return "login"; // return to the login view instead of error_page
        }
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/forgot_password")
    public String forgot_password() {
        return "forgot_password";
    }

    @GetMapping("index")
    public String showHomePage(){
        return "index";
    }

    @GetMapping("/menu")
    public String menu() {
        return "menu";
    }

    @GetMapping("/pay")
    public String pay() {
        return "pay";
    }

    @GetMapping("/summary")
    public String summary() {
        return "summary";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/sign_out")
    public String sign_out() {
        return "sign_out";
    }

    @GetMapping("/deactivate")
    public String deactivate_account() {
        return "deactivate";
    }


}







