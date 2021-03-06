package com.ua.lev_neko.controllers;

import com.ua.lev_neko.models.Customer;
import com.ua.lev_neko.models.Role;
import com.ua.lev_neko.service.CustomerService;
import com.ua.lev_neko.service.CustomerServiceImpl;
import com.ua.lev_neko.utils.CustomerEditor;
import com.ua.lev_neko.utils.CustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@PropertySource("classpath:validation.properties")
public class MainController {

    @Autowired
    private Environment environment;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @GetMapping("/")
    public String index(Model model){
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                //when Anonymous Authentication is enabled
                !(SecurityContextHolder.getContext().getAuthentication()
                        instanceof AnonymousAuthenticationToken) ){
            Customer user = (Customer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("user",user);
            //return "success";
            Role role = user.getRole();
            if (role.equals(Role.ROLE_USER)){
                return "redirect:/user/"+user.getUsername();
            }
            else{
                return "redirect:/admin/"+user.getUsername();
            }
        }else {
            return "index";
        }
    }

    @PostMapping("/success")
    public String success(Model model){
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                //when Anonymous Authentication is enabled
                !(SecurityContextHolder.getContext().getAuthentication()
                        instanceof AnonymousAuthenticationToken) ){
            Customer user = (Customer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("user",user);
            Role role = user.getRole();
            if (role.equals(Role.ROLE_USER)){
                return "redirect:/user/"+user.getUsername();
            }
            else{
                return "redirect:/admin/"+user.getUsername();
            }
        }
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/user/{username}")
    public String user(@PathVariable String username,Model model){
        Customer user =(Customer) customerServiceImpl.loadUserByUsername(username);
        model.addAttribute("user",user);
        return "user";
    }

    @GetMapping("/admin/{username}")
    public String admin(@PathVariable String username,Model model){
        Customer user =(Customer) customerServiceImpl.loadUserByUsername(username);
        model.addAttribute("user",user);
        return "admin";
    }

    @GetMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }


    @Autowired
    private CustomerEditor customerEditor;
    @Autowired
    private CustomerValidator customerValidator;

    @PostMapping("/save")
    public String save(Customer customer , BindingResult result , Model model){
        customerValidator.validate(customer,result);
        if (result.hasErrors()) {
            String errors = "";
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError error : allErrors) {
                errors += " " + environment.getProperty(error.getCode());
            }
            model.addAttribute("error", errors);
            return "index";
        }
        customerEditor.setValue(customer);
        customerService.save(customer);
        System.out.println(customer.toString());
        return "login";
    }

}
