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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private static CustomerDAO customerDAO1;
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

    /*@GetMapping("/findUsers")
        public String findUsers(@RequestParam("user") String user , Model model){
        String[] param = user.split(" ");
        if (param.length == 1) {
            String parametr = param[0];
            Set<Customer> allByName1 = finByOneParam(parametr);
            model.addAttribute("users", allByName1);
            return "UserList";
        } else if (param.length == 2){
            String p1 = param[0];
            String p2 = param[1];
            Set<Customer> list = findByTwoParam(p1, p2);
            model.addAttribute("users", list);
            return "UserList";
        }else if(param.length >= 3){
            model.addAttribute("error","You entered something wrong");
            return "UserList";
        }
        return "UserList";
    }*/

    public static Set<Customer> finByOneParam(String parametr) {
        List<Customer> allByName = customerDAO1.findAllByName(parametr);
        List<Customer> allBySurname = customerDAO1.findAllBySurname(parametr);
        allByName.addAll(allBySurname);
        Set<Customer> allByName1 = new HashSet<>(allByName);
        Customer uzer = MainController.findLocalUser();
        int id = uzer.getId();
        Iterator<Customer> iterator = allByName1.iterator();
        while (iterator.hasNext()) {
            Customer next = iterator.next();
            if (next.getId() == id) {
                iterator.remove();
            }
        }
        return allByName1;
    }


    public static Set<Customer> findByTwoParam(String p1 , String p2){
        List<Customer> list1 = customerDAO1.findAllByNameAndSurname(p1, p2);
        List<Customer> list2 = customerDAO1.findAllByNameAndSurname(p2, p1);
        list1.addAll(list2);
        Set<Customer> list = new HashSet<>(list1);
        Customer uzero = MainController.findLocalUser();
        int id = uzero.getId();
        Iterator<Customer> iterator = list.iterator();
        while (iterator.hasNext()){
        Customer next = iterator.next();
            if (next.getId() == id){
            iterator.remove();
            }
        }
        return list;
    }
}
