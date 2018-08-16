package com.ua.lev_neko.controllers;

import com.ua.lev_neko.dao.CustomerDAO;
import com.ua.lev_neko.models.Customer;
import com.ua.lev_neko.service.CustomerService;
import com.ua.lev_neko.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.bind.SchemaOutputResolver;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @Autowired
    private CustomerDAO customerDAO;

    @GetMapping("/UserList")
        public String UsersPage(){
            return "UserList";
        }

    @GetMapping("/findUsers")
        public String findUsers(@RequestParam("user") String user , Model model){
        String[] param = user.split(" ");
        if (param.length == 1){
            String parametr = param[0];
            List<Customer> allByName = customerDAO.findAllByName(parametr);
            List<Customer> allBySurname = customerDAO.findAllBySurname(parametr);
            allByName.addAll(allBySurname);
            allByName.stream().distinct().collect(Collectors.toList());
            if (SecurityContextHolder.getContext().getAuthentication() != null &&
                    SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                    //when Anonymous Authentication is enabled
                    !(SecurityContextHolder.getContext().getAuthentication()
                            instanceof AnonymousAuthenticationToken) ) {
                Customer uzer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                int id = uzer.getId();
                Iterator<Customer> iter = allByName.iterator();
                while (iter.hasNext()){
                    Customer next = iter.next();
                    if(next.getId()== id){
                        iter.remove();
                    }
                }
                model.addAttribute("users", allByName.toArray());
                return "UserList";
            }else{
                model.addAttribute("users", allByName);
                return "UserList";
            }
        }
        else if (param.length == 2){
            String p1 = param[0];
            String p2 = param[1];
            List<Customer> list1 = customerDAO.findAllByNameAndSurname(p1, p2);
            System.out.println(list1);
            List<Customer> list2 = customerDAO.findAllByNameAndSurname(p2, p1);
            System.out.println(list2);
            list1.addAll(list2);
            list1.stream().distinct().collect(Collectors.toList());
            if (SecurityContextHolder.getContext().getAuthentication() != null &&
                    SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                    //when Anonymous Authentication is enabled
                    !(SecurityContextHolder.getContext().getAuthentication()
                            instanceof AnonymousAuthenticationToken) ) {
                Customer uzero = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                int id = uzero.getId();
                Iterator<Customer> iter = list1.iterator();
                while (iter.hasNext()){
                    Customer next = iter.next();
                    if(next.getId()== id){
                        iter.remove();
                    }
                }
                model.addAttribute("users", list1);
                System.out.println(list1);
                return "UserList";
            }
            else{
                model.addAttribute("users", list1);
                return "UserList";
            }
        }else if(param.length >= 3){
            model.addAttribute("error","You entered something wrong");
            return "UserList";
        }
        return "UserList";
    }
}
